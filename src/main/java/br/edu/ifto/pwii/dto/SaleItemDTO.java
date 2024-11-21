package br.edu.ifto.pwii.dto;

import br.edu.ifto.pwii.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemDTO {
    private Long id;
    private BigDecimal amount;
    private Product product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public BigDecimal totalPrice() {
        return product.getPrice().multiply(amount);
    }
    
}
