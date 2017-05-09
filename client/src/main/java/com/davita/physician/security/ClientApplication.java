package com.davita.physician.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class ClientApplication {

    static String keyStoreLocation = ClientApplication.class.getClassLoader().getResource("client" +
            ".jks").getFile();

    static {
        System.setProperty("javax.net.ssl.trustStore", keyStoreLocation);
        System.setProperty("javax.net.ssl.trustStorePassword", "password");
        System.setProperty("javax.net.ssl.keyStore", keyStoreLocation);
        System.setProperty("javax.net.ssl.keyStorePassword", "password");

		/*
        * Verify the hostname for local environment endpoint that's being called (server). This verification would be
        * expanded in the production env per the javadoc:
        * Compares the hostname with the name from server certificate.
        */
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> {
            if (hostname.equals("localhost")) {
                return true;
            }
            return false;
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }


    @Bean
    public RestTemplate template() throws Exception {
        RestTemplate template = new RestTemplate();
        return template;
    }

}
