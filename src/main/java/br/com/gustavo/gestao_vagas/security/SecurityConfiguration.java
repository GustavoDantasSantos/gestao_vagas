package br.com.gustavo.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter filter;

    @Autowired
    private CandidateSecurityFilter candidateSecurityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests( auth -> {
                auth.requestMatchers("company").permitAll();
                auth.requestMatchers("/candidate").permitAll();
                auth.requestMatchers("/company/auth").permitAll();
                auth.requestMatchers("/candidate/auth").permitAll();
                auth.anyRequest().authenticated();
            })

            .addFilterBefore(candidateSecurityFilter, BasicAuthenticationFilter.class)
            .addFilterBefore(filter, BasicAuthenticationFilter.class);

        return httpSecurity.build();
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
