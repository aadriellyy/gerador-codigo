package br.jus.tse.administrativa.contato;

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
public class Telefone {
    
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqTelefone")
    @Id
    @Column(name = "ID_TELEFONE")
    private Long id;
    
    
    @Column(name = "NR_TELEFONE")
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FKID_PESSOA", referencedColumnName="ID_PESSOA",nullable=false) 
    private ContatoPessoal dono;
    
    
    public void setDono(ContatoPessoal dono) {
        this.dono = dono;
    }

    public Telefone(String telefone) {
        super();
        this.telefone = telefone;
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
    
    
    public String logString() {
        throw new UnsupportedOperationException("Adicione uma implmenentação adequada.");
    }
    
    
}
