package com.bmc.emailsendservice.mailClients;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class GmailClient extends MailClient{

    public GmailClient(String user, String password) {
        super(user, password);
    }

    @Override
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = createJavaMailSender();
        mailSender.setHost(appData.getGmailHost());
        mailSender.setPort(appData.getGmailPort());
        return mailSender;
    }

}
