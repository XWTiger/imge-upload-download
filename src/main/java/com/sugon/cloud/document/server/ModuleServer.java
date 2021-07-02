package com.sugon.cloud.document.server;

import com.sugon.cloud.document.entity.Module;

public interface ModuleServer {
    /**
     * 创建顶部模块
     * @param module 模块实体类
     */
    void addModule(Module module);
}
