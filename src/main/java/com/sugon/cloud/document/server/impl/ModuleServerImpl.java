package com.sugon.cloud.document.server.impl;

import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.mapper.ModuleMapper;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ModuleServerImpl implements ModuleServer {

    private Logger logger = LoggerFactory.getLogger(ModuleServerImpl.class);

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private ExceptionService exceptionService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) throws Exception {
        // 首先判断该模块名称是否已经被使用
        if (Objects.nonNull(this.selectModuleByName(module.getName()))){  // 若是含有该名称
            throw new Exception(exceptionService.errorMessage("", "module005"));
        } else {  // 否则添加该模块
            moduleMapper.addModule(module);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModuleById(Integer id) {
        //TODO 1、先删除模块下方目录

        // 2、删除模块
        moduleMapper.deleteModuleById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) throws Exception {
        // 首先判断该模块名称是否已经被使用
        if (Objects.nonNull(this.selectModuleByName(module.getName()))){  // 若是含有该名称
            throw new Exception(exceptionService.errorMessage("", "module005"));
        } else {  // 否则更新该模块
            moduleMapper.updateModule(module);
        }
    }

    @Override
    public List<Module> selectModuleByFuzzyName(String moduleName) {
        return moduleMapper.selectModuleByFuzzyName(moduleName);
    }

    @Override
    public Module selectModuleByName(String moduleName) {
        return moduleMapper.selectModuleByName(moduleName);
    }


}
