package br.com.gustavo.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gustavo.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.gustavo.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateService {
    
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public CandidateEntity execute(CandidateEntity candidate) throws Exception {
        var candidateFound = this.repository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail());
        if(candidateFound != null) {
            throw new Exception("Usuário já existe");
        }

        var password = encoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return this.repository.save(candidate);
    }
}
