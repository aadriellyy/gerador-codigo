package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

interface TelefoneDao {
    
    void gravar(Telefone telefone);
    
    Optional<Telefone>recuperarPorId(Long id);
    
    List<Telefone>recuperarTodos();
    
    void regravar(Telefone telefone);
    
    void apagar(Telefone telefone);
    
    
}
