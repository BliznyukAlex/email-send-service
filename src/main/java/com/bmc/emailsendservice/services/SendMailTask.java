package com.bmc.emailsendservice.services;

import com.bmc.emailsendservice.api.models.ApiResponse;
import com.bmc.emailsendservice.api.models.SendEmailRequest;
import com.bmc.emailsendservice.config.ApplicationContextProvider;
import com.bmc.emailsendservice.mailClients.MailClient;
import com.bmc.emailsendservice.data.AppData;
import com.bmc.emailsendservice.data.models.EmailDto;
import com.bmc.emailsendservice.db.models.Employee;
import com.bmc.emailsendservice.db.models.EmployeeEmail;
import com.bmc.emailsendservice.db.models.Vendor;
import com.bmc.emailsendservice.db.repositories.EmployeeEmailRepository;
import com.bmc.emailsendservice.db.repositories.EmployeeRepository;
import com.bmc.emailsendservice.db.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.concurrent.Callable;

@Slf4j
public class SendMailTask implements Callable {

    private final AppData appData = ApplicationContextProvider.getApplicationContext().getBean(AppData.class);
    private final VendorRepository vendorRepository = ApplicationContextProvider.getApplicationContext().getBean(VendorRepository.class);
    private final EmployeeRepository employeeRepository = ApplicationContextProvider.getApplicationContext().getBean(EmployeeRepository.class);
    private final EmployeeEmailRepository employeeEmailRepository = ApplicationContextProvider.getApplicationContext().getBean(EmployeeEmailRepository.class);

    private final SendEmailRequest sendEmailRequest;

    public SendMailTask(SendEmailRequest sendEmailRequest) {
        this.sendEmailRequest = sendEmailRequest;
    }

    public ApiResponse sendEmailToCustomer() {
        log.info("Sending email to customer: " + sendEmailRequest.getCustomerEmail() + " with text: " + sendEmailRequest.getEmailText());
        Vendor vendor = getEmailVendor(sendEmailRequest.getCustomerEmail());
        Employee employee = employeeRepository.findById(sendEmailRequest.getEmployeeId()).orElse(null);
        EmployeeEmail employeeEmail = null;
        if (employee != null && vendor != null) {
            employeeEmail = employeeEmailRepository.findByEmployeeAndVendor(employee, vendor);
        }
        if (employeeEmail != null && employeeEmail.getEmail() != null && appData.getVendorClassMap().containsKey(vendor.getName())) {
            try {
                EmailDto emailDto = new EmailDto(employeeEmail.getEmail(), sendEmailRequest.getCustomerEmail(), sendEmailRequest.getEmailText(), sendEmailRequest.getSubject());
                MailClient mailClient = (MailClient) appData.getVendorClassMap().get(vendor.getName()).getConstructor(String.class, String.class).newInstance(employeeEmail.getEmail(), employeeEmail.getPassword());
                JavaMailSenderImpl javaMailSender = mailClient.getJavaMailSender();
                sendMail(javaMailSender, emailDto);
                log.info(String.format("Email sent to from employee: %s to customer: %s with subject: %s", emailDto.getFrom(), emailDto.getTo(), emailDto.getSubject()));
            } catch (Exception exc) {
                log.error(String.format("EXCEPTION: %s : %s", exc.getMessage(), Arrays.toString(exc.getStackTrace())));
                return new ApiResponse(String.format("error: message wasn't send: %s", sendEmailRequest));
            }
        } else {
            return new ApiResponse(String.format("error: message wasn't send: %s", sendEmailRequest));
        }
        return new ApiResponse();
    }

    private void sendMail(JavaMailSenderImpl javaMailSender, EmailDto emailDto) throws MessagingException {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(emailDto.getFrom());
            helper.setTo(emailDto.getTo());
            helper.setSubject(emailDto.getSubject());
            helper.setText(emailDto.getBody());
            javaMailSender.send(mimeMessage);

    }

    private Vendor getEmailVendor(String email) {
        String vendor = null;
        try {
            vendor = email.split("@")[1];
        } catch (Exception exc) {
            log.error(String.format("EXCEPTION: %s : %s", exc.getMessage(), Arrays.toString(exc.getStackTrace())));
        }
        return vendorRepository.findByName(vendor);
    }

    @Override
    public ApiResponse call() {
        try {
            return sendEmailToCustomer();
        } catch (Exception exc) {
            return new ApiResponse("error");
        }
    }
}
