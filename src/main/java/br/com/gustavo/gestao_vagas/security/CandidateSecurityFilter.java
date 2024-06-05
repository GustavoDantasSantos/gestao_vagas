package br.com.gustavo.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.gustavo.gestao_vagas.providers.JWTProviders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CandidateSecurityFilter extends OncePerRequestFilter {

    @Autowired
    JWTProviders jwtProviders;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("candidate")) {
            if(header != null) {
                DecodedJWT token = this.jwtProviders.validateCandidateToken(header);
    
                if(token != null) {
                    request.setAttribute("candidateId", token.getSubject());
                    var roles = token.getClaim("roles").asList(Object.class);

                    var grants = roles.stream().map( role ->  new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())).toList();
                    
                    UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
    
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
    
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
    
}
