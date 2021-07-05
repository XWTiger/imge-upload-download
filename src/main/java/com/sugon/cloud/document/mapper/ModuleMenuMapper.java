package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.Module;
import com.sugon.cloud.document.entity.ModuleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: chenxi
 * @create: 2021-07-02 17:22
 **/
@Repository
public interface ModuleMenuMapper {
    /**
     * 查询该模块下是否含有同名菜单
     * @param menuName 菜单名称
     * @param moduleId 模块id
     * @return 若是存在返回菜单名称，不存在就返回空
     */
    String selectMenuByNameInModule(@Param("menuName") String menuName, @Param("moduleId") Integer moduleId);

    /**
     * 保存菜单
     * @param moduleMenu 需要保存的菜单
     */
    void addModuleMenu(ModuleMenu moduleMenu);

    /**
     * 根据id删除菜单，需要删除其子菜单
     * @param id 菜单id
     */
    void deleteModuleMenuById(Integer id);

    /**
     * 判断该名称是否与其他菜单重名
     * @param newMenuName 新名称
     * @param menuId 菜单id
     * @return 是否重名
     */
    String selectMenuNameByNameInOtherModule(@Param("newMenuName") String newMenuName, @Param("menuId") Integer menuId);

    /**
     * 根据id修改新名称
     * @param id id
     * @param newMenuName 新名称
     */
    void updateModuleMenuById(@Param("id") Integer id, @Param("newMenuName") String newMenuName);

    /**
     * 模糊查询菜单
     * @param menuName 菜单名称
     * @return  菜单列表
     */
    List<ModuleMenu> selectMenuByFuzzyName(String menuName);

    /**
     * 根据id查询具体菜单
     * @param id id
     * @return 具体菜单
     */
    ModuleMenu selectMenuById(Integer id);

    /**
     * 得到该模块下所有菜单
     * @param moduleId 模块id
     * @return 所有菜单
     */
    List<ModuleMenu> selectFirstMenusByModuleId(Integer moduleId);

    /**
     * 根据父id查询菜单
     * @param parentId 父id
     * @return 具体菜单
     */
    ModuleMenu selectModuleByParentId(Integer parentId);

    /**
     * 批量删除所有菜单
     * @param deleteIds
     */
    void deleteModuleMenusByIds(@Param("deleteIds") List<Integer> deleteIds);

    /**
     * 通过模块id得到所有的第一级菜单id
     * @param moduleId 模块id
     * @return 第一次菜单id
     */
    List<Integer> getFirstMenuByModuleId(Integer moduleId);
}
