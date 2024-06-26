package br.com.gustavo.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gustavo.gestao_vagas.providers.JWTProviders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private JWTProviders jwtProvider;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
        ) throws ServletException, IOException {

            // SecurityContextHolder.getContext().setAuthentication(null);
            String header = request.getHeader("Authorization");
            
            if(request.getRequestURI().startsWith("/company")){
                if(header != null) {
                    var token = this.jwtProvider.validateToken(header);
    
                    if(token == null) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
    
                    request.setAttribute("companyId", token.getSubject());
                    var roles = token.getClaim("roles").asList(Object.class);

                    var grants = roles.stream().map( role ->  new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())).toList();
    
                    UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
    
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    
}
