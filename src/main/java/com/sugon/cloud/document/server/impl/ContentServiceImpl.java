package com.sugon.cloud.document.server.impl;

import com.sugon.cloud.document.entity.Content;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.mapper.ContentMapper;
import com.sugon.cloud.document.server.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ExceptionServiceImpl exceptionService;

    @Override
    public void addContent(Content content) throws Exception {
        //校验是否已经有文档内容
        Content exist = contentMapper.getContentByMenuId(content.getModelMenuId());
        if (Objects.nonNull(exist)) {
            throw new Exception(exceptionService.errorMessage("", "fileContent004"));
        }
        contentMapper.addContent(content);
    }

    @Override
    public ResultModel getByModelMenuId(Integer moduleMenuId) {
        ResultModel resultModel = new ResultModel();
        Content content = contentMapper.getContentByMenuId(moduleMenuId);
        resultModel.setContent(content);
        return resultModel;
    }

    @Override
    public ResultModel getById(Integer id) {

        ResultModel resultModel = new ResultModel();
        Content content = contentMapper.getContentById(id);
        resultModel.setContent(content);
        return resultModel;
    }

    @Override
    public void delete(Integer id) {
        contentMapper.delete(id);
    }

    @Override
    public void deleteByMenuId(Integer menuId) {
        contentMapper.deleteByMenuId(menuId);
    }

    @Override
    public void update(Content content) {
        contentMapper.update(content);
    }
}
