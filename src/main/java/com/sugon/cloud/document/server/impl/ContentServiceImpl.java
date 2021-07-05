package com.sugon.cloud.document.server.impl;

import com.sugon.cloud.document.entity.Content;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.mapper.ContentMapper;
import com.sugon.cloud.document.server.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void addContent(Content content) {
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
    public void update(Content content) {
        contentMapper.update(content);
    }
}
