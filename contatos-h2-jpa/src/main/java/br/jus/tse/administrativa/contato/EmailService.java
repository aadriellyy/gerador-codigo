package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
    private EmailDao emailDao;

    public EmailService(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Transactional
    public void criarEmail(Email email){
        this.emailDao.gravar(email);
    }
    @Transactional
    public Optional<Email> buscarPorId(Long id){
        return this.emailDao.recuperarPorId(id);
    }
    @Transactional
    public List<Email> buscarTodos(){
        return this.emailDao.recuperarTodos();

    }
    @Transactional
    public void atualizarEmail(Email email){
        this.emailDao.regravar(email);
    }
    @Transactional
    public void deletarEmail(Email email){
        this.emailDao.apagar(email);
    }

}
