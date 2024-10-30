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
import br.jus.tse.administrativa.contato.EnderecoDaoJpa;
import br.jus.tse.administrativa.contato.Endereco;
import jakarta.persistence.EntityManager;

@SpringBootTest
@ContextConfiguration
@TestPropertySource("../resources/application-test.properties") 
public class EnderecoPersistenceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContatoPessoalDaoJpa contatoPessoalDaoJpa = new ContatoPessoalDaoJpa(entityManager);

    @Autowired
    EnderecoDaoJpa enderecoDaoJpa = new EnderecoDaoJpa(entityManager);

    @Test
    @Transactional
    public void gravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289340",nascimento);
        contatoPessoalDaoJpa.gravar(dono);



        Endereco endereco = new Endereco("69039-210","Rua Arthur de Souza","Novo Israel",dono);
        enderecoDaoJpa.gravar(endereco);
        assertNotNull(endereco.getId());
    }

    @Test
    @Transactional
    public void recuperarTodosTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Endereco endereco = new Endereco("69039-210","Rua Arthur de Souza","Novo Israel",dono);
        enderecoDaoJpa.gravar(endereco);
        assertNotNull(endereco.getId());
        List<Endereco> enderecos = enderecoDaoJpa.recuperarTodos();
        assertEquals(true, enderecos.contains(endereco));
        assertEquals(1, enderecos.size());
    }

    @Test
    @Transactional
    public void recuperarPorIdTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Endereco endereco = new Endereco("69039-210","Rua Arthur de Souza","Novo Israel",dono);
        enderecoDaoJpa.gravar(endereco);

        assertEquals(true, enderecoDaoJpa.recuperarPorId(endereco.getId()).isPresent());
    }

    @Test
    @Transactional
    public void regravarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Endereco endereco = new Endereco("69039-210","Rua Arthur de Souza","Novo Israel",dono);
        enderecoDaoJpa.gravar(endereco);
        
        Endereco atualizado = new Endereco(endereco.getId(),"60741-750","Vila Puebla","Serrinha",dono);
        enderecoDaoJpa.regravar(atualizado);
        assertEquals("60741-750", enderecoDaoJpa.recuperarPorId(endereco.getId()).get().getCep());
        assertEquals("Vila Puebla", enderecoDaoJpa.recuperarPorId(endereco.getId()).get().getLogradouro());
        assertEquals("Serrinha", enderecoDaoJpa.recuperarPorId(endereco.getId()).get().getComplemento());

    }

    @Test
    @Transactional
    public void apagarTest() {
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal("John Doe","04930289341",nascimento);
        contatoPessoalDaoJpa.gravar(dono);

        Endereco endereco = new Endereco("69039-210","Rua Arthur de Souza","Novo Israel",dono);
        enderecoDaoJpa.gravar(endereco);
        Long idEndereco = endereco.getId();


        assertEquals(true, enderecoDaoJpa.recuperarPorId(idEndereco).isPresent());
        enderecoDaoJpa.apagar(endereco);
        List<Endereco> enderecos = enderecoDaoJpa.recuperarTodos();
        assertEquals(false, enderecos.contains(endereco));


    }


    
}
