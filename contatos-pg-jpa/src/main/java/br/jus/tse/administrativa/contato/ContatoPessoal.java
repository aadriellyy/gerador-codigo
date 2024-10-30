package br.jus.tse.administrativa.contato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.jus.tse.administrativa.contato.rest.ContatoPessoalRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "PESSOA_FISICA")
//@Access(AccessType.FIELD)
@SequenceGenerator(name = "sqContaPessoal", sequenceName = "SQ_PESSOA_FISICA", allocationSize = 1)
public class ContatoPessoal {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqContaPessoal")
    @Id
    @Column(name = "ID_PESSOA")
    private Long id;
    
    
    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;
    
    @Column(name = "NR_CPF")
    private String cpf;
    
    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;
   
   
    @OneToMany( mappedBy = "dono", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Telefone>telefones = new ArrayList<>();

    @OneToMany( mappedBy = "dono", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Email>emails = new ArrayList<>();

    @OneToOne(optional=true,cascade =  CascadeType.REMOVE, mappedBy="dono")
    @JsonBackReference
    private Endereco endereco;
    
        
    public ContatoPessoal(String nomeCompleto, String cpf, LocalDate nascimento) {
        super();
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public ContatoPessoal(Long id, String nomeCompleto, String cpf, LocalDate nascimento) {
        super();
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public ContatoPessoal() {

    }

    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public void setTelefones(List<Telefone> telefones){
        this.telefones = telefones;
    }

    public void addTelefone(Telefone telefone) {
       telefone.setDono(this);
       telefones.add(telefone);
    }
    
    public List<Telefone> getTelefones() {
        return this.telefones;
    }

    public void setEmails(List<Email> emails){
        this.emails = emails;
    }

    public void addEmail(Email email) {
        email.setDono(this);
        emails.add(email);
        
    }
     
     public List<Email> getEmails() {
         return this.emails;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void transforma(ContatoPessoalRequest request){
        this.cpf = request.getCpf();
        this.nomeCompleto = request.getNomeCompleto();
        this.nascimento = request.getNascimento();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, id, nascimento,nomeCompleto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContatoPessoal other = (ContatoPessoal) obj;
        return Objects.equals(this.cpf, other.cpf) && Objects.equals(this.id, other.id)
                && Objects.equals(this.nascimento, other.nascimento) && Objects.equals(this.nomeCompleto, other.nomeCompleto);
    }

    
    
    @Override
    public String toString() {
        return "ContatoPessoal [id=" + id + ", nomeCompleto=" + nomeCompleto + ", cpf=" + cpf + ", nascimento="
                + nascimento + "]";
    }

    public String logString() {
        //throw new UnsupportedOperationException("Adicione uma implmenentação adequada.");
        return ("Adicione uma implementação adequada.");
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
