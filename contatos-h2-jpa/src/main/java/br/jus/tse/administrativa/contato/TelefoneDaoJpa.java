package br.jus.tse.administrativa.contato;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
public class TelefoneDaoJpa implements TelefoneDao {

    private EntityManager em;
    private static final Logger LOGGER = LoggerFactory.getLogger(TelefoneDaoJpa.class);

    public TelefoneDaoJpa(EntityManager em) {
        super();
        this.em = em;
    }

    @Override
    public void gravar(Telefone telefone) {
        try{
            LOGGER.info("Tentando gravar telefone: {}", telefone.logString());
            this.em.persist(telefone);
            LOGGER.info("Telefone gravado com sucesso: {}", telefone.logString());
        } catch (PersistenceException e){
            LOGGER.error("Erro ao gravar Telefone {}", e.getMessage(), e);
        }
    }

    @Override
    public Optional<Telefone> recuperarPorId(Long id) {
        String jpql = "SELECT telefone FROM Telefone telefone WHERE telefone.id = :id";
        TypedQuery<Telefone>typedObjectQuery = em.createQuery(jpql, Telefone.class);
        typedObjectQuery.setParameter("id", id);

        try{
            LOGGER.info("Recuperando telefone com ID: {}", id);
            Telefone telefone = typedObjectQuery.getSingleResult();
            LOGGER.info("Telefone recuperado com sucesso: {}", telefone.logString());
            return Optional.of(telefone);
        }catch(PersistenceException e){
            LOGGER.error("Não foi possível recuperar o Telefone {} => {}", id, e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Telefone> recuperarTodos() {
        String jpql = "SELECT telefone FROM Telefone telefone";
        TypedQuery<Telefone>TypedObjectQuery = em.createQuery(jpql, Telefone.class);

        try{
            LOGGER.info("Recuperando todos os telefones.");
            List<Telefone> telefones = TypedObjectQuery.getResultList();
            LOGGER.info("Total de telefones recuperados: {}", telefones.size());
            return telefones;
        } catch (PersistenceException e) {
            LOGGER.error("Não foi possível recuperar a lista de Telefones {}.", e.getMessage(), e);
        }

        return Collections.emptyList();
    }

    @Override
    public void regravar(Telefone telefone) {
        try{
            LOGGER.info("Tentando atualizar telefone: {}", telefone.logString());
            em.merge(telefone);
            LOGGER.info("Telefones atualizado com sucesso: {}", telefone.logString());
        } catch (PersistenceException e) {
            LOGGER.error("Erro ao atualizar o Telefone {} => {}", telefone.logString(), e.getMessage(), e);
        }

    }

    @Override
    public void apagar(Telefone telefone) {
        try{
            LOGGER.info("Tentando apagar telefone: {}", telefone.logString());
            em.remove(telefone);
            LOGGER.info("Telefone apagado com sucesso: {}", telefone.logString());
        } catch (PersistenceException e) {
            LOGGER.error("Não foi possível excluir o Telefone {} => {}", telefone.logString(), e.getMessage(), e);
        }

    }
}