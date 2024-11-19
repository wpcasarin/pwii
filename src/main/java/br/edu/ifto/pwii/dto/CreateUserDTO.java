package br.edu.ifto.pwii.dto;

import br.edu.ifto.pwii.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    private UserType type;
    private String email;
    private String phone;
    // B2C
    private String cpf;
    private String name;
    // B2B
    private String cnpj;
    private String businessName;
}
