package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.Content;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.server.ContentService;
import com.sugon.cloud.document.server.impl.ExceptionServiceImpl;
import com.sugon.cloud.document.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "文档内容接口")
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private ExceptionServiceImpl exceptionService;

    @ApiOperation("添加文档内容")
    @PostMapping
    public ResultModel addContent(@RequestBody Content content) {

        ResultModel resultModel = new ResultModel();
        resultModel.setStatusMes("保存成功");
        try {
            contentService.addContent(content);
        } catch (Exception e) {
            log.error("add content error:", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())) {
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "fileContent001"));
            }
        }
        return resultModel;
    }

    @ApiOperation("删除文档内容")
    @DeleteMapping("/file/{file_id}")
    public ResultModel deleteContent(@PathVariable("file_id") String fileId) {

        ResultModel resultModel = new ResultModel();
        try {
            contentService.delete(Integer.valueOf(fileId));
        } catch (Exception e) {
            log.error("delete content error:", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())) {
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "fileContent002"));
            }
        }
        return resultModel;
    }

    @ApiOperation("根据菜单id查询文档内容")
    @GetMapping("/menu/{menu_id}")
    public ResultModel queryContentByModelMenuId(@PathVariable("menu_id") String menuId) {

        ResultModel resultModel = new ResultModel();
        try {
           resultModel = contentService.getByModelMenuId(Integer.valueOf(menuId));
        } catch (Exception e) {
            log.error("query content error: ", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())) {
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "fileContent003"));
            }
        }
        return resultModel;
    }

    @ApiOperation("根据文件id查询文档内容")
    @GetMapping("/{file_id}")
    public ResultModel queryContentById(@PathVariable("file_id") String fileId) {

        ResultModel resultModel = new ResultModel();
        try {
           resultModel = contentService.getById(Integer.valueOf(fileId));
        } catch (Exception e) {
            log.error("query content error:", e);
            resultModel.setStatusCode(0);
            if (CommonUtils.isContainChinese(e.getMessage())) {
                resultModel.setStatusMes(e.getMessage());
            } else {
                resultModel.setStatusMes(exceptionService.errorMessage("", "fileContent003"));
            }
        }
        return resultModel;
    }

}
