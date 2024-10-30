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
import br.jus.tse.administrativa.contato.TelefoneDaoJpa;
import br.jus.tse.administrativa.contato.Telefone;
import jakarta.persistence.EntityManager;

@SpringBootTest
@ContextConfiguration
@TestPropertySource("../resources/application-test.properties") 
public class TelefonePersistenceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContatoPessoalDaoJpa contatoPessoalDaoJpa = new ContatoPessoalDaoJpa(entityManager);

    @Autowired
    TelefoneDaoJpa telefoneDaoJpa = new TelefoneDaoJpa(entityManager);

    @Test
    @Transactional
    public void gravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289340",nascimento);
        contatoPessoalDaoJpa.gravar(dono);



        Telefone telefone = new Telefone("(82) 2442-5913",dono);
        telefoneDaoJpa.gravar(telefone);
        assertNotNull(telefone.getId());
        assertEquals("(82) 2442-5913", telefone.getTelefone());
    }

    @Test
    @Transactional
    public void recuperarTodosTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Telefone telefone = new Telefone("(82) 2442-5913",dono);
        telefoneDaoJpa.gravar(telefone);
        assertNotNull(telefone.getId());
        List<Telefone> telefones = telefoneDaoJpa.recuperarTodos();
        assertEquals(true, telefones.contains(telefone));
        assertEquals(1, telefones.size());
    }

    @Test
    @Transactional
    public void recuperarPorIdTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Telefone telefone = new Telefone("(82) 2442-5913",dono);
        telefoneDaoJpa.gravar(telefone);

        assertEquals(true, telefoneDaoJpa.recuperarPorId(telefone.getId()).isPresent());
    }

    @Test
    @Transactional
    public void regravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Telefone telefone = new Telefone("(82) 2442-5913",dono);
        telefoneDaoJpa.gravar(telefone);
        
        Telefone atualizado = new Telefone(telefone.getId(),"(66) 3221-0774",dono);
        telefoneDaoJpa.regravar(atualizado);
        assertEquals("(66) 3221-0774", telefoneDaoJpa.recuperarPorId(telefone.getId()).get().getTelefone());

    }

    @Test
    @Transactional
    public void apagarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Telefone telefone = new Telefone("(82) 2442-5913",dono);
        telefoneDaoJpa.gravar(telefone);
        Long idTelefone = telefone.getId();


        assertEquals(true, telefoneDaoJpa.recuperarPorId(idTelefone).isPresent());
        telefoneDaoJpa.apagar(telefone);
        List<Telefone> telefones = telefoneDaoJpa.recuperarTodos();
        assertEquals(false, telefones.contains(telefone));


    }


    
}
