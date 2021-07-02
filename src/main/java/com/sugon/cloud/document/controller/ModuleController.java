package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleServer;
import com.sugon.cloud.document.utils.CommonUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResultModel createModule(@RequestBody @ApiParam(name = "module") Module module){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.addModule(module);
        }catch (Exception e){
            log.error("add module error：" + e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module001"));
            }
        }
        return resultModel;
    }

    @DeleteMapping
    @ApiOperation("删除顶部模块")
    public ResultModel deleteModule(@RequestParam("id") Integer id){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.deleteModuleById(id);
        }catch (Exception e){
            log.error("delete module error：" + e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module002"));
            }
        }
        return resultModel;
    }

    @PutMapping
    @ApiOperation("修改顶部模块")
    public ResultModel updateModule(@RequestBody @ApiParam(name = "module") Module module){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.updateModule(module);
        }catch (Exception e){
            log.error("update module error：" + e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module003"));
            }
        }
        return resultModel;
    }

    @GetMapping
    @ApiOperation("查询顶部模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "module_name", value = "模块名称", required = true, dataType = "String", paramType = "query")})
    public ResultModel selectModuleByFuzzyName(@RequestParam("module_name") String moduleName){
        ResultModel resultModel = new ResultModel();
        try {
            List<Module> module = moduleServer.selectModuleByFuzzyName(moduleName);
            resultModel.setContent(module);
        }catch (Exception e){
            log.error("select module by fuzzy error：" + e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module004"));
            }
        }
        return resultModel;
    }




}
