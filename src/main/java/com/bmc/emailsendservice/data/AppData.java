package com.bmc.emailsendservice.data;

import com.bmc.emailsendservice.mailClients.GmailClient;
import com.bmc.emailsendservice.mailClients.WallaClient;
import com.bmc.emailsendservice.mailClients.YahooClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AppData {

    @Getter
    @Value("${spring.mail.gmail.host}")
    private String gmailHost;
    @Getter
    @Value("${spring.mail.gmail.port}")
    private Integer gmailPort;
    @Getter
    @Value("${spring.mail.yahoo.host}")
    private String yahooHost;
    @Getter
    @Value("${spring.mail.yahoo.port}")
    private Integer yahooPort;
    @Getter
    @Value("${spring.mail.walla.host}")
    private String wallaHost;
    @Getter
    @Value("${spring.mail.walla.port}")
    private Integer wallaPort;

    private static final String GMAIL = "gmail.com";
    private static final String WALLA = "walla.co.il";
    private static final String YAHOO = "yahoo.com";

    @Getter
    private Map<String, Class> vendorClassMap;

    @Getter
    ExecutorService executorService = Executors.newFixedThreadPool(8);

    public void loadData() {
        vendorClassMap = new HashMap<>();
        vendorClassMap.put(GMAIL, GmailClient.class);
        vendorClassMap.put(WALLA, WallaClient.class);
        vendorClassMap.put(YAHOO, YahooClient.class);
    }
}
