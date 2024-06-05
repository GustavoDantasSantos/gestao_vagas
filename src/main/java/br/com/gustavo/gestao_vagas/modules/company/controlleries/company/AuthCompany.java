package br.com.gustavo.gestao_vagas.modules.company.controlleries.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gustavo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gustavo.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.gustavo.gestao_vagas.modules.company.services.company.AuthCompanyService;

@RequestMapping("/company")
@Controller
public class AuthCompany {
    @Autowired
    private AuthCompanyService service;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO companyDTO){
        try {
            AuthCompanyResponseDTO token = this.service.execute(companyDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    };
}
