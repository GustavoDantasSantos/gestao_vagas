package br.com.gustavo.gestao_vagas.modules.company.controlleries.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import br.com.gustavo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gustavo.gestao_vagas.modules.company.services.company.CreateCompanyService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/company")
public class CreateCompanyController {

    @Autowired
    private CreateCompanyService service;

    @PostMapping("/")
    public ResponseEntity<Object> handle(@Valid @RequestBody CompanyEntity entity) {
        try {
            var savedCompany = this.service.execute(entity);
            return ResponseEntity.ok().body(savedCompany);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
