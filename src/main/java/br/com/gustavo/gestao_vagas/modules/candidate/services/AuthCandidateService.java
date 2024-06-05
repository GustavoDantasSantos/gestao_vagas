package br.com.gustavo.gestao_vagas.modules.candidate.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.gustavo.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gustavo.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gustavo.gestao_vagas.modules.candidate.repository.CandidateRepository;

public class AuthCandidateService {

    @Value("${secutiry.token.secret.candidate}")
    private String secretKey;
    
    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO candidateRequestDTO) throws AuthenticationException {
        var candidateAlredyExists = this.repository.findByUsername(candidateRequestDTO.username())
            .orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Usuario/senha incorretas");
                }
            );
        
        Boolean passwordMatchers = encoder.matches(candidateRequestDTO.password(), candidateAlredyExists.getPassword());

        if(!passwordMatchers) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var token = JWT.create()
            .withIssuer("javagas")
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withClaim("roles", Arrays.asList("candidate"))
            .withSubject(candidateAlredyExists.getId().toString())
            .sign(algorithm);
        
        return AuthCandidateResponseDTO.builder().acessToken(token).build();
        
    };
}
