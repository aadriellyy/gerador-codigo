package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.administrativa.exceptions.CustomException;

@Service
public class ContatoPessoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContatoPessoalService.class);
    private ContatoPessoalDao contatoPessoalDao;

    public ContatoPessoalService(ContatoPessoalDao contatoPessoalDao) {
        super();
        this.contatoPessoalDao = contatoPessoalDao;
    }

    @Transactional
    public List<ContatoPessoal>buscarTodos(){
        try {
            LOGGER.info("Buscando todos os contatos (service).");
            List<ContatoPessoal> contatos = this.contatoPessoalDao.recuperarTodos();
            LOGGER.info("Total de contatos recuperados (service): {}", contatos.size());
            return contatos;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public Optional<ContatoPessoal> buscarPorId(Long id) {
        try {
            LOGGER.info("Buscando contato com ID (service): {}", id);
            Optional<ContatoPessoal> contato = this.contatoPessoalDao.recuperarPorId(id);
            if (contato.isPresent()) {
                LOGGER.info("Contato encontrado (service): {}", contato.get().logString());
            } else {
                LOGGER.warn("Contato com ID {} não encontrado (service)", id);
            }
            return contato;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public ContatoPessoal salvar(ContatoPessoal contato){
        try {
            LOGGER.info("Criando contato (service): {}", contato.logString());
            this.contatoPessoalDao.gravar(contato);
            LOGGER.info("Contato criado com sucesso (service): {}", contato.logString());
            return contato;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public void regravar(ContatoPessoal contato){
        try {
            LOGGER.info("Atualizando contato (service): {}", contato.logString());
            this.contatoPessoalDao.regravar(contato);
            LOGGER.info("Contato atualizado com sucesso (service): {}", contato.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public void excluir(ContatoPessoal contato){
        try {
            LOGGER.info("Deletando contato (service): {}", contato.logString());
            this.contatoPessoalDao.apagar(contato);
            LOGGER.info("Contato deletado com sucesso (service): {}", contato.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

}
