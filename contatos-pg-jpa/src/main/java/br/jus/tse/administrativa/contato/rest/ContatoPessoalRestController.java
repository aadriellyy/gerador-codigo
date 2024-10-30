package br.jus.tse.administrativa.contato.rest;


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

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.ContatoPessoalService;

@RestController
@RequestMapping("/contatos")
public class ContatoPessoalRestController {
    private ContatoPessoalService contatoPessoalService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContatoPessoalRestController.class);
    public ContatoPessoalRestController(ContatoPessoalService contatoPessoalService) {
        this.contatoPessoalService = contatoPessoalService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> adicionar(@RequestBody  ContatoPessoalRequest contatoPessoalRequest) {
        LOGGER.info("Iniciando criação de contato (controller): {}", contatoPessoalRequest.logString());
        Set<String> validations = contatoPessoalRequest.isValidOnStage(ValidOnCreation.class);  
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }
        ContatoPessoal contatoPessoal = contatoPessoalRequest.transforma();
        contatoPessoal = contatoPessoalService.salvar(contatoPessoal);
        LOGGER.info("ContatoPessoal criado (controller): {}",contatoPessoal.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(contatoPessoal);
    }
    
    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos() {
        LOGGER.info("Buscando todos os contatos");
        List<ContatoPessoal> contatos = contatoPessoalService.buscarTodos();
        LOGGER.info("Contatos encontrados (controller): {}", contatos.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(contatos);
        
    }

    @GetMapping(value="/{id_contato}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable("id_contato") Long id) {
        LOGGER.info("Buscando contato de id (controller): {}", id);
        if(id == null){
            return ResponseEntity.badRequest().body("id inválido");
        }

        Optional<ContatoPessoal> possivelContato = contatoPessoalService.buscarPorId(id);
        ContatoPessoal contato = possivelContato.get();
        LOGGER.info("Contato encontrado com sucesso (controller): {}", contato.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(contato);

    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> atualizar(@RequestBody ContatoPessoalRequest contatoPessoalRequest) {
        LOGGER.info("Iniciando atualização de contato de id (controller): {}", contatoPessoalRequest.getId());
        Set<String> validations = contatoPessoalRequest.isValidOnStage(ValidOnUpdate.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }
        Optional<ContatoPessoal> possivelContato = contatoPessoalService.buscarPorId(contatoPessoalRequest.getId());

        ContatoPessoal contato = possivelContato.get();
        contato.transforma(contatoPessoalRequest);
        LOGGER.info("Contato encontrado com sucesso (controller): {}", contato.logString());
        contatoPessoalService.regravar(contato);
        LOGGER.info("Contato atualizado com sucesso (controller): {}", contato.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(contato);
    }

    @DeleteMapping(value="/{id_contato}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> excluir(@PathVariable("id_contato") Long id) {
        LOGGER.info("Iniciando deleção de contato de id: {}", id);
        Optional<ContatoPessoal> possivelContato = contatoPessoalService.buscarPorId(id);
        ContatoPessoal contato = possivelContato.get();
        LOGGER.info("Contato encontrado com sucesso (controller): {}", contato.logString());
        contatoPessoalService.excluir(contato);
        LOGGER.info("Contato deletado com sucesso (controller): {}", contato.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(contato);
    }
}
