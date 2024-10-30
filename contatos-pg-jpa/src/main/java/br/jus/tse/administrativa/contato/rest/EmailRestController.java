package br.jus.tse.administrativa.contato.rest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.Email;
import br.jus.tse.administrativa.contato.EmailService;
import br.jus.tse.administrativa.contato.ContatoPessoalService;


@RestController
@RequestMapping("/contatos")
public class EmailRestController {

    private EmailService emailService;
    private ContatoPessoalService contatoPessoalService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailRestController.class);
    

    public EmailRestController(EmailService emailService, ContatoPessoalService contatoPessoalService){
        this.emailService = emailService;
        this.contatoPessoalService = contatoPessoalService;
    }

    @PostMapping(value="/{id_dono}/emails", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> adicionar(@PathVariable("id_dono") Long idDono, @RequestBody EmailRequest emailRequest) {

        LOGGER.info("Iniciando criação de email para dono com ID: {}", idDono);
         if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        Set<String> validations = emailRequest.isValidOnStage(ValidOnCreation.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<ContatoPessoal> possivelDono = this.contatoPessoalService.buscarPorId(idDono);
        ContatoPessoal dono = possivelDono.get();
        Email email = emailRequest.transforma(dono);
        email = emailService.criarEmail(email);
        LOGGER.info("Email criado para dono com ID: {}", idDono);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(email);
    }

    
    @GetMapping(value="/{id_dono}/emails", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos(@PathVariable("id_dono") Long idDono) {
        LOGGER.info("Buscando todos os emails");
        
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        List<Email> emails = emailService.buscarTodos(); 
        LOGGER.info("Emails encontrados: {}", emails.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(emails);
    }

    @GetMapping(value="/{id_dono}/emails/{id_email}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable("id_dono") Long idDono,@PathVariable("id_email") Long id) {
        LOGGER.info("Buscando email de id: {}", id);
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        Optional<Email> possivelEmail = emailService.buscarPorId(id);
        Email email = possivelEmail.get();
        LOGGER.info("email encontrado com sucesso: {}", email.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(email);
    }


    @PutMapping(value="/{id_dono}/emails",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> atualizar(@PathVariable("id_dono") Long idDono, @RequestBody EmailRequest emailRequest) {
        LOGGER.info("Iniciando atualização de email de id: {}", emailRequest.getId());

        Set<String> validations = emailRequest.isValidOnStage(ValidOnUpdate.class);
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<ContatoPessoal> possivelDono = this.contatoPessoalService.buscarPorId(idDono);
        Optional<Email> possivelEmail = emailService.buscarPorId(emailRequest.getId());
        if (possivelEmail.isPresent()) {
            Email email = emailRequest.transforma(possivelDono.get());
            emailService.atualizarEmail(email);
            LOGGER.info("email atualizado com sucesso: {}", email.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(email);
        }else{
            LOGGER.info("email não encontrado com id: {}", emailRequest.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("not-found-id", String.valueOf(emailRequest.getId())).body("Email não encontrado com id:" + emailRequest.getId());
        }
    }

    @DeleteMapping(value="/{id_dono}/emails/{id_email}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> excluir(@PathVariable("id_dono") Long idDono, @PathVariable("id_email") Long id) {
        LOGGER.info("Iniciando deleção de email de id: {}", id);

        Optional<Email> possivelEmail = emailService.buscarPorId(id);
        Email email = possivelEmail.get();
        emailService.deletarEmail(email);
        LOGGER.info("email deletado com sucesso: {}", email.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(email);
    }
    

}