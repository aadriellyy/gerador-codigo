   






  /*
* Created on 2024-10-30 ( 11:31:29 )
* Generated by Telosys ( https://www.telosys.org/ ) version 4.1.0
*/ 








    
package br.jus.tse.administrativa.rest;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

                        
import br.jus.tse.administrativa.PessoaFisica;
import br.jus.tse.administrativa.PessoaFisicaService;

import br.jus.tse.administrativa.Email;
import br.jus.tse.administrativa.EmailService;


@RestController
@RequestMapping("//pessoafisica")
public class EmailRestController {

    private EmailService service;
    private PessoaFisicaService pessoafisicaService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailRestController.class);

    public EmailRestController(EmailService service, PessoaFisicaService pessoafisicaService) {
        this.service = service;
        this.pessoafisicaService = pessoafisicaService;
    }

    @PostMapping(value="/{id_pessoafisica}/Email", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<?> save(@PathVariable("id_pessoafisica") Long idPessoaFisica, @RequestBody EmailRequest request){
        LOGGER.info("Iniciando criação de um novo Email para PessoaFisica com ID: {}", idPessoaFisica);
        if (idPessoaFisica == null){
            return ResponseEntity.badRequest().body("idPessoaFisica está inválido");
        }

        Set< String > validations = request.isValidOnStage(ValidOnCreation.class);
        if(request == null){
            return ResponseEntity.badRequest().body("Não há dados para serem cadastrados");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<PessoaFisica> possivelPessoaFisica = this.pessoafisicaService.findById(idPessoaFisica);
        if(possivelPessoaFisica.isPresent()){
            PessoaFisica pessoafisica = possivelPessoaFisica.get();
            Email object = request.transform(pessoafisica);
            service.save(object);
            LOGGER.info("Email criado para PessoaFisica com sucesso!");
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        }
        else{
            LOGGER.info("pessoafisica não encontrado com ID: {}", idPessoaFisica);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idPessoaFisica)).build();
        }

    }

    @GetMapping(value="/{id_pessoafisica}/Email", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findAll(@PathVariable("id_pessoafisica") Long idPessoaFisica) {
        LOGGER.info("Buscando lista de Email");

        if (idPessoaFisica == null){
            return ResponseEntity.badRequest().body("idPessoaFisica está inválido");
        }

        List<Email> list = service.findAll();
        LOGGER.info("Email encontrados: {}", list.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(list);
    }

    @GetMapping(value="/{id_pessoafisica}/Email/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id_pessoafisica") Long idPessoaFisica, @PathVariable("id") Long id){
        LOGGER.info("Buscando Email por id: {}", id);

        if (idPessoaFisica == null){
            return ResponseEntity.badRequest().body("idPessoaFisica está inválido");
        }

        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<Email> object = service.findById(id);
        if (object.isPresent()) {
            LOGGER.info("Email encontrado com sucesso: {}");
            return ResponseEntity.ok().header("Custom-header", "foo").body(object.get());
        }else{
            LOGGER.info("Email não encontrado pelo id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }
       
    @PutMapping(value="/{id_pessoafisica}/Email", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id_pessoafisica") Long idPessoaFisica, @RequestBody EmailRequest request) {
        LOGGER.info("Iniciando atualização de Email pelo id: {}", request.getIdEmail());

        Set< String > validations = request.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<PessoaFisica> possivelPessoaFisica = this.pessoafisicaService.findById(idPessoaFisica);
        Optional<Email> possivelObject = service.findById(request.getIdEmail());
        if(possivelObject.isPresent()){
            Email object = request.transform(possivelPessoaFisica.get());
            service.update(object);
            LOGGER.info("Email atualizado(a) com sucesso: {}", object.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
        } else{
            LOGGER.info("Email não encontrado(a) pelo id: {}", request.getIdEmail());
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(request.getIdEmail())).build();
        }
    }


    @DeleteMapping(value="/{id_pessoafisica}/Email/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delete(@PathVariable("id_pessoafisica") Long idPessoaFisica, @PathVariable("id") Long id) {
        LOGGER.info("Iniciando deleção de Email pelo id: {}", id);
        Optional<Email> possivelObject = service.findById(id);
        Email object = possivelObject.get();
        service.delete(object);
        LOGGER.info("Email deletado(a) com sucesso: {}", object.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(object);
    }

}


