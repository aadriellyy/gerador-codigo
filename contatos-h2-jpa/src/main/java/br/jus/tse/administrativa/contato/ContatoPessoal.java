package br.jus.tse.administrativa.contato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
    private List<Telefone>telefones = new ArrayList<>();

    @OneToMany( mappedBy = "dono", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Email>emails = new ArrayList<>();

    @OneToOne(optional=false,cascade =  CascadeType.REMOVE, mappedBy="dono")
    private Endereco endereco;
    
        
    public ContatoPessoal(String nomeCompleto, String cpf, LocalDate nascimento) {
        super();
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
    
    public void addTelefone(Telefone telefone) {
       telefone.setDono(this);
       telefones.add(telefone);
    }
    
    public List<Telefone> getTelefones() {
        return this.telefones;
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

    @Override
    public int hashCode() {
        return Objects.hash(cpf, id, nascimento);
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
                && Objects.equals(this.nascimento, other.nascimento);
    }
    
    public String logString() {
        throw new UnsupportedOperationException("Adicione uma implmenentação adequada.");
    }
}
