package com.bmc.emailsendservice.mailClients;

import com.bmc.emailsendservice.config.ApplicationContextProvider;
import com.bmc.emailsendservice.data.AppData;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Slf4j
@NoArgsConstructor
public  abstract class MailClient{

    protected AppData appData = ApplicationContextProvider.getApplicationContext().getBean(AppData.class);

    protected String user;
    protected String password;


    public MailClient(String user, String password) {
        this.user = user;
        this.password = password;

    }

    public abstract JavaMailSenderImpl getJavaMailSender();

    protected JavaMailSenderImpl createJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(user);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }


}
