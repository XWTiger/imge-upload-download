package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.Content;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMapper {

    void addContent(Content content);

    Content getContentById(int fileId);


    Content getContentByMenuId(int menuId);

    void update(Content content);

    void delete(int fileId);
}
