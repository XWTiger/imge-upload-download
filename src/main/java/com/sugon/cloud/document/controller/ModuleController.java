package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.entity.page.PageCL;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleServer;
import com.sugon.cloud.document.utils.CommonUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/document/module")
@Api(tags = {"文档模块接口"},description = " ")
@Slf4j
@Validated
public class ModuleController {

    @Autowired
    private ModuleServer moduleServer;

    @Autowired
    private ExceptionService exceptionService;

    @PostMapping
    @ApiOperation("添加模块")
    public ResultModel createModule(@RequestBody @ApiParam(name = "module") Module module){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.addModule(module);
        }catch (Exception e){
            log.error("add module error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module001"));
            }
        }
        return resultModel;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除模块")
    public ResultModel deleteModule(@PathVariable("id") @ApiParam("id") Integer id){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.deleteModuleById(id);
        }catch (Exception e){
            log.error("delete module error: {}", e);
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
    @ApiOperation("修改模块")
    public ResultModel updateModule(@RequestBody @ApiParam(name = "module") Module module){
        ResultModel resultModel = new ResultModel();
        try {
            moduleServer.updateModule(module);
        }catch (Exception e){
            log.error("update module error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module003"));
            }
        }
        return resultModel;
    }

    @GetMapping("/fuzzy/{module_name}")
    @ApiOperation("模糊查询模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "module_name", value = "模块名称", required = true, dataType = "String", paramType = "query")})
    public ResultModel selectModuleByFuzzyName(@PathVariable("module_name") @ApiParam("module_name") String moduleName){
        ResultModel resultModel = new ResultModel();
        try {
            List<Module> modules = moduleServer.selectModuleByFuzzyName(moduleName);
            resultModel.setContent(modules);
        }catch (Exception e){
            log.error("select modules by fuzzy error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module004"));
            }
        }
        return resultModel;
    }

    @GetMapping("/{id}")
    @ApiOperation("查询具体模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "模块id", required = true, dataType = "Integer", paramType = "query")})
    public ResultModel selectModuleById(@PathVariable("id") @ApiParam("id") Integer id){
        ResultModel resultModel = new ResultModel();
        try {
            Module module = moduleServer.selectModuleById(id);
            resultModel.setContent(module);
        }catch (Exception e){
            log.error("select module by id error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module008"));
            }
        }
        return resultModel;
    }

    @GetMapping("/all")
    @ApiOperation("查询所有模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_size", value = "每页条数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "page_num", value = "页数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "Integer", paramType = "query")
    })
    public ResultModel selectModule(@RequestParam(value = "page_size", required = false) Integer pageSize, @RequestParam(value = "page_num", required = false) Integer pageNum, @RequestParam(value = "name", required = false) String name){
        ResultModel resultModel = new ResultModel();
        try {
            if (Objects.isNull(pageNum) || Objects.isNull(pageSize)) {
                pageNum = 1;
                pageSize = 10000;
            }
            PageCL<Module> modules = moduleServer.selectModules(pageNum, pageSize, name);
            resultModel.setContent(modules);

        }catch (Exception e){
            log.error("select all module error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "module007"));
            }
        }
        return resultModel;
    }




}
