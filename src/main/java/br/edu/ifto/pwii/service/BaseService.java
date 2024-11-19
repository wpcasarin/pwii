package br.edu.ifto.pwii.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    T create(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    T update(ID id, T entity, T updatedEntity);

    void delete(ID id);

    long count();
}