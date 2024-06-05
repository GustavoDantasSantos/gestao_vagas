package br.com.gustavo.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gustavo.gestao_vagas.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private JWTProvider jwtProvider;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
        ) throws ServletException, IOException {

            SecurityContextHolder.getContext().setAuthentication(null);
            String header = request.getHeader("Authorization");

            if(header != null) {
                var subjectToken = this.jwtProvider.validateToken(header);

                if(subjectToken.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("companyId", subjectToken);

                UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    
}
