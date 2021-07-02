package com.sugon.cloud.document.server.impl;

import com.sugon.cloud.document.server.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String errorMessage(String prefixMsg, String errorCode) {
        String suffixMsg = messageSource.getMessage(errorCode,null, null);
        String errorMsg = prefixMsg + suffixMsg;
        return errorMsg;
    }
}
