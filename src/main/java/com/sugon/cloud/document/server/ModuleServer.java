package com.sugon.cloud.document.server;

import com.sugon.cloud.document.entity.Module;

import java.util.List;

public interface ModuleServer {
    /**
     * 创建顶部模块
     * @param module 模块实体类
     */
    void addModule(Module module) throws Exception;

    /**
     * 删除顶部模块
     * @param id 模块id
     */
    void deleteModuleById(Integer id);

    /**
     * 更新顶部模块
     * @param module 新模块实体类
     */
    void updateModule(Module module) throws Exception;

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
    List<Module> selectModules();

    /**
     * 查询具体模块
     * @param id 具体模块id
     * @return 具体模块
     */
    Module selectModuleById(Integer id);
}
