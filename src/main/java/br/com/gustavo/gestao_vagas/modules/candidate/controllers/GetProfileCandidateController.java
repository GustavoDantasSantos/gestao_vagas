package br.com.gustavo.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gustavo.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gustavo.gestao_vagas.modules.candidate.services.GetProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/candidate")
public class GetProfileCandidateController {
    
    @Autowired
    private GetProfileCandidateService service;

    public ResponseEntity<Object> getProfile(HttpServletRequest request){
        var candidateId = request.getAttribute("candidateId");
        try {
            ProfileCandidateResponseDTO candidate = this.service.getProfile(
                UUID.fromString(candidateId.toString())
            );
            return ResponseEntity.ok().body(candidate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
