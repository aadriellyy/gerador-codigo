package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

interface ContatoPessoalDao {
    
    void gravar(ContatoPessoal contato);
    
    Optional<ContatoPessoal>recuperarPorId(Long id);
    
    List<ContatoPessoal>recuperarTodos();
    
    void regravar(ContatoPessoal contato);
    
    void apagar(ContatoPessoal contato);
    
    
}
