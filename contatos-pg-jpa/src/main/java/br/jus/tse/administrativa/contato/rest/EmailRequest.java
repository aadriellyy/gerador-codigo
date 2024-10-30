package br.jus.tse.administrativa.contato.rest;


import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.Email;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class EmailRequest {


    @NotNull(message = "O ID não pode ser nulo.")
    private Long id;

    //@Email(message = "O email deve ser válido.")
    @NotNull(message = "O email não pode ser nulo.")
    private String email;

    public Email transforma(ContatoPessoal dono){
        Email email = new Email(this.id, this.email, dono); 
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailDTO [id=" + id + ", email=" + email + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailRequest other = (EmailRequest) obj;
        return Objects.equals(this.email, other.email) && Objects.equals(this.id, other.id);
    }
    
    public Set<String>isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        
        Set<ConstraintViolation<EmailRequest>>violacoes = validator.validate(this, stage);
        
        if (violacoes.isEmpty()) {
            return Collections.emptySet();
        }
        
        Set<String> violations = new HashSet<>() ;
        violacoes.forEach((violacao)->{
            violations.add(violacao.getMessageTemplate());
        });
        
        return violations;
    }

    public String logString() {
        //throw new UnsupportedOperationException("Adicione uma implmenentação adequada.");
        return ("Adicione uma implmenentação adequada.");
    }
}
