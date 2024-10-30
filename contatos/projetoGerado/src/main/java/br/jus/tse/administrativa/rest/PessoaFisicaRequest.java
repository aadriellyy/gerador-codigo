  






 /*
* Created on 2024-10-30 ( 11:31:29 )
* Generated by Telosys ( https://www.telosys.org/ ) version 4.1.0
*/ 












package br.jus.tse.administrativa.rest;


import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
     
import com.fasterxml.jackson.annotation.JsonFormat;

   
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import br.jus.tse.administrativa.PessoaFisica;


public class PessoaFisicaRequest {

     
    @NotNull(groups = {ValidOnUpdate.class}, message = "idPessoa de PessoaFisica está invalido.")
    @JsonProperty("idPessoa")
    private Long idPessoa;

             @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "nomeCompleto de PessoaFisica está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "nomeCompleto de PessoaFisica está em branco")
    @JsonProperty("nomeCompleto")
    private String nomeCompleto;

         
    @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "dtNascimento de PessoaFisica está invalido.")
    @JsonProperty("dtNascimento")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dtNascimento;

             @NotNull(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "nrCpf de PessoaFisica está invalido.")
    @NotBlank(groups = {ValidOnCreation.class, ValidOnUpdate.class}, message = "nrCpf de PessoaFisica está em branco")
    @JsonProperty("nrCpf")
    private String nrCpf;

    

    public PessoaFisica transform( String nomeCompleto,   LocalDate dtNascimento,    String nrCpf  ){
        PessoaFisica object = new PessoaFisica(     nomeCompleto,       dtNascimento,    nrCpf  );
        return object;
    }


     
    public void setIdPessoa( Long idPessoa ) {
        this.idPessoa = idPessoa ;
    }
    public Long getIdPessoa() {
        return this.idPessoa;
    }
           
    public void setNomeCompleto( String nomeCompleto ) {
        this.nomeCompleto = nomeCompleto ;
    }
    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

           
    public void setDtNascimento( LocalDate dtNascimento ) {
        this.dtNascimento = dtNascimento ;
    }
    public LocalDate getDtNascimento() {
        return this.dtNascimento;
    }

           
    public void setNrCpf( String nrCpf ) {
        this.nrCpf = nrCpf ;
    }
    public String getNrCpf() {
        return this.nrCpf;
    }

      
    @Override
    public String toString() {
        return "PessoaFisicaDTO [                                                                                                idPessoa=" + idPessoa + ",   nomeCompleto=" + nomeCompleto + ",   dtNascimento=" + dtNascimento + ",   nrCpf=" + nrCpf + "]";
      }

                                        
    @Override
    public int hashCode(){ return Objects.hash(idPessoa, nomeCompleto, dtNascimento, nrCpf); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PessoaFisicaRequest other = (PessoaFisicaRequest) obj;

        return
                 Objects.equals(this.idPessoa, other.idPessoa) &&          Objects.equals(this.nomeCompleto, other.nomeCompleto) &&          Objects.equals(this.dtNascimento, other.dtNascimento) &&          Objects.equals(this.nrCpf, other.nrCpf);     }

    public Set< String >isValidOnStage (Class<?> stage) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Set< ConstraintViolation< PessoaFisicaRequest >>violacoes = validator.validate(this, stage);

        if (violacoes.isEmpty()) {
            return Collections.emptySet();
        }

        Set< String > violations = new HashSet<>() ;
        violacoes.forEach((violacao)->{
            violations.add(violacao.getMessageTemplate());
        });

        return violations;
    }

    public String logString() {
        return ("Adicione uma implmenentação adequada.");
    }

}