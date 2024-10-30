package br.jus.tse.administrativa.contato;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.jus.tse.administrativa.exceptions.CustomException;

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
    public Telefone gravar(Telefone telefone) {
        try{
            LOGGER.info("Tentando gravar telefone: {}", telefone.logString());
            this.em.persist(telefone);
            LOGGER.info("Telefone gravado com sucesso: {}", telefone.logString());
            return telefone;
        } catch (Exception e) {
            throw new CustomException("Erro ao gravar Telefone: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public Optional<Telefone> recuperarPorId(Long id) {
        String jpql = "select telefone from Telefone telefone where telefone.id = :id_telefone";
        TypedQuery<Telefone>typedObjectQuery = em.createQuery(jpql, Telefone.class);
        typedObjectQuery.setParameter("id_telefone", id);

        try{
            LOGGER.info("Recuperando telefone com ID: {}", id);
            Telefone telefone = typedObjectQuery.getSingleResult();
            LOGGER.info("Telefone recuperado com sucesso: {}", telefone.logString());
            return Optional.of(telefone);
        } catch (Exception e) {
            throw new CustomException("Não foi possível recuperar o Telefone: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public List<Telefone> recuperarTodos() {
        String jpql = "select telefone from Telefone telefone join fetch telefone.dono";
        TypedQuery<Telefone>TypedObjectQuery = em.createQuery(jpql, Telefone.class);

        try{
            LOGGER.info("Recuperando todos os telefones.");
            List<Telefone> telefones = TypedObjectQuery.getResultList();
            LOGGER.info("Total de telefones recuperados: {}", telefones.size());
            return telefones;
        } catch (Exception e) {
            throw new CustomException("Não foi possível recuperar a lista de Telefones: " + e.getCause()+ " " + e.getMessage());
        }
    }

    @Override
    public void regravar(Telefone telefone) {
        try{
            LOGGER.info("Tentando atualizar telefone: {}", telefone.logString());
            em.merge(telefone);
            LOGGER.info("Telefones atualizado com sucesso: {}", telefone.logString());
        } catch (Exception e) {
            throw new CustomException("Erro ao atualizar o Telefone: " + e.getCause()+ " " + e.getMessage());
        }

    }

    @Override
    public void apagar(Telefone telefone) {
        telefone = em.merge(telefone);
        try{
            LOGGER.info("Tentando apagar telefone: {}", telefone.logString());
            em.remove(telefone);
            LOGGER.info("Telefone apagado com sucesso: {}", telefone.logString());
        } catch (Exception e) {
            throw new CustomException("Não foi possível excluir o Telefone: " + e.getCause()+ " " + e.getMessage());
        }

    }
}