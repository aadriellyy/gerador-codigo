package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

interface EnderecoDao {
    
    void gravar(Endereco endereco);
    
    Optional<Endereco>recuperarPorId(Long id);
    
    List<Endereco>recuperarTodos();
    
    void regravar(Endereco endereco);
    
    void apagar(Endereco endereco);
}
