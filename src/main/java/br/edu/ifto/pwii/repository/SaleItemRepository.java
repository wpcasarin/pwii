package br.edu.ifto.pwii.repository;

import br.edu.ifto.pwii.model.Product;
import br.edu.ifto.pwii.model.SaleItem;

public interface SaleItemRepository extends BaseRepository<SaleItem, Long> {
    boolean existsSaleItemByProductAndDeletedAtIsNull(Product entity);
}
