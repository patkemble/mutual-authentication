package com.davita.physician.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pkemble on 5/9/17.
 */
@Slf4j
@Component
public class RestClient implements CommandLineRunner {
    @Autowired
    RestTemplate template;

    @Override
    public void run(String... strings) throws Exception {
        // Expecting a URL from the command line to call as a RESTful call and print out the response
        ResponseEntity<String> resp = template.getForEntity(strings[0], String.class);
        log.info(resp.getBody());
    }
}
