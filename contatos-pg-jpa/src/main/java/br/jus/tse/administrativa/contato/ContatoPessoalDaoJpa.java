package br.jus.tse.administrativa.contato;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.jus.tse.administrativa.exceptions.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Repository
public class ContatoPessoalDaoJpa implements ContatoPessoalDao {

    private EntityManager em;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ContatoPessoalDaoJpa.class);
    
    public ContatoPessoalDaoJpa(EntityManager em) {
        super();
        this.em = em;
    }

    @Override
    public ContatoPessoal gravar(ContatoPessoal contato) throws CustomException {
        try {
            LOGGER.info("Tentando gravar contato: {}", contato.logString());
            this.em.persist(contato);
            LOGGER.info("Contato gravado com sucesso: {}", contato.logString());
            return contato;
        } catch (PersistenceException e) {
            throw new CustomException("Erro ao gravar contato: " + e.getCause()+ " " + e.getMessage());
        }

    }

    @Override
    public Optional<ContatoPessoal> recuperarPorId(Long id) {
        String jpql = "select pessoa from ContatoPessoal pessoa where pessoa.id=:id_pessoa";
        
        TypedQuery<ContatoPessoal>typedObjectQuery = em.createQuery(jpql, ContatoPessoal.class);
        typedObjectQuery.setParameter("id_pessoa", id);
        
        try {
            LOGGER.info("Recuperando contato com ID: {}", id);
            ContatoPessoal contato =  typedObjectQuery.getSingleResult();
            LOGGER.info("Contato recuperado com sucesso: {}", contato.logString());
            return Optional.of(contato);
        } catch (PersistenceException e) {
            throw new CustomException("Não foi possível recuperar o contato: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public List<ContatoPessoal> recuperarTodos() {
        String jpql = "select pessoa from ContatoPessoal pessoa";
        
        TypedQuery<ContatoPessoal>typedObjectQuery = em.createQuery(jpql, ContatoPessoal.class);
        
        try {
            LOGGER.info("Recuperando todos os contatos.");
            List<ContatoPessoal> contatos = typedObjectQuery.getResultList();
            LOGGER.info("Total de contatos recuperados: {}", contatos.size());
            return contatos;
        } catch (PersistenceException e) {
            throw new CustomException("Não foi possível recuperar a lista de  contatos: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public void regravar(ContatoPessoal contato) {
       try {
            LOGGER.info("Tentando atualizar contato: {}", contato.logString());
            em.merge(contato);
            LOGGER.info("Contato atualizado com sucesso: {}", contato.logString());
        } catch (PersistenceException e) {
            throw new CustomException("Não foi possível atualizar o contato: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public void apagar(ContatoPessoal contato) {
    contato = em.merge(contato);
        try {
            LOGGER.info("Tentando apagar contato: {}", contato.logString());
            em.remove(contato);
            LOGGER.info("Contato apagado com sucesso: {}", contato.logString());
            
        } catch (PersistenceException e) {
            throw new CustomException("Não foi possível apagar o contato: " + e.getCause()+ " " + e.getMessage());
        }
        
      
    }

}
