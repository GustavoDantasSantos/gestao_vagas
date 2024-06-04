package br.com.gustavo.gestao_vagas.modules.company.controlleries.job;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gustavo.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.gustavo.gestao_vagas.modules.company.entities.JobEntity;
import br.com.gustavo.gestao_vagas.modules.company.services.job.CreateJobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/job")
public class CreateJobController {

    @Autowired
    private CreateJobService  service;

    public ResponseEntity<Object> handle(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        try {
            var companyId = request.getAttribute("companyId");

            var entity =JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .level(createJobDTO.getLevel())
                .description(createJobDTO.getDescription())
                .companyId(UUID.fromString(companyId.toString()))
                .build();

            var savedJob = this.service.execute(entity);
            
            return ResponseEntity.ok().body(savedJob);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
