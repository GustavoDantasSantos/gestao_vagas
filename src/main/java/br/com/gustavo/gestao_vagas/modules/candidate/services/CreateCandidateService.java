package br.com.gustavo.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gustavo.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.gustavo.gestao_vagas.modules.candidate.repository.CandidateRepository;

public class CreateCandidateService {
    
    @Autowired
    private CandidateRepository repository;

    public void execute(CandidateEntity candidate) throws Exception {
        var candidateFound = this.repository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail());
        if(candidateFound == null) {
            throw new Exception("Usuário não existe");
        }

        this.repository.save(candidate);
    }
}
