package br.edu.ifto.pwii.repository;

import br.edu.ifto.pwii.model.BaseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    @Transactional
    default void softDelete(T entity) {
        entity.softDelete();
        save(entity);
    }
}