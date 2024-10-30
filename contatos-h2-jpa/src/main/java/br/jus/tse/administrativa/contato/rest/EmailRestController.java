package br.jus.tse.administrativa.contato.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import br.jus.tse.administrativa.contato.GestaoDeContatosSevice;


@RestController
@RequestMapping("/contatos")
public class EmailRestController {

    private EmailService emailService;
    private GestaoDeContatosSevice gestaoContatos;
    
    

    public EmailRestController(EmailService emailService, GestaoDeContatosSevice gestaoDeContatosSevice){
        this.emailService = emailService;
        this.gestaoContatos = gestaoDeContatosSevice;
    }

    @PostMapping(value="/{id_dono}/emails", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> adicionar(@PathVariable("id_dono") Long idDono, @RequestBody EmailRequest emailRequest) {
        
         if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        Set<String> validations = emailRequest.isValidOnStage(ValidOnCreation.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }


        Map<String,String> executar = new HashMap<>();

        ContatoPessoal dono = this.gestaoContatos.pegaUm(idDono);
        Email email = emailRequest.trasforma(dono);


        //to do: definir catch de erro email
        // erros: email já existente
        emailService.criarEmail(email);
        executar.put("msg", "email criado");
        return new ResponseEntity<>(executar, HttpStatus.OK);
    }

    
    @GetMapping(value="/{id_dono}/emails", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos(@PathVariable("id_dono") Long idDono) {
        
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        List<Email> emails = emailService.buscarTodos(); 
        
        return ResponseEntity.ok().header("Custom-Header", "foo").body(emails);
    }

    @GetMapping(value="/{id_dono}/emails/{id_email}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable("id_dono") Long idDono,@PathVariable("id_email") Long id) {
        
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        Optional<Email> possivelEmail = emailService.buscarPorId(id);
        if (possivelEmail.isPresent()) {
            return ResponseEntity.ok().header("Custom-Header", "foo").body(possivelEmail.get());
        }
        return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
    }


    @PutMapping(value="/{id_dono}/emails/{id_email}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> atualizar(@PathVariable("id_dono") Long idDono, @RequestBody EmailRequest emailRequest, @PathVariable("id_email") Long id) {
        
        Set<String> validations = emailRequest.isValidOnStage(ValidOnUpdate.class);

        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        ContatoPessoal dono = this.gestaoContatos.pegaUm(idDono);
        Email email = emailRequest.trasforma(id,dono);
        Optional<Email> possivelEmail = emailService.buscarPorId(id);
        if (possivelEmail.isPresent()) {
            emailService.atualizarEmail(email);
            return ResponseEntity.ok().header("Custom-Header", "foo").body(possivelEmail.get());
        }
        return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        
    }

    @DeleteMapping(value="/{id_dono}/emails/{id_email}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> excluir(@PathVariable("id_dono") Long idDono, @RequestBody EmailRequest emailRequest, @PathVariable("id_email") Long id) {

        ContatoPessoal dono = this.gestaoContatos.pegaUm(idDono);
        Email email = emailRequest.trasforma(id,dono);
        //to do: definir catch de erro email
        try {
            emailService.deletarEmail(email);
            return ResponseEntity.ok().header("Custom-Header", "foo").body("Excluido o id " + id);
        } catch (Exception e) {
            return ResponseEntity.ok().header("Custom-Header", "foo").body("Excluido o id " + id);
        }
        
        
        
    }
    

}

// PUT NÃO ESTÃO REALMENTE DELETANDO DO H2