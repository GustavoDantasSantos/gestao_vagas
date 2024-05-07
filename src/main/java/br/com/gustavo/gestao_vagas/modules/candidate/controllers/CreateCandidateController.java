package br.com.gustavo.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.gustavo.gestao_vagas.modules.candidate.entity.CandidateEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/candidate")
public class CreateCandidateController {
    
    @PostMapping("/")
    public void create(@RequestBody CandidateEntity entity) {
        System.out.println("Candidato" + entity.getName());
    }

}
