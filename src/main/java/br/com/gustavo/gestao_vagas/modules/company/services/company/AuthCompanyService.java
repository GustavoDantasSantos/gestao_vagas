package br.com.gustavo.gestao_vagas.modules.company.services.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.gustavo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.gustavo.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gustavo.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyService {

    @Value("${secutiry.token.secret}")
    private String secretKey;
    
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public String execute(AuthCompanyDTO companyDTO) throws Exception{
        CompanyEntity companyExists = this.repository.findByUsername(companyDTO.getUsername());

        if(companyExists != null) {
            throw new Exception("Empresa já existe");
        }

        var passwordMatches = this.encoder.matches(companyDTO.getPassword(), companyExists.getPassword());

        if(!passwordMatches) {
            throw new Exception("Usuario/Senha são diferentes");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String token = JWT.create().withIssuer("javagas").withSubject(companyExists.getId().toString()).sign(algorithm);

        return token;
    }
}
