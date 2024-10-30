package br.jus.tse.administrativa.contato.rest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContatoPessoalRequest {
    
    //@NotNull(groups = {ValidOnUpdate.class}, message = "Id de Contato Pessoal está inválido.")
    @JsonProperty("id")
    private Long id;
    
    
    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "Nome de Contato Pessoal está inválido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "Nome de Contato Pessoal está em branco")
    @JsonProperty("nome_completo")
    private String nomeCompleto;
    
    
    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "CPF está inválido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "CPF de Contato Pessoal está em branco")
    @JsonProperty("cpf")
    private String cpf;
    
    
    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "Data de Nascimento está inválido.")
    @JsonProperty("data_nascimento")
     @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate nascimento;

    public ContatoPessoal transforma(){
        ContatoPessoal contatoPessoal = new ContatoPessoal(this.nomeCompleto,this.cpf,this.nascimento);
        return contatoPessoal;
    }

    public ContatoPessoal transforma(long id){
        ContatoPessoal contatoPessoal = new ContatoPessoal(id,this.nomeCompleto,this.cpf,this.nascimento);
        return contatoPessoal;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNomeCompleto() {
        return this.nomeCompleto;
    }


    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }


    public String getCpf() {
        return this.cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public LocalDate getNascimento() {
        return this.nascimento;
    }


    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
    public Set<String>isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        
        Set<ConstraintViolation<ContatoPessoalRequest>>violacoes = validator.validate(this, stage);
        
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
