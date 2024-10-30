package br.jus.tse.administrativa.contato.rest;


import java.time.LocalDate;
import br.jus.tse.administrativa.contato.ContatoPessoal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ContatoPessoalDTO {

    @JsonProperty("id")
    private Long id;  

    @JsonProperty("nomeCompleto")
    private String nomeCompleto;

    @JsonProperty("nascimento")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate nascimento;

    @JsonProperty("cpf")
    private String cpf;

    public ContatoPessoal trasforma(){
        ContatoPessoal contato = new ContatoPessoal(this.nomeCompleto, this.cpf, this.nascimento); 
        return contato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ContatoPessoalDTO [id=" + id + ", nomeCompleto=" + nomeCompleto + ", nascimento=" + nascimento
                + ", cpf=" + cpf + "]";
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
        ContatoPessoalDTO other = (ContatoPessoalDTO) obj;
        return Objects.equals(this.cpf, other.cpf) && Objects.equals(this.id, other.id)
                && Objects.equals(this.nascimento, other.nascimento);
    }
    
}
