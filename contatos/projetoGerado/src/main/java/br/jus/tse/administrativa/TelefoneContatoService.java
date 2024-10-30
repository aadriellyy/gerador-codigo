  






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
public class TelefoneContatoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelefoneContatoService.class);

    private TelefoneContatoDao repository;

    public TelefoneContatoService(TelefoneContatoDao repository) {

        super();
        this.repository = repository;
    }

    @Transactional
    public List<TelefoneContato> findAll() {
        try{
            LOGGER.info("Buscando todos TelefoneContato (service).");
            List<TelefoneContato> list = this.repository.findAll();
            LOGGER.info("Tamanho da lista recuperada (service): {}", list.size());
            return list;
        }catch (Exception e){
            LOGGER.error("Erro ao buscar todos TelefoneContato (service).", e);
            throw e;
        }
    }

    @Transactional
    public Optional<TelefoneContato> findById(Long id) {
        try{
            LOGGER.info("Buscando TelefoneContato com ID (service): {}", id);
            Optional<TelefoneContato> TelefoneContato = this.repository.findById(id);
                if (TelefoneContato.isPresent()) {
                    LOGGER.info("TelefoneContato encontrado (service): {}", TelefoneContato .get().logString());
                } else {
                    LOGGER.warn("TelefoneContato  com ID {} não encontrado (service)", id);
                }
            return TelefoneContato ;
        } catch (Exception e){
            LOGGER.error("Erro ao buscar TelefoneContato com ID (service): {}", id, e);
            throw e;
        }

    }

    @Transactional
    public void save(TelefoneContato TelefoneContato) {
        try {
            LOGGER.info("Criando o TelefoneContato (service): {}", TelefoneContato.logString());
            this.repository.save(TelefoneContato);
            LOGGER.info("TelefoneContato criado com sucesso (service): {}", TelefoneContato.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao criar TelefoneContato (service): {}", TelefoneContato.logString(), e);
            throw e;
        }
        this.repository.save(TelefoneContato);
    }

    @Transactional
    public void update(TelefoneContato TelefoneContato) {
        try {
            LOGGER.info("Atualizando TelefoneContato (service): {}", TelefoneContato.logString());
            this.repository.update(TelefoneContato);
            LOGGER.info("TelefoneContato atualizado com sucesso (service): {}", TelefoneContato.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar TelefoneContato (service): {}", TelefoneContato.logString(), e);
            throw e;
        }
        this.repository.update(TelefoneContato);
    }

    @Transactional
    public void delete(TelefoneContato TelefoneContato) {
        try {
            LOGGER.info("Deletando TelefoneContato (service): {}", TelefoneContato.logString());
            this.repository.delete(TelefoneContato);
            LOGGER.info("TelefoneContato deletado com sucesso (service): {}", TelefoneContato.logString());
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar TelefoneContato (service): {}", TelefoneContato.logString(), e);
            throw e;
        }
        this.repository.delete(TelefoneContato);
    }



}