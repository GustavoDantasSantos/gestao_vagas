package br.com.gustavo.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTProviders {

    @Value("${secutiry.token.secret}")
    private String secretKey;

    @Value("${secutiry.token.secret.candidate}")
    private String secretKeyCandidate;

    public DecodedJWT validateToken(String tokenRecived) {
        String token = tokenRecived.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        try {
            DecodedJWT tokenDecodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);
            return tokenDecodedJWT;
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public DecodedJWT validateCandidateToken(String tokenRecived) {
        String token = tokenRecived.replace("Bearer", "");
        Algorithm algorithm = Algorithm.HMAC256(this.secretKeyCandidate);

        try {
            DecodedJWT tokenDecodedJWT = JWT.require(algorithm)
                .build()
                .verify(token);
            
            return tokenDecodedJWT;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
