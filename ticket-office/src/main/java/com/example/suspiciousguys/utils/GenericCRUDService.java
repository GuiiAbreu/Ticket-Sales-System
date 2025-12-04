package com.example.suspiciousguys.utils;

import java.util.List;
import java.util.UUID;

public interface GenericCRUDService<T> {
    T findById(UUID id);
    List<T> findAll();
    T save(T entity);
    void delete(T entity);
    void delete(UUID id);

}
