package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.administrativa.exceptions.CustomException;

@Service
public class TelefoneService {

    private TelefoneDao telefoneDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(TelefoneService.class);

    public TelefoneService(TelefoneDao telefoneDao) {
        super();
        this.telefoneDao = telefoneDao;
    }


    @Transactional
    public List<Telefone>buscarTodos(){
        try {
            LOGGER.info("Buscando todos os telefones (service).");
            List<Telefone> telefones = this.telefoneDao.recuperarTodos();
            LOGGER.info("Total de telefones recuperados (service): {}", telefones.size());
            return telefones;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public Optional<Telefone> buscarPorId(Long id) {
        try {
            LOGGER.info("Buscando telefone com ID (service): {}", id);
            Optional<Telefone> telefone = this.telefoneDao.recuperarPorId(id);
            if (telefone.isPresent()) {
                LOGGER.info("Telefone encontrado (service): {}", telefone.get().logString());
            } else {
                LOGGER.warn("Telefone com ID {} não encontrado (service)", id);
            }
            return telefone;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public Telefone adicionar(Telefone telefone) {
        try {
            LOGGER.info("Criando telefone (service): {}", telefone.logString());
            this.telefoneDao.gravar(telefone);
            LOGGER.info("Telefone criado com sucesso (service): {}", telefone.logString());
            return telefone;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public void atualizarTelefone(Telefone telefone) {
        try {
            LOGGER.info("Atualizando telefone (service): {}", telefone.logString());
            this.telefoneDao.regravar(telefone);
            LOGGER.info("Telefone atualizado com sucesso (service): {}", telefone.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

    @Transactional
    public void deletarTelefone(Telefone telefone) {
        try {
            LOGGER.info("Deletando telefone (service): {}", telefone.logString());
            this.telefoneDao.apagar(telefone);
            LOGGER.info("Telefone deletado com sucesso (service): {}", telefone.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
}
