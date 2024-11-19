package br.edu.ifto.pwii.service;

import br.edu.ifto.pwii.model.Product;
import br.edu.ifto.pwii.repository.BaseRepository;
import br.edu.ifto.pwii.repository.ProductRepository;
import br.edu.ifto.pwii.repository.SaleItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseServiceImpl<Product, Long> {

    public ProductService(BaseRepository<Product, Long> repository, ProductRepository productRepository, SaleItemRepository saleItemRepository) {
        super(repository);
        this.productRepository = productRepository;
        this.saleItemRepository = saleItemRepository;
    }

    private final ProductRepository productRepository;
    private final SaleItemRepository saleItemRepository;
    
    public void delete(Long id) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
        boolean hasSaleItems = saleItemRepository.existsSaleItemByProductAndDeletedAtIsNull(entity);
        if (hasSaleItems) {
            throw new RuntimeException("Cannot delete product as it is associated with sale items");
        }
        productRepository.softDelete(entity);

    }
}
