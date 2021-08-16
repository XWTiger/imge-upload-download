package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.Module;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {
    /**
     * 创建顶部模块
     * @param module 顶部模块实体类
     */
    void addModule(Module module);

    /**
     * 删除顶部模块
     * @param id id
     */
    void deleteModuleById(Integer id);

    /**
     * 更新顶部模块
     * @param module 新模块实体类
     */
    void updateModule(Module module);

    /**
     * 根据模块名称进行模糊查询
     * @param moduleName 模块名
     * @return 查询得到的模块列表
     */
    List<Module> selectModuleByFuzzyName(String moduleName);

    /**
     * 根据模块名称进行查询
     * @param moduleName 模块名
     * @return 查询得到的模块列表
     */
    Module selectModuleByName(String moduleName);

    /**
     * 查询所有模块
     * @return 所有模块
     */
    List<Module> selectModules(String name);

    /**
     * 查询该模块名是否已经存在
     * @param moduleName 模块名
     * @return 模块名
     */
    String selectModuleNameByName(String moduleName);

    /**
     * 查找其他module是否含有该名称
     * @param moduleName 名称
     * @param id 本moduleid
     * @return 是否含有名称
     */
    String selectModuleNameByNameInOrtherModule(@Param("moduleName") String moduleName, @Param("id") Integer id);

    /**
     * 查询具体模块
     * @param id 具体模块id
     * @return 具体模块
     */
    Module selectModuleById(Integer id);

    List<Module> selectByCatalogId(Integer catalogId);

}
