package br.jus.tse.administrativa.contato.rest;


import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.Endereco;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequest {

    @NotNull(message = "O ID não pode ser nulo.")
    private Long id;
    
    @NotNull(message = "O cep não pode ser nulo.")
    private String cep;
    
    @NotNull(message = "O logradouro não pode ser nulo.")
    private String logradouro;
    
    @NotNull(message = "O complemento não pode ser nulo.")
    private String complemento;
    
    public Endereco trasforma(ContatoPessoal dono){
        Endereco endereco = new Endereco(this.cep,this.logradouro,this.complemento, dono); 
        return endereco;
    }
    public Endereco trasforma(Long id,ContatoPessoal dono){
        Endereco endereco = new Endereco(id,this.cep,this.logradouro,this.complemento, dono); 
        return endereco;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    @Override
    public String toString() {
        return "EnderecoRequest [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento="
                + complemento + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(id,cep,logradouro,complemento);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnderecoRequest other = (EnderecoRequest) obj;
        return Objects.equals(this.complemento, other.complemento) && Objects.equals(this.logradouro, other.logradouro) && Objects.equals(this.cep, other.cep) && Objects.equals(this.id, other.id);
    }
    
    public Set<String>isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        
        Set<ConstraintViolation<EnderecoRequest>>violacoes = validator.validate(this, stage);
        
        if (violacoes.isEmpty()) {
            return Collections.emptySet();
        }
        
        Set<String> violations = new HashSet<>() ;
        violacoes.forEach((violacao)->{
            violations.add(violacao.getMessageTemplate());
        });
        
        return violations;
    }
}
