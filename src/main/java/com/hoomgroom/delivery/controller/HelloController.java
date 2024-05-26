package com.hoomgroom.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.Generated;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Generated
    public String index() {
        return "Hello from deliveryService, please access our service through port 8081!";
    }

}
