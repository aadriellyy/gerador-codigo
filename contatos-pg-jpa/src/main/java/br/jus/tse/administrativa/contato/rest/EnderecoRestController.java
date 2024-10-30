package br.jus.tse.administrativa.contato.rest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.Endereco;
import br.jus.tse.administrativa.contato.EnderecoService;
import br.jus.tse.administrativa.contato.ContatoPessoalService;


@RestController
@RequestMapping("/contatos")
public class EnderecoRestController {
    //TO DO: TRATAR ERROS DE QUANDO O ELEMENTO NÃO É ENCONTRADO

    private EnderecoService enderecoService;
    private ContatoPessoalService contatoPessoalService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoRestController.class);

    public EnderecoRestController(EnderecoService enderecoService, ContatoPessoalService contatoPessoalService){
        this.enderecoService = enderecoService;
        this.contatoPessoalService = contatoPessoalService;
    }

    @PostMapping(value="/{id_dono}/enderecos", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> adicionar(@PathVariable("id_dono") Long idDono, @RequestBody EnderecoRequest enderecoRequest) {

        LOGGER.info("Iniciando criação de endereço para morador com ID: {}", idDono);

         if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        Set<String> validations = enderecoRequest.isValidOnStage(ValidOnCreation.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }


        Optional<ContatoPessoal> possivelDono = this.contatoPessoalService.buscarPorId(idDono);
        ContatoPessoal dono = possivelDono.get();
        Endereco endereco = enderecoRequest.transforma(dono);
        endereco = enderecoService.criarEndereco(endereco);
        LOGGER.info("Endereço criado para morador com ID: {}", idDono);
        return ResponseEntity.ok().header("Custom-Header", "foo").body(endereco);
    }

    
    @GetMapping(value="/{id_dono}/enderecos", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos(@PathVariable("id_dono") Long idDono) {
        LOGGER.info("Buscando todos os endereços");

        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        List<Endereco> enderecos = enderecoService.buscarTodos(); 
        LOGGER.info("Endereços encontrados: {}", enderecos.size());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(enderecos);
    }

    @GetMapping(value="/{id_dono}/enderecos/{id_endereco}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable("id_dono") Long idDono,@PathVariable("id_endereco") Long id) {
        LOGGER.info("Buscando endereço de id: {}", id);

        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        Optional<Endereco> possivelEndereco = enderecoService.buscarPorId(id);
        Endereco endereco = possivelEndereco.get();
        LOGGER.info("endereço encontrado com sucesso: {}", endereco.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(endereco);
    }


    @PutMapping(value="/{id_dono}/enderecos",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> atualizar(@PathVariable("id_dono") Long idDono, @RequestBody EnderecoRequest enderecoRequest) {
        LOGGER.info("Iniciando atualização de endereco de id: {}", enderecoRequest.getId());

        Set<String> validations = enderecoRequest.isValidOnStage(ValidOnUpdate.class);
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        Optional<ContatoPessoal> possivelDono = this.contatoPessoalService.buscarPorId(idDono);
        Optional<Endereco> possivelEndereco = enderecoService.buscarPorId(enderecoRequest.getId());
        if (possivelEndereco.isPresent()) {
            Endereco endereco = enderecoRequest.transforma(possivelDono.get());
            enderecoService.atualizarEndereco(endereco);
            LOGGER.info("endereco atualizado com sucesso: {}", endereco.logString());
            return ResponseEntity.ok().header("Custom-Header", "foo").body(endereco);
        }else{
            LOGGER.info("endereco não encontrado com id: {}", enderecoRequest.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("not-found-id", String.valueOf(enderecoRequest.getId())).body("Email não encontrado com id:" + enderecoRequest.getId());
        }
    }

    @DeleteMapping(value="/{id_dono}/enderecos/{id_endereco}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> excluir(@PathVariable("id_dono") Long idDono, @PathVariable("id_endereco") Long id) {
        LOGGER.info("Iniciando deleção de endereco de id: {}", id);
        Optional<Endereco> possivelEndereco = enderecoService.buscarPorId(id);
        Endereco endereco = possivelEndereco.get();
        enderecoService.deletarEndereco(endereco);
        LOGGER.info("endereco deletado com sucesso: {}", endereco.logString());
        return ResponseEntity.ok().header("Custom-Header", "foo").body(endereco);
    }

}
