package com.sugon.cloud.document.server;


import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.entity.ModuleMenu;

import java.util.List;

public interface ModuleMenuServer {
    /**
     * 添加菜单
     * @param moduleMenu 需要添加的菜单
     */
    Integer addModuleMenu(ModuleMenu moduleMenu) throws Exception;

    /**
     * 根据id删除菜单，需要删除其子菜单
     * @param id 菜单id
     */
    void deleteModuleMenuById(Integer id);

    /**
     * 更新菜单
     * @param id 菜单id
     * @param newMenuName 新菜单名称
     */
    void updateModuleMenu(Integer id, String newMenuName) throws Exception;

    /**
     * 模糊查询菜单
     * @param menuName 模糊名称
     * @return 菜单列表
     */
    List<ModuleMenu> selectMenuByFuzzyName(String menuName);

    /**
     * 根据id查询具体菜单
     * @param id id
     * @return 具体菜单
     */
    ModuleMenu selectMenuById(Integer id);

    /**
     * 通过模块id得到所有的第一级菜单id
     * @param moduleId 模块id
     * @return 第一次菜单id
     */
    List<Integer> getFirstMenuIdsByModuleId(Integer moduleId);

    /**
     * 通过模块id查询菜单树
     * @param moduleId
     * @return
     */
    List<ModuleMenu> selectByModuleId(Integer moduleId);
}
