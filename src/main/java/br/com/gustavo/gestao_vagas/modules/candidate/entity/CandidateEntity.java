package br.com.gustavo.gestao_vagas.modules.candidate.entity;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;

public class CandidateEntity {
    private UUID id;
    private String name;
    private String username;
    
    @Email
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String description;
    private String curriculum;

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCurriculum() {
        return this.curriculum;
    }

    public UUID getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}