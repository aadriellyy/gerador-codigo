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
import br.jus.tse.administrativa.contato.Telefone;
import br.jus.tse.administrativa.contato.TelefoneService;
import br.jus.tse.administrativa.contato.ContatoPessoalService;


@RestController
@RequestMapping("/contatos")
public class TelefoneRestController {

    private TelefoneService telefoneService;
    private ContatoPessoalService contatoPessoalService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TelefoneRestController.class);
    

    public TelefoneRestController(TelefoneService telefoneService, ContatoPessoalService contatoPessoalService){
        this.telefoneService = telefoneService;
        this.contatoPessoalService = contatoPessoalService;
    }

    @PostMapping(value="/{id_dono}/telefones", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> adicionar(@PathVariable("id_dono") Long idDono, @RequestBody TelefoneRequest telefoneRequest) {

        LOGGER.info("Iniciando criação de telefone para dono com ID: {}", idDono);
         if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        Set<String> validations = telefoneRequest.isValidOnStage(ValidOnCreation.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<ContatoPessoal> possivelDono = this.contatoPessoalService.buscarPorId(idDono);
        if(possivelDono.isPresent()){
            ContatoPessoal dono = possivelDono.get();
            Telefone telefone = telefoneRequest.transforma(dono);
            telefone = telefoneService.adicionar(telefone);
            LOGGER.info("Telefone criado para dono com ID: {}", idDono);
            return ResponseEntity.ok().header("Custom-Header", "foo").body(telefone);
        }else{
            LOGGER.info("Dono não encontrado com ID: {}", idDono);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(idDono)).build();
        }
    }

    
    @GetMapping(value="/{id_dono}/telefones", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos(@PathVariable("id_dono") Long idDono) {
        LOGGER.info("Buscando todos os telefones");
        
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        List<Telefone> telefones = telefoneService.buscarTodos(); 
        LOGGER.info("Telefones encontrados: {}", telefones.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(telefones);
    }

    @GetMapping(value="/{id_dono}/telefones/{id_telefone}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable("id_dono") Long idDono,@PathVariable("id_telefone") Long id) {
        LOGGER.info("Buscando telefone de id: {}", id);
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        Optional<Telefone> possivelTelefone = telefoneService.buscarPorId(id);
        if (possivelTelefone.isPresent()) {
            Telefone telefone = possivelTelefone.get();
            LOGGER.info("telefone encontrado com sucesso: {}", telefone.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(telefone);
        }else{
            LOGGER.info("telefone não encontrado de id: {}", id);
            return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        }
    }


    @PutMapping(value="/{id_dono}/telefones",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> atualizar(@PathVariable("id_dono") Long idDono, @RequestBody TelefoneRequest telefoneRequest) {
        LOGGER.info("Iniciando atualização de telefone de id: {}", telefoneRequest.getId());

        Set<String> validations = telefoneRequest.isValidOnStage(ValidOnUpdate.class);
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<ContatoPessoal> possivelDono = this.contatoPessoalService.buscarPorId(idDono);
        Optional<Telefone> possivelTelefone = telefoneService.buscarPorId(telefoneRequest.getId());
        if (possivelTelefone.isPresent()) {
            Telefone telefone = telefoneRequest.transforma(possivelDono.get());
            telefoneService.atualizarTelefone(telefone);
            LOGGER.info("telefone atualizado com sucesso: {}", telefone.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(telefone);
        }else{
            LOGGER.info("telefone não encontrado com id: {}", telefoneRequest.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("not-found-id", String.valueOf(telefoneRequest.getId())).body("Email não encontrado com id:" + telefoneRequest.getId());
        }
    }

    @DeleteMapping(value="/{id_dono}/telefones/{id_telefone}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> excluir(@PathVariable("id_dono") Long idDono, @PathVariable("id_telefone") Long id) {
        LOGGER.info("Iniciando deleção de telefone de id: {}", id);
        Optional<Telefone> possivelTelefone = telefoneService.buscarPorId(id);
        Telefone telefone = possivelTelefone.get();
        telefoneService.deletarTelefone(telefone);
        LOGGER.info("telefone deletado com sucesso: {}", telefone.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(telefone);
    }
    

}