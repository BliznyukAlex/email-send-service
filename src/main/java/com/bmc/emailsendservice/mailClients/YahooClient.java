package com.bmc.emailsendservice.mailClients;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class YahooClient extends MailClient {

    public YahooClient(String user, String password) {
        super(user, password);
    }

    @Override
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = createJavaMailSender();
        mailSender.setHost(appData.getYahooHost());
        mailSender.setPort(appData.getYahooPort());
        return mailSender;
    }

}
