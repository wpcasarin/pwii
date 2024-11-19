package br.edu.ifto.pwii.service;

import br.edu.ifto.pwii.model.BaseEntity;
import br.edu.ifto.pwii.repository.BaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static br.edu.ifto.pwii.utils.EntityUtils.hasChanged;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T extends BaseEntity, ID> implements BaseService<T, ID> {

    private final BaseRepository<T, ID> repository;

    @Override
    @Transactional
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public T update(ID id, T entity, T updatedEntity) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Entity not found");
        }
        if (hasChanged(entity, updatedEntity)) {
            return repository.save(updatedEntity);
        }
        return entity;
    }

    @Override
    @Transactional
    public void delete(ID id) {
        T entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity with ID " + id + " not found"));
        repository.softDelete(entity);
    }

    @Override
    public long count() {
        return repository.count();
    }

}