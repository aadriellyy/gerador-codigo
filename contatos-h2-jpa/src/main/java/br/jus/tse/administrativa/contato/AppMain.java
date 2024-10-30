package br.jus.tse.administrativa.contato;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.datafaker.Faker;


@Service
public class AppMain implements ApplicationRunner {
    
    private final ContatoPessoalDao contatoDao;
    private final TelefoneDao telefoneDao;
    Faker faker = new Faker();
    
    public AppMain(ContatoPessoalDao repository, TelefoneDao repositoryTelefone ) {
        this.contatoDao = repository;
        this.telefoneDao = repositoryTelefone;
    }
    
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        String nome = faker.name().fullName();
        String cpf = faker.cpf().valid();
        LocalDate nascimento = faker.timeAndDate().birthday();
        
        Telefone telefone = new Telefone(faker.phoneNumber().cellPhone());
        ContatoPessoal pessoal = new ContatoPessoal(nome, cpf.replaceAll("\\D+", ""), nascimento);

        pessoal.addTelefone(telefone);

        this.contatoDao.gravar(pessoal);

        this.telefoneDao.gravar(telefone);

        List<ContatoPessoal>contatos = this.contatoDao.recuperarTodos();
        
        for (ContatoPessoal contato: contatos) {
            System.out.println(contato.getId());
            for (Telefone tel: contato.getTelefones()) {
                System.out.println(tel.getTelefone());
            }
            
        }
    }

}
