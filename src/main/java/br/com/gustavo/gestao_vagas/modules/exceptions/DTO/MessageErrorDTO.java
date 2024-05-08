package br.com.gustavo.gestao_vagas.modules.exceptions.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageErrorDTO {
    private String message;
    private String field;
}
