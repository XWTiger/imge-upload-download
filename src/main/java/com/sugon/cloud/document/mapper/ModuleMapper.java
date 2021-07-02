package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.Module;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleMapper {
    /**
     * 创建顶部模块
     * @param module 顶部模块实体类
     */
    void addModule(Module module);
}
