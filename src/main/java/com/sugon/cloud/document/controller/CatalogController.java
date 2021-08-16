package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.CatalogEntity;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.server.CatalogService;
import com.sugon.cloud.document.server.impl.ExceptionServiceImpl;
import com.sugon.cloud.document.utils.CommonUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "文档种类接口")
@RestController
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

    @Autowired
    private ExceptionServiceImpl exceptionService;

    @Autowired
    private CatalogService catalogService;




    @PostMapping
    @ApiOperation("添加模块")
    public ResultModel createModule(@RequestBody @ApiParam(name = "catalog") CatalogEntity catalogEntity){
        ResultModel resultModel = new ResultModel();
        try {
            catalogService.addCatalog(catalogEntity);
        }catch (Exception e){
            log.error("add module error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "catalog002"));
            }
        }
        return resultModel;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除模块")
    public ResultModel deleteModule(@PathVariable("id") @ApiParam("id") Integer id){
        ResultModel resultModel = new ResultModel();
        try {
            catalogService.deleteCatalog(id);
        }catch (Exception e){
            log.error("delete module error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "catalog004"));
            }
        }
        return resultModel;
    }

    @PutMapping
    @ApiOperation("修改文档种类")
    public ResultModel update(@RequestBody @ApiParam(name = "module") CatalogEntity catalogEntity){
        ResultModel resultModel = new ResultModel();
        try {
            catalogService.updateCatalog(catalogEntity);
        }catch (Exception e){
            log.error("update module error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "catalog003"));
            }
        }
        return resultModel;
    }



    @GetMapping("/all")
    @ApiOperation("查询所有种类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "Integer", paramType = "query")
    })
    public ResultModel selectModule( @RequestParam(value = "name", required = false) String name){
        ResultModel resultModel = new ResultModel();
        try {
            List<CatalogEntity> catalogs = catalogService.list(name);
            resultModel.setContent(catalogs);
        }catch (Exception e){
            log.error("select all module error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())){
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "catalog005"));
            }
        }
        return resultModel;
    }

}
