package br.jus.tse.administrativa.contato;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
    public void gravar(ContatoPessoal contato) {
        try {
            LOGGER.info("Tentando gravar contato: {}", contato.logString());
            this.em.persist(contato);
            LOGGER.info("Contato gravado com sucesso: {}", contato.logString());
        } catch (PersistenceException e) {
            LOGGER.error("Erro ao gravar contato {}", e.getMessage(), e);
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
        }
        catch(PersistenceException e) {
            LOGGER.error("Não foi possível recuperar o 'ContatoPessoal' {} =>{}", id, e.getMessage(), e);
        }
        return Optional.empty();
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
        }
        catch(PersistenceException e) {
            LOGGER.error("Não foi possível recuperar a lista de  'ContatoPessoais' {}.",e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public void regravar(ContatoPessoal contato) {
       try {
            LOGGER.info("Tentando atualizar contato: {}", contato.logString());
            em.merge(contato);
            LOGGER.info("Contato atualizado com sucesso: {}", contato.logString());
       }catch(PersistenceException pe) {
           LOGGER.error("Não foi possível atualizar o   'ContatoPessoal' {} => {}.", contato.logString(), pe.getMessage(), pe);
       }
    }

    @Override
    public void apagar(ContatoPessoal contato) {
        try {
            LOGGER.info("Tentando apagar contato: {}", contato.logString());
            em.remove(contato);
            LOGGER.info("Contato apagado com sucesso: {}", contato.logString());
            
        } catch (PersistenceException e) {
            LOGGER.error("Não foi possível apagar o 'ContatoPessoal' {} =>{}", contato.logString(), e.getMessage(), e);
        }
        
      
    }

}
