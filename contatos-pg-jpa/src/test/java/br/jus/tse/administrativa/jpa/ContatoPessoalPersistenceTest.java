package br.jus.tse.administrativa.jpa;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.ContatoPessoalDaoJpa;
import jakarta.persistence.EntityManager;

@SpringBootTest
@ContextConfiguration
@TestPropertySource("../resources/application-test.properties") 
public class ContatoPessoalPersistenceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContatoPessoalDaoJpa contatoPessoalDaoJpa = new ContatoPessoalDaoJpa(entityManager);

    @Test
    @Transactional
    public void gravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal contato = new ContatoPessoal("John Doe","04930289340",nascimento);

        contatoPessoalDaoJpa.gravar(contato);

        assertNotNull(contato.getId());
    }

    @Test
    @Transactional
    public void recuperarTodosTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal contato = new ContatoPessoal("John Doe","04930289341",nascimento);

        contatoPessoalDaoJpa.gravar(contato);
        assertNotNull(contato.getId());
        List<ContatoPessoal> contatos = contatoPessoalDaoJpa.recuperarTodos();        
        assertEquals(true, contatos.contains(contato));
        assertEquals(1,contatos.size());
    }

    @Test
    @Transactional
    public void recuperarPorIdTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal contato = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(contato);

        assertEquals(true,contatoPessoalDaoJpa.recuperarPorId(contato.getId()).isPresent());
    }

    @Test
    @Transactional
    public void regravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal contato = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(contato);

        contato.setCpf("04930042341");
        contatoPessoalDaoJpa.regravar(contato);
        assertEquals("04930042341", contatoPessoalDaoJpa.recuperarPorId(contato.getId()).get().getCpf());

    }

    @Test
    @Transactional
    public void apagarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal contato = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(contato);
        Long id = contato.getId();

        assertEquals(true,contatoPessoalDaoJpa.recuperarPorId(id).isPresent());
        contatoPessoalDaoJpa.apagar(contato);
        List<ContatoPessoal> contatos = contatoPessoalDaoJpa.recuperarTodos();
        assertEquals(false,contatos.contains(contato));
    }


    

}
