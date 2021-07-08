package com.sugon.cloud.document.server.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.entity.ModuleMenu;
import com.sugon.cloud.document.entity.page.PageCL;
import com.sugon.cloud.document.mapper.ModuleMapper;
import com.sugon.cloud.document.mapper.ModuleMenuMapper;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleMenuServer;
import com.sugon.cloud.document.server.ModuleServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Service
public class ModuleServerImpl implements ModuleServer {

    private Logger logger = LoggerFactory.getLogger(ModuleServerImpl.class);

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private ModuleMenuMapper moduleMenuMapper;

    @Autowired
    private ModuleMenuServer moduleMenuServer;

    @Autowired
    private ExceptionService exceptionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) throws Exception {
        // 首先判断名称不能为空
        if (Strings.isNullOrEmpty(module.getName())){
            throw new Exception(exceptionService.errorMessage("", "module006"));
        }
        // 再判断该模块名称是否已经被使用
        if (!Strings.isNullOrEmpty(moduleMapper.selectModuleNameByName(module.getName()))){  // 若是含有该名称
            throw new Exception(exceptionService.errorMessage("", "module005"));
        } else {  // 否则添加该模块
            moduleMapper.addModule(module);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModuleById(Integer id) {
        //1、先删除模块下方目录
        List<Integer> firstMenuIds = moduleMenuServer.getFirstMenuIdsByModuleId(id);    //先得到第一级菜单
        for (Integer firstMenuId : firstMenuIds) {
            moduleMenuServer.deleteModuleMenuById(firstMenuId);  // 删除模块下面菜单
        }

        // 2、删除模块
        moduleMapper.deleteModuleById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) throws Exception {
        // 首先判断名字不能为空
        if (Strings.isNullOrEmpty(module.getName())){
            throw new Exception(exceptionService.errorMessage("", "module006"));
        }
        // 再判断该模块名称是否已经被使用，其他的module是否含有该名称
        if (!Strings.isNullOrEmpty(moduleMapper.selectModuleNameByNameInOrtherModule(module.getName(), module.getId()))){  // 若是含有该名称
            throw new Exception(exceptionService.errorMessage("", "module005"));
        } else {  // 否则更新该模块
            moduleMapper.updateModule(module);
        }
    }

    @Override
    public List<Module> selectModuleByFuzzyName(String moduleName) {
        // 查询模块时需要得到模块下的菜单
        List<Module> modules = moduleMapper.selectModuleByFuzzyName(moduleName);
        for (Module module : modules) {
            Integer moduleId = module.getId();
            // 得到该模块下所有菜单
            List<ModuleMenu> moduleMenus = moduleMenuMapper.selectFirstMenusByModuleId(moduleId);
            module.setMenus(moduleMenus);
        }
        return modules;
    }

    @Override
    public Module selectModuleByName(String moduleName) {
        return moduleMapper.selectModuleByName(moduleName);
    }

    @Override
    public PageCL<Module> selectModules(int pageSize, int pageNum, String name) {

        // 查询模块时需要得到模块下的菜单
        List<Module> modules = moduleMapper.selectModules(name);
        for (Module module : modules) {
            Integer moduleId = module.getId();
            // 得到该模块下所有菜单以及其子菜单
            List<ModuleMenu> moduleMenus = moduleMenuMapper.selectFirstMenusByModuleId(moduleId);
            module.setMenus(moduleMenus);
        }
        PageHelper.startPage(pageSize, pageNum);
        //分页信息
        PageInfo<Module> pageInfo = new PageInfo<>(modules);
        PageCL<Module> pageCL = new PageCL();
        pageCL.setPageCount(Integer.valueOf(pageInfo.getPages()));
        pageCL.setTotal(Integer.parseInt(String.valueOf(pageInfo.getTotal())));
        pageCL.setPageNum(pageNum);
        pageCL.setPageSize(pageSize);
        pageCL.setSize(pageSize);
        pageCL.setList(pageInfo.getList());
        return pageCL;
    }

    @Override
    public Module selectModuleById(Integer id) {
        Module module = moduleMapper.selectModuleById(id);
        Integer moduleId = module.getId();
        // 得到该模块下所有菜单
        List<ModuleMenu> moduleMenus = moduleMenuMapper.selectFirstMenusByModuleId(moduleId);
        module.setMenus(moduleMenus);
        return module;
    }


}
