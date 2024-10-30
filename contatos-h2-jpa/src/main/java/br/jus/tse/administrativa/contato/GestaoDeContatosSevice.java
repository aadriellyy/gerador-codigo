package br.jus.tse.administrativa.contato;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GestaoDeContatosSevice {
    private ContatoPessoalDao contatoDao;

    public GestaoDeContatosSevice(ContatoPessoalDao contatoDao) {
        this.contatoDao = contatoDao;
    }

    @Transactional
    public void criarContato(ContatoPessoal contato){
        this.contatoDao.gravar(contato);

    }

    public ContatoPessoal pegaUm(Long id){
        return this.contatoDao.recuperarPorId(id).get();
    }

}