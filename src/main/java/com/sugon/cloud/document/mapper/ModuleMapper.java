package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.Module;
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
}
