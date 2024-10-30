package br.jus.tse.administrativa.contato;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EnderecoDaoTest {

    @Autowired
    private EnderecoDao enderecoDao;
    private ContatoPessoalDao contatoPessoalDao;


    @Test
    void testApagar() {

    }

    @Test
    void testGravar() {
        String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        contatoPessoalDao.gravar(dono);
        long id = 3;
        String cep = "65082-722";
        String logradouro = "Vila Nova"; 
        String complemento = "Casa 404";
        Endereco endereco = new Endereco(id,cep,logradouro,complemento,dono);
       // enderecoDao.gravar(endereco);
        //Optional<Endereco> search = enderecoDao.recuperarPorId(endereco.getId());
       // assert(search.isPresent());
        //assert(search.get().getCep().equals(cep));
    }

    @Test
    void testRecuperarPorId() {

    }

    @Test
    void testRecuperarTodos() {

    }

    @Test
    void testRegravar() {

    }
}
