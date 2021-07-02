package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module")
@Api(tags = {"文档信息-顶部模块"},description = " ")
@Slf4j
@Validated
public class ModuleController {

    @Autowired
    private ModuleServer moduleServer;

    @Autowired
    private ExceptionService exceptionService;

    @PostMapping
    @ApiOperation("添加顶部模块")
    public ResultModel createHpa(@RequestBody @ApiParam(name = "module") Module module){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.addModule(module);
        }catch (Exception e){
            log.error("add module error：" + e);
            resultModel.setStatusCode(0);
        }
        return resultModel;
    }

}
