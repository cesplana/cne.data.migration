package com.cne.data.migration.service;

import java.util.Set;

public interface BaseDataLoaderService<T_POJO, T_DOC> {
    Set<T_POJO> extractData();
    Set<T_DOC> transformData(Set<T_POJO> pojoSet);
    Long loadData(Set<T_DOC> documents);
}
