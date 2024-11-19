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
@DiscriminatorValue("B2C")
@EqualsAndHashCode(callSuper = true)
public class UserB2C extends User {

    private String cpf;

    private String name;

}
