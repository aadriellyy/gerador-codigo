package br.jus.tse.administrativa.contato;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.jus.tse.administrativa.exceptions.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EnderecoDaoJpa implements EnderecoDao {

    private EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoDaoJpa.class);

    public EnderecoDaoJpa(EntityManager em) {
        super();
        this.em = em;
    }

    @Override
    public Endereco gravar(Endereco endereco) {
        try{
            LOGGER.info("Tentando gravar email: {}", endereco.logString());
            this.em.persist(endereco);
            LOGGER.info("Endereco gravado com sucesso: {}", endereco.logString());
            return endereco;
        } catch (Exception e) {
            throw new CustomException("Erro ao gravar Endereco: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public Optional<Endereco> recuperarPorId(Long id) {
        String jpql = "select endereco from Endereco endereco where endereco.id=:id_endereco";
        TypedQuery<Endereco>typedObjectQuery = em.createQuery(jpql,Endereco.class);
        typedObjectQuery.setParameter("id_endereco",id);

        try{
            LOGGER.info("Recuperando endereco com ID: {}", id);
            Endereco endereco = typedObjectQuery.getSingleResult();
            LOGGER.info("Endereco recuperado com sucesso: {}", endereco.logString());
            return Optional.of(endereco);
        } catch (Exception e) {
            throw new CustomException("Não foi possível recuperar o Endereco: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public List<Endereco> recuperarTodos() {
        //String jpql = "select endereco from Endereco endereco join fetch endereco.dono";
        String jpql = "select endereco from Endereco endereco";

        TypedQuery<Endereco>typedObjectQuery = em.createQuery(jpql,Endereco.class);
        try{
            LOGGER.info("Recuperando todos os enderecos");
            List<Endereco> enderecos = typedObjectQuery.getResultList();
            LOGGER.info("Total de enderecos recuperados: {}", enderecos.size());
            return enderecos;
        } catch (Exception e) {
            throw new CustomException("Não foi possível recuperar a lista de Endereco: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public void regravar(Endereco endereco) {
        try{
            LOGGER.info("Atualizando o endereco: {}", endereco.logString());
            em.merge(endereco);
            LOGGER.info("Endereco atualizado com sucesso: {}", endereco.logString());
        } catch (Exception e) {
            throw new CustomException("Não foi possível atualizar o Endereco: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public void apagar(Endereco endereco) {
        endereco = em.merge(endereco);
        try {
            LOGGER.info("Tentando apagar endereco: {}", endereco.logString());
            em.remove(endereco);
            LOGGER.info("Endereco deletado com sucesso: {}", endereco.logString());
        } catch (Exception e) {
            throw new CustomException("Não foi possível deletar o Endereco: " + e.getCause()+ " " + e.getMessage());
        }
    }
    
}
