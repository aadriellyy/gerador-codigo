  






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

import br.jus.tse.administrativa.rest.TelefoneContatoRequest;

@Entity
@Table(name="telefone_contato", schema="public" )
@SequenceGenerator(name = "sqTelefoneContato", sequenceName = "SQ_TELEFONE_CONTATO", allocationSize = 1)       
public class TelefoneContato {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqTelefoneContato")

  
    @Id
    @Column(name="id_telefone", nullable=false)
    private Long idTelefone;    
    @Column(name="nr_telefone", nullable=false, length=15, unique=true)
    private String nrTelefone;

   
 
    @ManyToOne
    @JoinColumn(name="fkid_pessoa", referencedColumnName="id_pessoa")
    private PessoaFisica pessoafisica;



    public TelefoneContato() {
    }

                                                             
    public TelefoneContato(Long idTelefone, String nrTelefone) {
        super();
        
        this.idTelefone = idTelefone;

        
        this.nrTelefone = nrTelefone;

            }

                                                                                  
    public TelefoneContato(Long idTelefone, String nrTelefone, PessoaFisica pessoafisica) {
        super();
         
        this.idTelefone = idTelefone;   
        this.nrTelefone = nrTelefone;    
        this.pessoafisica = pessoafisica;

              }

    public TelefoneContato(String nrTelefone, PessoaFisica pessoafisica) {
        super();
          
        this.pessoafisica = pessoafisica;

        
        this.nrTelefone = nrTelefone;    
    }


    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }
    public Long getIdTelefone() {
        return this.idTelefone;
    }

 

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }
    public String getNrTelefone() {
        return this.nrTelefone;
    }

 
  


    public PessoaFisica getPessoafisica() {
        return this.pessoafisica;
    }


    public void transform(TelefoneContatoRequest request){
             this.nrTelefone = request.getNrTelefone();
           }

    @Override
    public int hashCode(){ return Objects.hash(idTelefone, nrTelefone); }

    @Override
    public boolean equals (Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TelefoneContato other = (TelefoneContato) obj;

        return
                  Objects.equals(this.idTelefone, other.idTelefone) &&          Objects.equals(this.nrTelefone, other.nrTelefone);
    }

    public String logString() {
        return ("Adicione uma implmenentação adequada.");
    }


}