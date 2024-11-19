package br.edu.ifto.pwii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDTO {
    private Long id;
    private String email;
    private String phone;
    // B2C
    private String cpf;
    private String name;
    // B2B
    private String cnpj;
    private String businessName;
}
