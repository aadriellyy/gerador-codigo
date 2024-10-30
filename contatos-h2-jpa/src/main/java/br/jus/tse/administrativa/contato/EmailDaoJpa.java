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
public class EmailDaoJpa implements EmailDao{

    private EntityManager em;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailDaoJpa.class);

    public EmailDaoJpa(EntityManager em){
        super();
        this.em = em;
    }

    @Override
    public void gravar(Email email) {
        try {
            LOGGER.info("Tentando gravar email: {}", email.logString());
            this.em.persist(email);
            LOGGER.info("Email gravado com sucesso: {}", email.logString());
        } catch (PersistenceException e) {
            LOGGER.error("Não foi possível gravar o 'Email' {} =>{}",email.logString(),e.getMessage(),e);
        }
    }

    @Override
    public Optional<Email> recuperarPorId(Long id) {
        String jpql = "select email from Email email where email.id=:id_email";
        TypedQuery<Email>typedObjectQuery = em.createQuery(jpql,Email.class);
        typedObjectQuery.setParameter("id_email",id);
        try{
            LOGGER.info("Recuperando email com ID: {}", id);
            Email email = typedObjectQuery.getSingleResult();
            LOGGER.info("Email recuperado com sucesso: {}", email.logString());
            return Optional.of(email);
        }
        catch(PersistenceException e){
            LOGGER.error("Não foi possível recuperar o 'Email' {} =>{}",id,e.getMessage(),e);
        }
        return Optional.empty();
    }


    @Override
    public List<Email> recuperarTodos() {
        String jpql = "select email from Email email join fetch email.dono";

        TypedQuery<Email>typedObjectQuery = em.createQuery(jpql,Email.class);
        try{
            LOGGER.info("Recuperando todos os emails.");
            List<Email> emails = typedObjectQuery.getResultList();
            LOGGER.info("Total de emails recuperados: {}", emails.size());
            return emails;
        }
        catch(PersistenceException e){
            LOGGER.error("Não foi possível recuperar a lista de  'Emails' {}.",e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public void regravar(Email email) {
        try{
            LOGGER.info("Tentando atualizar email: {}", email.logString());
            em.merge(email);
            LOGGER.info("Email atualizado com sucesso : {}", email.logString());
        } catch(PersistenceException e){
            LOGGER.error("Não foi possível atualizar o   'Email' {} => {}.", email.logString(), e.getMessage(), e);
        }
    }

    @Override
    public void apagar(Email email) {
        email = em.merge(email);
        try{
            LOGGER.info("Tentando deletar email: {}", email.logString());
            em.remove(email);
            LOGGER.info("Email deletado com sucesso: {}", email.logString());
        } catch(PersistenceException e){
            LOGGER.error("Não foi possível deletar o   'Email' {} => {}.", email.logString(), e.getMessage(), e);
        }
    }


}
