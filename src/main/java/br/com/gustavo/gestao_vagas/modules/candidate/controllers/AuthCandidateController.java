package br.com.gustavo.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gustavo.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gustavo.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gustavo.gestao_vagas.modules.candidate.services.AuthCandidateService;

@Controller
@RequestMapping("/auth")
public class AuthCandidateController {
    @Autowired
    private AuthCandidateService service;
    
    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO requestDTO) {
        try {
            AuthCandidateResponseDTO token = this.service.execute(requestDTO);
            return ResponseEntity.ok().body(token);            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
