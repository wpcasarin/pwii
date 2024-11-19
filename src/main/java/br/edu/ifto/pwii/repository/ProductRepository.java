package br.edu.ifto.pwii.repository;

import br.edu.ifto.pwii.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
