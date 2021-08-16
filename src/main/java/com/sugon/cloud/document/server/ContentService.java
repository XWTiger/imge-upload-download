package com.sugon.cloud.document.server;

import com.sugon.cloud.document.entity.Content;
import com.sugon.cloud.document.entity.model.ResultModel;

public interface ContentService {
    ResultModel addContent(Content content) throws Exception;

    /**
     * 通过菜单id 查群对应得内容
     * @param moduleMenuId 菜单id
     * @return
     */
    ResultModel getByModelMenuId(Integer moduleMenuId);

    /**
     * 通过内容id 查询 内容
     * @param id
     * @return
     */
    ResultModel getById(Integer id);

    void delete(Integer id);

    void deleteByMenuId(Integer menuId);

    void update(Content content);
}
