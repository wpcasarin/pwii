package br.edu.ifto.pwii.repository;

import br.edu.ifto.pwii.model.BaseEntity;
import br.edu.ifto.pwii.model.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends BaseRepository<Sale, Long> {

    default void softDelete(Sale entity) {
        var items = entity.getItems();
        items.forEach(BaseEntity::softDelete);
        entity.softDelete();
        save(entity);
    }
}
