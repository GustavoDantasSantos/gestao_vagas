package br.com.gustavo.gestao_vagas.modules.candidate.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String username;
    
    @Email
    private String email;

    @Length(min = 10, max = 100)
    private String password;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

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

    public LocalDateTime getDataTime() {
        return this.createdAt;
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

    public void setDataTime(LocalDateTime dataTime) {
        this.createdAt = dataTime;
    }
}