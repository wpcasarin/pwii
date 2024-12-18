package br.edu.ifto.pwii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    private Long id;
    private List<SaleItemDTO> items;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public BigDecimal totalPrice() {
        return items.stream()
                .map(SaleItemDTO::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
