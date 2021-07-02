package com.sugon.cloud.document.server.impl;

import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.mapper.ModuleMapper;
import com.sugon.cloud.document.server.ModuleServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModuleServerImpl implements ModuleServer {

    private Logger logger = LoggerFactory.getLogger(ModuleServerImpl.class);

    @Autowired
    private ModuleMapper moduleMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) {
        moduleMapper.addModule(module);
    }
}
