package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMapper {

    Integer addContent(Content content);

    Content getContentById(int fileId);


    Content getContentByMenuId(int menuId);

    void update(Content content);

    void delete(int fileId);

    void deleteByMenuId(Integer menuId);
}
