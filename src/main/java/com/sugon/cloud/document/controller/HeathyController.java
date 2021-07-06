package com.sugon.cloud.document.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micro-healthy")
public class HeathyController {

    @GetMapping
    public String health() {
        return "cci service is healthy";
    }
}
