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
@Table(name = "EMAIL")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "sqEmail", sequenceName = "SQ_EMAIL",allocationSize = 1)
@JsonIgnoreProperties(value= {"suppliers"})
public class Email {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sqEmail")
    @Id
    @Column(name="ID_EMAIL")
    private Long id;
    
    @Column(name="CX_EMAIL")
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="FKID_PESSOA", referencedColumnName="ID_PESSOA",nullable=false)
    private ContatoPessoal dono;

    public Email() {
    }

    public Email(String email, ContatoPessoal dono) {
        super();
        this.email = email;
        this.dono = dono;
    }
    public Email(Long id, String email, ContatoPessoal dono) {
        super();
        this.id = id;
        this.email = email;
        this.dono = dono;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public ContatoPessoal getDono() {
        return dono;
    }

    public void setDono(ContatoPessoal dono) {
        this.dono = dono;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Email other = (Email) obj;
        return Objects.equals(this.email,other.email) && Objects.equals(this.id, other.id) && Objects.equals(this.dono,other.dono);
    }

    @Override
    public String toString() {
        return "Email [id=" + id + ", email=" + email + ", dono=" + dono + "]";
    }
    
    public String logString() {
        throw new UnsupportedOperationException("Adicione uma implmenentação adequada.");
    }
    
    
}
