package br.edu.ifto.pwii.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("deleted_at IS NULL")
public class Sale extends BaseEntity {

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BigDecimal totalPrice() {
        return items.stream()
                .map(SaleItem::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
