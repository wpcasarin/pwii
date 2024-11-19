package br.edu.ifto.pwii.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("B2B")
@EqualsAndHashCode(callSuper = true)
public class UserB2B extends User {

    private String cnpj;
    
    private String businessName;

}
