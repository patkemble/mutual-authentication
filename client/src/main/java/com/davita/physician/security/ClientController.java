package com.davita.physician.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pkemble on 5/9/17.
 */
@RestController
@RequestMapping("/hello")
public class ClientController {

    @GetMapping(path = "/{name}")
    public String getName(@PathVariable("name") String name) {
        return name;
    }
}
