package com.sugon.cloud.document.server;

import com.sugon.cloud.document.entity.CatalogEntity;

import java.util.List;

public interface CatalogService {
    void addCatalog(CatalogEntity catalogEntity);

    List<CatalogEntity> list(String name);

    void deleteCatalog(Integer id);

    void updateCatalog(CatalogEntity catalogEntity);
}
