package br.com.gustavo.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.gustavo.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gustavo.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class GetProfileCandidateService {

    @Autowired
    private CandidateRepository repository;

    public ProfileCandidateResponseDTO getProfile(UUID candidateId) {
        var candidate = this.repository.findById(candidateId)
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("usuario não existe no banco de dados");
        });

        ProfileCandidateResponseDTO candidateDTO = ProfileCandidateResponseDTO.builder()
            .id(candidateId)    
            .name(candidate.getName())
            .email(candidate.getEmail())
            .username(candidate.getUsername())
            .description(candidate.getDescription())
            .build();

        return candidateDTO;
    }
}
