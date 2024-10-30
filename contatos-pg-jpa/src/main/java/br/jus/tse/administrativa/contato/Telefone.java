package br.jus.tse.administrativa.contato;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TELEFONE_CONTATO")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "sqTelefone", sequenceName = "SQ_TELEFONE", allocationSize = 1)
@JsonIgnoreProperties(value= {"suppliers"})
public class Telefone {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqTelefone")
    @Id
    @Column(name = "ID_TELEFONE")
    private Long id;
    
    
    @Column(name = "NR_TELEFONE")
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="FKID_PESSOA", referencedColumnName="ID_PESSOA",nullable=false) 
    private ContatoPessoal dono;
    
    public Telefone(){
    }

    public Telefone(String telefone, ContatoPessoal dono) {
        super();
        this.telefone = telefone;
        this.dono = dono;
    }

    public Telefone(Long id, String telefone, ContatoPessoal dono) {
        super();
        this.id = id;
        this.telefone = telefone;
        this.dono = dono;
    }

    public void setDono(ContatoPessoal dono) {
        this.dono = dono;
    }


    public Long getId() {
        return this.id;
    }


    public String getTelefone() {
        return this.telefone;
    }


    public ContatoPessoal getDono() {
        return this.dono;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id,telefone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        return Objects.equals(this.telefone,other.telefone) && Objects.equals(this.id, other.id) && Objects.equals(this.dono,other.dono);
    }

    @Override
    public String toString() {
        return "Telefone [id=" + id + ", telefone=" + telefone + ", dono=" + dono + "]";
    }

    public String logString() {
        //throw new UnsupportedOperationException("Adicione uma implmenentação adequada.");
        return ("Adicione uma implementação adequada.");
    }
    
    
}
