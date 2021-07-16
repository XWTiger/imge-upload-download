package com.sugon.cloud.document.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/micro-healthy")
@Api(tags = {"Doc健康检查操作接口"},description = " ")
public class HeathyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String health() throws Exception {

        int code = 404;
        try {
            String url = "http://sugoncloud-gateway-http.micro-service.svc.cluster.local:8080/sugoncloud-document-api/actuator/health";
            ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(new HttpHeaders()),JSONObject.class);
            code = responseEntity.getStatusCode().value();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(code>=400){
            throw new Exception("document service is unhealthy");
        }
        return "document service is healthy";
    }
}
