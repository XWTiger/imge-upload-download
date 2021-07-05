package com.sugon.cloud.document.server.impl;

import com.google.common.base.Strings;
import com.sugon.cloud.document.entity.ModuleMenu;
import com.sugon.cloud.document.mapper.ModuleMenuMapper;
import com.sugon.cloud.document.server.ContentService;
import com.sugon.cloud.document.server.ExceptionService;
import com.sugon.cloud.document.server.ModuleMenuServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ModuleMenuServerImpl implements ModuleMenuServer {

    private Logger logger = LoggerFactory.getLogger(ModuleMenuServerImpl.class);

    @Autowired
    private ModuleMenuMapper moduleMenuMapper;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ExceptionService exceptionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModuleMenu(ModuleMenu moduleMenu) throws Exception {
        // 首先判断需要添加的菜单名是否为空
        if (Strings.isNullOrEmpty(moduleMenu.getName())){
            throw new Exception(exceptionService.errorMessage("", "moduleMenu006"));
        }
        // 再判断在该模块下是否含有同名菜单
        if (!Strings.isNullOrEmpty(
                moduleMenuMapper.selectMenuByNameInModule(moduleMenu.getName(), moduleMenu.getModuleId()))) {
            throw new Exception(exceptionService.errorMessage("", "moduleMenu005"));
        } else {
            // 没有则保存
            moduleMenuMapper.addModuleMenu(moduleMenu);
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModuleMenuById(Integer id) {
        List<Integer> deleteIds = new ArrayList<>();  // 保存需要删除的菜单id
        deleteIds.add(id);
        ModuleMenu moduleMenu = this.selectMenuById(id);  // 得到所有的菜单
        getDeleteIds(deleteIds, moduleMenu.getModuleMenus());  // 得到所有需要删除的菜单id
        // 首先需要删除菜单下的文档
        for (Integer deleteId : deleteIds) {
            contentService.deleteByMenuId(deleteId);
        }

        // 再删除菜单
        moduleMenuMapper.deleteModuleMenusByIds(deleteIds);
    }

    /**
     * 递归得到所有需要删除的id
     */
    private void getDeleteIds(List<Integer> deleteIds, List<ModuleMenu> moduleMenus){
        for (ModuleMenu moduleMenu : moduleMenus) {
            deleteIds.add(moduleMenu.getId());
            getDeleteIds(deleteIds, moduleMenu.getModuleMenus());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModuleMenu(Integer id, String newMenuName) throws Exception {
        // 首先判断名字是否为空
        if (Strings.isNullOrEmpty(newMenuName)){
            throw new Exception(exceptionService.errorMessage("", "moduleMenu006"));
        }
        // 再判断该菜单名称是否已经被使用，其他菜单是否含有该名称
        if (!Strings.isNullOrEmpty(moduleMenuMapper.selectMenuNameByNameInOtherModule(newMenuName, id))){
            throw new Exception(exceptionService.errorMessage("", "moduleMenu005"));
        }
        // 修改菜单
        moduleMenuMapper.updateModuleMenuById(id, newMenuName);
    }

    @Override
    public List<ModuleMenu> selectMenuByFuzzyName(String menuName) {
        return moduleMenuMapper.selectMenuByFuzzyName(menuName);
    }

    @Override
    public ModuleMenu selectMenuById(Integer id) {
        return moduleMenuMapper.selectMenuById(id);
    }

    @Override
    public List<Integer> getFirstMenuIdsByModuleId(Integer moduleId) {
        return moduleMenuMapper.getFirstMenuByModuleId(moduleId);
    }
}
