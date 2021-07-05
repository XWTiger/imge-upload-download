package com.sugon.cloud.document.server.impl;

import com.sugon.cloud.document.entity.Content;
import com.sugon.cloud.document.entity.model.ResultModel;
import com.sugon.cloud.document.server.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {
    @Override
    public void addContent(Content content) {

    }

    @Override
    public ResultModel getByModelMenuId(Integer moduleMenuId) {
        return null;
    }

    @Override
    public ResultModel getById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Content content) {

    }
}
