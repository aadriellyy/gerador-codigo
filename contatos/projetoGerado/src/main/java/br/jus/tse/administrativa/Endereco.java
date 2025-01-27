  






 /*
* Created on 2024-10-30 ( 11:31:29 )
* Generated by Telosys ( https://www.telosys.org/ ) version 4.1.0
*/                                                                                                                                                                                                                          













package br.jus.tse.administrativa;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import br.jus.tse.administrativa.rest.EnderecoRequest;

@Entity
@Table(name="endereco", schema="public" )
@SequenceGenerator(name = "sqEndereco", sequenceName = "SQ_ENDERECO", allocationSize = 1)       
public class Endereco {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqEndereco")

  
    @Id
    @Column(name="id_endereco", nullable=false)
    private Long idEndereco;    
    @Column(name="cep", nullable=false, length=10, unique=true)
    private String cep;

   
    @Column(name="logradouro", nullable=false, length=90, unique=true)
    private String logradouro;

   
    @Column(name="complemento", nullable=false, length=30, unique=true)
    private String complemento;

   
 
    @ManyToOne
    @JoinColumn(name="fkid_pessoa", referencedColumnName="id_pessoa")
    private PessoaFisica pessoafisica;



    public Endereco() {
    }

                                                                                                                 
    public Endereco(Long idEndereco, String cep, String logradouro, String complemento) {
        super();
        
        this.idEndereco = idEndereco;

        
        this.cep = cep;

        
        this.logradouro = logradouro;

        
        this.complemento = complemento;

            }

                                                                                                                                      
    public Endereco(Long idEndereco, String cep, String logradouro, String complemento, PessoaFisica pessoafisica) {
        super();
         
        this.idEndereco = idEndereco;   
        this.cep = cep;   
        this.logradouro = logradouro;   
        this.complemento = complemento;    
        this.pessoafisica = pessoafisica;

              }

    public Endereco(String cep, String logradouro, String complemento, PessoaFisica pessoafisica) {
        super();
          
        this.pessoafisica = pessoafisica;

        
        this.cep = cep;    
        this.pessoafisica = pessoafisica;

        
        this.logradouro = logradouro;    
        this.pessoafisica = pessoafisica;

        
        this.complemento = complemento;    
    }


    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }
    public Long getIdEndereco() {
        return this.idEndereco;
    }

 

    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCep() {
        return this.cep;
    }

 
 

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getLogradouro() {
        return this.logradouro;
    }

 
 

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getComplemento() {
        return this.complemento;
    }

 
  


    public PessoaFisica getPessoafisica() {
        return this.pessoafisica;
    }


    public void transform(EnderecoRequest request){
             this.cep = request.getCep();
              this.logradouro = request.getLogradouro();
              this.complemento = request.getComplemento();
           }

    @Override
    public int hashCode(){ return Objects.hash(idEndereco, cep, logradouro, complemento); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;

        return
                  Objects.equals(this.idEndereco, other.idEndereco) &&          Objects.equals(this.cep, other.cep) &&          Objects.equals(this.logradouro, other.logradouro) &&          Objects.equals(this.complemento, other.complemento);
    }

    public String logString() {
        return ("Adicione uma implmenentação adequada.");
    }


}