  






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
public class PessoaFisicaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaFisicaService.class);

    private PessoaFisicaDao repository;

    public PessoaFisicaService(PessoaFisicaDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<PessoaFisica> findAll() {
        try{
            LOGGER.info("Buscando todos PessoaFisica (service).");
            List<PessoaFisica> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos PessoaFisica (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<PessoaFisica> findById(Long id) {
        try{
            LOGGER.info("Buscando PessoaFisica com ID (service): {}", id);
            Optional<PessoaFisica> PessoaFisica = this.repository.findById(id);
                if (PessoaFisica.isPresent()) {
                    LOGGER.info("PessoaFisica encontrado (service): {}", PessoaFisica .get().logString());
                } else {
                    LOGGER.warn("PessoaFisica  com ID {} não encontrado (service)", id);
                }
            return PessoaFisica ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar PessoaFisica com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(PessoaFisica PessoaFisica) {
        try {
            LOGGER.info("Criando o PessoaFisica (service): {}", PessoaFisica.logString());
            this.repository.save(PessoaFisica);
            LOGGER.info("PessoaFisica criado com sucesso (service): {}", PessoaFisica.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao criar PessoaFisica (service): {}", PessoaFisica.logString(), e);
            throw e;
        }
        this.repository.save(PessoaFisica);
    }

    @Transactional
    public void update(PessoaFisica PessoaFisica) {
        try {
            LOGGER.info("Atualizando PessoaFisica (service): {}", PessoaFisica.logString());
            this.repository.update(PessoaFisica);
            LOGGER.info("PessoaFisica atualizado com sucesso (service): {}", PessoaFisica.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar PessoaFisica (service): {}", PessoaFisica.logString(), e);
            throw e;
        }
        this.repository.update(PessoaFisica);
    }

    @Transactional
    public void delete(PessoaFisica PessoaFisica) {
        try {
            LOGGER.info("Deletando PessoaFisica (service): {}", PessoaFisica.logString());
            this.repository.delete(PessoaFisica);
            LOGGER.info("PessoaFisica deletado com sucesso (service): {}", PessoaFisica.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar PessoaFisica (service): {}", PessoaFisica.logString(), e);
            throw e;
        }
        this.repository.delete(PessoaFisica);
    }



}