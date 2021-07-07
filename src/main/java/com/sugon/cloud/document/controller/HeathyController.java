package com.sugon.cloud.document.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/micro-healthy")
@Api(tags = {"Doc健康检查操作接口"},description = " ")
public class HeathyController {

    @GetMapping
    public String health() {
        return "cci service is healthy";
    }
}
