package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.ModuleMenu;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleMenuServer;
import com.sugon.cloud.document.utils.CommonUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document/module-menu")
@Api(tags = {"文档菜单接口"},description = " ")
@Slf4j
@Validated
public class ModuleMenuController {

    @Autowired
    private ModuleMenuServer moduleMenuServer;

    @Autowired
    private ExceptionService exceptionService;

    @PostMapping
    @ApiOperation("添加菜单")
    public ResultModel createModuleMenu(@RequestBody @ApiParam(name = "moduleMenu") ModuleMenu moduleMenu){
        ResultModel resultModel = new ResultModel();
        try {
            Integer id = moduleMenuServer.addModuleMenu(moduleMenu);
            moduleMenu.setId(id);
            resultModel.setContent(moduleMenu);
        }catch (Exception e){
            log.error("add moduleMenu error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "moduleMenu001"));
            }
        }
        return resultModel;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除菜单")
    public ResultModel deleteModule(@PathVariable("id") @ApiParam("id") Integer id){
        ResultModel resultModel = new ResultModel();
        try {
            moduleMenuServer.deleteModuleMenuById(id);
        }catch (Exception e){
            log.error("delete moduleMenu error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "moduleMenu002"));
            }
        }
        return resultModel;
    }

    @PutMapping
    @ApiOperation("修改菜单")
    public ResultModel updateModule(@RequestParam("id") Integer id, @RequestParam("newMenuName") String newMenuName){
        ResultModel resultModel = new ResultModel();
        try {
            moduleMenuServer.updateModuleMenu(id, newMenuName);
        }catch (Exception e){
            log.error("update moduleMenu error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "moduleMenu003"));
            }
        }
        return resultModel;
    }

    @GetMapping("/fuzzy/{menu_name}")
    @ApiOperation("模糊查询菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menu_name", value = "菜单名称", required = true, dataType = "String", paramType = "query")})
    public ResultModel selectMenuByFuzzyName(@PathVariable("menu_name") @ApiParam("menu_name") String menuName){
        ResultModel resultModel = new ResultModel();
        try {
            List<ModuleMenu> menus = moduleMenuServer.selectMenuByFuzzyName(menuName);
            resultModel.setContent(menus);
        }catch (Exception e){
            log.error("select module by fuzzy error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "moduleMenu004"));
            }
        }
        return resultModel;
    }

    @GetMapping("/{id}")
    @ApiOperation("查询具体菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String", paramType = "query")})
    public ResultModel selectMenuById(@PathVariable("id") @ApiParam("id") Integer id){
        ResultModel resultModel = new ResultModel();
        try {
            ModuleMenu menu = moduleMenuServer.selectMenuById(id);
            resultModel.setContent(menu);
        }catch (Exception e){
            log.error("select module by id error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "moduleMenu007"));
            }
        }
        return resultModel;
    }

    @GetMapping("/module/{id}")
    @ApiOperation("通过模块id查询菜单树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "String", paramType = "query")})
    public ResultModel selectMenuByModuleId(@PathVariable("id") @ApiParam("id") Integer moduleId){
        ResultModel resultModel = new ResultModel();
        try {
            List<ModuleMenu> menus = moduleMenuServer.selectByModuleId(moduleId);
            resultModel.setContent(menus);
        }catch (Exception e){
            log.error("select module by id error: {}", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "moduleMenu007"));
            }
        }
        return resultModel;
    }


}
