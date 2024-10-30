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
import br.jus.tse.administrativa.contato.EmailDaoJpa;
import br.jus.tse.administrativa.contato.Email;
import jakarta.persistence.EntityManager;

@SpringBootTest
@ContextConfiguration
@TestPropertySource("../resources/application-test.properties") 
public class EmailPersistenceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContatoPessoalDaoJpa contatoPessoalDaoJpa = new ContatoPessoalDaoJpa(entityManager);

    @Autowired
    EmailDaoJpa emailDaoJpa = new EmailDaoJpa(entityManager);

    @Test
    @Transactional
    public void gravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289340",nascimento);
        contatoPessoalDaoJpa.gravar(dono);



        Email email = new Email("dono@dono",dono);
        emailDaoJpa.gravar(email);
        assertNotNull(email.getId());
        assertEquals("dono@dono", email.getEmail());
    }

    @Test
    @Transactional
    public void recuperarTodosTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Email email = new Email("dono@dono",dono);
        emailDaoJpa.gravar(email);
        assertNotNull(email.getId());
        List<Email> emails = emailDaoJpa.recuperarTodos();
        assertEquals(true, emails.contains(email));
        assertEquals(1, emails.size());
    }

    @Test
    @Transactional
    public void recuperarPorIdTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Email email = new Email("dono@dono",dono);
        emailDaoJpa.gravar(email);

        assertEquals(true, emailDaoJpa.recuperarPorId(email.getId()).isPresent());
    }

    @Test
    @Transactional
    public void regravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Email email = new Email("dono@dono",dono);
        emailDaoJpa.gravar(email);
        
        Email atualizado = new Email(email.getId(),"teste@teste",dono);
        emailDaoJpa.regravar(atualizado);
        assertEquals("teste@teste", emailDaoJpa.recuperarPorId(email.getId()).get().getEmail());

    }

    @Test
    @Transactional
    public void apagarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Email email = new Email("dono@dono",dono);
        emailDaoJpa.gravar(email);
        Long idEmail = email.getId();


        assertEquals(true, emailDaoJpa.recuperarPorId(idEmail).isPresent());
        emailDaoJpa.apagar(email);
        List<Email> emails = emailDaoJpa.recuperarTodos();
        assertEquals(false, emails.contains(email));


    }


    
}
