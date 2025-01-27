  






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

import br.jus.tse.administrativa.rest.PessoaFisicaRequest;

@Entity
@Table(name="pessoa_fisica", schema="public" )
@SequenceGenerator(name = "sqPessoaFisica", sequenceName = "SQ_PESSOA_FISICA", allocationSize = 1)       
public class PessoaFisica {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqPessoaFisica")

  
    @Id
    @Column(name="id_pessoa", nullable=false)
    private Long idPessoa;    
    @Column(name="nome_completo", nullable=false, length=120)
    private String nomeCompleto;

   
    @Column(name="dt_nascimento", nullable=false)
    private LocalDate dtNascimento;

   
    @Column(name="nr_cpf", nullable=false, length=11)
    private String nrCpf;

 

    
 
    @OneToMany(mappedBy = "pessoafisica")
    private List<Email> objEmail = new ArrayList<>();          
              
 
    @OneToMany(mappedBy = "pessoafisica")
    private List<Endereco> objEndereco = new ArrayList<>();          
              
 
    @OneToMany(mappedBy = "pessoafisica")
    private List<TelefoneContato> objTelefoneContato = new ArrayList<>();             
    public PessoaFisica() {
    }

                                                                                               
    public PessoaFisica(Long idPessoa, String nomeCompleto, LocalDate dtNascimento, String nrCpf) {
        super();
        
        this.idPessoa = idPessoa;

        
        this.nomeCompleto = nomeCompleto;

        
        this.dtNascimento = dtNascimento;

        
        this.nrCpf = nrCpf;

            }


    public PessoaFisica(String nomeCompleto, LocalDate dtNascimento, String nrCpf) {
        super();
        
        this.nomeCompleto = nomeCompleto;
        
        this.dtNascimento = dtNascimento;
        
        this.nrCpf = nrCpf;
             }


    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
    public Long getIdPessoa() {
        return this.idPessoa;
    }

 

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

 
 

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    public LocalDate getDtNascimento() {
        return this.dtNascimento;
    }

 
 

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }
    public String getNrCpf() {
        return this.nrCpf;
    }

 

                        
    public void addEmail(Email object){
        objEmail.add(object);
    }

    @JsonBackReference
    public List<Email> getEmail(){
        return this.objEmail;
    }

                                                      
    public void addEndereco(Endereco object){
        objEndereco.add(object);
    }

    @JsonBackReference
    public List<Endereco> getEndereco(){
        return this.objEndereco;
    }

                                                      
    public void addTelefoneContato(TelefoneContato object){
        objTelefoneContato.add(object);
    }

    @JsonBackReference
    public List<TelefoneContato> getTelefoneContato(){
        return this.objTelefoneContato;
    }

                       

    public void transform(PessoaFisicaRequest request){
             this.nomeCompleto = request.getNomeCompleto();
              this.dtNascimento = request.getDtNascimento();
              this.nrCpf = request.getNrCpf();
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
        PessoaFisica other = (PessoaFisica) obj;

        return
                  Objects.equals(this.idPessoa, other.idPessoa) &&          Objects.equals(this.nomeCompleto, other.nomeCompleto) &&          Objects.equals(this.dtNascimento, other.dtNascimento) &&          Objects.equals(this.nrCpf, other.nrCpf);
    }

    public String logString() {
        return ("Adicione uma implmenentação adequada.");
    }


}