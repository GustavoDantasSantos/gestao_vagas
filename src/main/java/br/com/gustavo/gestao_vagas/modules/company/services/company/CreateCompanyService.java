package br.com.gustavo.gestao_vagas.modules.company.services.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gustavo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gustavo.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyService {
    
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public CompanyEntity execute(CompanyEntity entity) throws Exception {
        var companyFound = this.repository.findByEmailOrUsername(entity.getEmail(), entity.getUsername());

        if(companyFound != null) {
            throw new Exception("Empresa já existe");
        }

        String password = encoder.encode(entity.getPassword());
        entity.setPassword(password);

        return this.repository.save(entity);
    }
}
