package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

interface EmailDao {
    
    void gravar(Email email);
    
    Optional<Email>recuperarPorId(Long id);
    
    List<Email>recuperarTodos();
    
    void regravar(Email email);
    
    void apagar(Email email);
    
    
}
