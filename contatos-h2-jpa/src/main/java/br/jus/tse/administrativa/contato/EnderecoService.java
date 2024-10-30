package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {
    private EnderecoDao enderecoDao;

    public EnderecoService(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    @Transactional
    public void criarEndereco(Endereco endereco){
        this.enderecoDao.gravar(endereco);
    }
    @Transactional
    public Optional<Endereco> buscarPorId(Long id){
        return this.enderecoDao.recuperarPorId(id);
    }
    @Transactional
    public List<Endereco> buscarTodos(){
        return this.enderecoDao.recuperarTodos();

    }
    @Transactional
    public void atualizarEndereco(Endereco endereco){
        this.enderecoDao.regravar(endereco);
    }
    @Transactional
    public void deletarEndereco(Endereco endereco){
        this.enderecoDao.apagar(endereco);
    }

}
