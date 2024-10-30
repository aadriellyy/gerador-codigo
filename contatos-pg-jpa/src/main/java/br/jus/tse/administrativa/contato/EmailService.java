package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.jus.tse.administrativa.exceptions.CustomException;
import jakarta.transaction.Transactional;

@Service
public class EmailService {

    private EmailDao emailDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public EmailService(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Transactional
    public Email criarEmail(Email email){
        try {
            LOGGER.info("Criando email (service): {}", email.logString());
            this.emailDao.gravar(email);
            LOGGER.info("email criado com sucesso (service): {}", email.logString());
            return email;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
    @Transactional
    public Optional<Email> buscarPorId(Long id){
        try {
            LOGGER.info("Buscando email com ID (service): {}", id);
            Optional<Email> email = this.emailDao.recuperarPorId(id);
            if (email.isPresent()) {
                LOGGER.info("Endereço encontrado (service): {}", email.get().logString());
            } else {
                LOGGER.warn("Endereço com ID {} não encontrado (service)", id);
            }
            return email;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
    @Transactional
    public List<Email> buscarTodos(){
        try {
            LOGGER.info("Buscando todos os emails (service).");
            List<Email> emails = this.emailDao.recuperarTodos();
            LOGGER.info("Total de emails recuperados (service): {}", emails.size());
            return emails;
        } catch (CustomException e) {
            throw new CustomException(e);
        }

    }
    @Transactional
    public void atualizarEmail(Email email){
        try {
            LOGGER.info("Atualizando email (service): {}", email.logString());
            this.emailDao.regravar(email);
            LOGGER.info("Endereço atualizado com sucesso (service): {}", email.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
    @Transactional
    public void deletarEmail(Email email){
        try {
            LOGGER.info("Deletando email (service): {}", email.logString());
            this.emailDao.apagar(email);
            LOGGER.info("Endereço deletado com sucesso (service): {}", email.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

}
