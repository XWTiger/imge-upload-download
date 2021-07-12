package com.sugon.cloud.document.server.impl;


import com.sugon.cloud.document.entity.CatalogEntity;
import com.sugon.cloud.document.mapper.CatalogMapper;
import com.sugon.cloud.document.server.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private ExceptionServiceImpl exceptionService;

    @Override
    public void addCatalog(CatalogEntity catalogEntity) {
        CatalogEntity catalog = catalogMapper.selectCatalogByName(catalogEntity.getName());
        if (Objects.nonNull(catalog)) {
            exceptionService.errorMessage("", "catalog001");
        }
        catalogMapper.addCatalog(catalogEntity);
    }

    @Override
    public List<CatalogEntity> list(String name) {
        return catalogMapper.list(name);
    }

    @Override
    public void deleteCatalog(Integer id) {
        catalogMapper.deleteCatalog(id);
    }

    @Override
    public void updateCatalog(CatalogEntity catalogEntity) {
        catalogMapper.updateCatalog(catalogEntity);
    }
}
