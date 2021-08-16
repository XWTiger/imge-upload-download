package com.sugon.cloud.document.mapper;

import com.sugon.cloud.document.entity.CatalogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogMapper {

    void addCatalog(CatalogEntity catalogEntity);

    List<CatalogEntity> list(String name);

    void deleteCatalog(Integer id);

    void updateCatalog(CatalogEntity catalogEntity);

    CatalogEntity selectCatalogByName(String name);
}
