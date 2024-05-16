package br.com.gustavo.gestao_vagas.modules.company.services.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gustavo.gestao_vagas.modules.company.entities.JobEntity;
import br.com.gustavo.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobService {
    @Autowired
    private JobRepository repository;

    public JobEntity execute(JobEntity entity) {
        return this.repository.save(entity);
    }
}