  






 /*
* Created on 2024-10-30 ( 11:31:29 )
* Generated by Telosys ( https://www.telosys.org/ ) version 4.1.0
*/
package br.jus.tse.administrativa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private EmailDao repository;

    public EmailService(EmailDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<Email> findAll() {
        try{
            LOGGER.info("Buscando todos Email (service).");
            List<Email> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos Email (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<Email> findById(Long id) {
        try{
            LOGGER.info("Buscando Email com ID (service): {}", id);
            Optional<Email> Email = this.repository.findById(id);
                if (Email.isPresent()) {
                    LOGGER.info("Email encontrado (service): {}", Email .get().logString());
                } else {
                    LOGGER.warn("Email  com ID {} não encontrado (service)", id);
                }
            return Email ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar Email com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(Email Email) {
        try {
            LOGGER.info("Criando o Email (service): {}", Email.logString());
            this.repository.save(Email);
            LOGGER.info("Email criado com sucesso (service): {}", Email.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao criar Email (service): {}", Email.logString(), e);
            throw e;
        }
        this.repository.save(Email);
    }

    @Transactional
    public void update(Email Email) {
        try {
            LOGGER.info("Atualizando Email (service): {}", Email.logString());
            this.repository.update(Email);
            LOGGER.info("Email atualizado com sucesso (service): {}", Email.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar Email (service): {}", Email.logString(), e);
            throw e;
        }
        this.repository.update(Email);
    }

    @Transactional
    public void delete(Email Email) {
        try {
            LOGGER.info("Deletando Email (service): {}", Email.logString());
            this.repository.delete(Email);
            LOGGER.info("Email deletado com sucesso (service): {}", Email.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar Email (service): {}", Email.logString(), e);
            throw e;
        }
        this.repository.delete(Email);
    }



}