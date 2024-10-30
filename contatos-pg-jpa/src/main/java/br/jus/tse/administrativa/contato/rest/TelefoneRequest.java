package br.jus.tse.administrativa.contato.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.Telefone;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;


public class TelefoneRequest {
    
    @NotNull(message = "O ID não pode ser nulo.")
    private Long id;
    
    
    @NotNull(message = "O telefone não pode ser nulo.")
    private String telefone;

    public Telefone transforma(ContatoPessoal dono){
        Telefone telefone = new Telefone(this.id,this.telefone, dono); 
        return telefone;
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTelefone() {
        return this.telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "TelefoneRequest [id=" + id + ", telefone=" + telefone + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telefone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TelefoneRequest other = (TelefoneRequest) obj;
        return Objects.equals(this.telefone, other.telefone) && Objects.equals(this.id, other.id);
    }

    public Set<String>isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        
        Set<ConstraintViolation<TelefoneRequest>>violacoes = validator.validate(this, stage);
        
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
