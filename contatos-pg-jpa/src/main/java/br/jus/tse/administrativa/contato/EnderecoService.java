package br.jus.tse.administrativa.contato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.jus.tse.administrativa.exceptions.CustomException;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {
    private EnderecoDao enderecoDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoService.class);

    public EnderecoService(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    @Transactional
    public Endereco criarEndereco(Endereco endereco){
        try {
            LOGGER.info("Criando endereço (service): {}", endereco.logString());
            this.enderecoDao.gravar(endereco);
            LOGGER.info("Endereço criado com sucesso (service): {}", endereco.logString());
            return endereco;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
    @Transactional
    public Optional<Endereco> buscarPorId(Long id){
        try {
            LOGGER.info("Buscando endereço com ID (service): {}", id);
            Optional<Endereco> endereco = this.enderecoDao.recuperarPorId(id);
            if (endereco.isPresent()) {
                LOGGER.info("Endereço encontrado (service): {}", endereco.get().logString());
            } else {
                LOGGER.warn("Endereço com ID {} não encontrado (service)", id);
            }
            return endereco;
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
    @Transactional
    public List<Endereco> buscarTodos(){
        try {
            LOGGER.info("Buscando todos os endereços (service).");
            List<Endereco> enderecos = this.enderecoDao.recuperarTodos();
            LOGGER.info("Total de endereços recuperados (service): {}", enderecos.size());
            return enderecos;
        } catch (CustomException e) {
            throw new CustomException(e);
        }

    }
    @Transactional
    public void atualizarEndereco(Endereco endereco){
        try {
            LOGGER.info("Atualizando endereço (service): {}", endereco.logString());
            this.enderecoDao.regravar(endereco);
            LOGGER.info("Endereço atualizado com sucesso (service): {}", endereco.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }
    @Transactional
    public void deletarEndereco(Endereco endereco){
        try {
            LOGGER.info("Deletando endereço (service): {}", endereco.logString());
            this.enderecoDao.apagar(endereco);
            LOGGER.info("Endereço deletado com sucesso (service): {}", endereco.logString());
        } catch (CustomException e) {
            throw new CustomException(e);
        }
    }

}
