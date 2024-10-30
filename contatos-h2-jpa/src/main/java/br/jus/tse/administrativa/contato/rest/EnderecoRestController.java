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
import br.jus.tse.administrativa.contato.Endereco;
import br.jus.tse.administrativa.contato.EnderecoService;
import br.jus.tse.administrativa.contato.GestaoDeContatosSevice;

@RestController
@RequestMapping("/contatos")
public class EnderecoRestController {
    //TO DO: TRATAR ERROS DE QUANDO O ELEMENTO NÃO É ENCONTRADO

    private EnderecoService enderecoService;
    private GestaoDeContatosSevice gestaoContatos;

    public EnderecoRestController(EnderecoService enderecoService, GestaoDeContatosSevice gestaoDeContatosSevice){
        this.enderecoService = enderecoService;
        this.gestaoContatos = gestaoDeContatosSevice;
    }

    //ok
    @PostMapping(value="/{id_dono}/enderecos", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> adicionar(@PathVariable("id_dono") Long idDono, @RequestBody EnderecoRequest enderecoRequest) {
        
         if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        Set<String> validations = enderecoRequest.isValidOnStage(ValidOnCreation.class);
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }


        Map<String,String> executar = new HashMap<>();

        ContatoPessoal dono = this.gestaoContatos.pegaUm(idDono);
        Endereco endereco = enderecoRequest.trasforma(dono);


        //to do: definir catch de erro endereco
        // erros: endereco já existente
        enderecoService.criarEndereco(endereco);
        executar.put("msg", "endereco criado");
        return new ResponseEntity<>(executar, HttpStatus.OK);
    }

    
    @GetMapping(value="/{id_dono}/enderecos", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos(@PathVariable("id_dono") Long idDono) {
        
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        
        List<Endereco> enderecos = enderecoService.buscarTodos(); 
        
        return ResponseEntity.ok().header("Custom-Header", "foo").body(enderecos);
    }

    @GetMapping(value="/{id_dono}/enderecos/{id_endereco}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable("id_dono") Long idDono,@PathVariable("id_endereco") Long id) {
        
        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        Optional<Endereco> possivelEndereco = enderecoService.buscarPorId(id);
        if (possivelEndereco.isPresent()) {
            return ResponseEntity.ok().header("Custom-Header", "foo").body(possivelEndereco.get());
        }
        return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
    }


    @PutMapping(value="/{id_dono}/enderecos/{id_endereco}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> atualizar(@PathVariable("id_dono") Long idDono, @RequestBody EnderecoRequest enderecoRequest, @PathVariable("id_endereco") Long id) {
        
        Set<String> validations = enderecoRequest.isValidOnStage(ValidOnUpdate.class);

        if (idDono == null) {
            return ResponseEntity.badRequest().body("id_dono está inválido");
        }
        if (!validations.isEmpty()) {
            return ResponseEntity.badRequest().body(validations);
        }

        ContatoPessoal dono = this.gestaoContatos.pegaUm(idDono);
        Endereco endereco = enderecoRequest.trasforma(id,dono);
        Optional<Endereco> possivelEndereco = enderecoService.buscarPorId(id);
        if (possivelEndereco.isPresent()) {
            enderecoService.atualizarEndereco(endereco);
            return ResponseEntity.ok().header("Custom-Header", "foo").body(possivelEndereco.get());
        }
        return ResponseEntity.notFound().header("not-found-id", String.valueOf(id)).build();
        
    }

    @DeleteMapping(value="/{id_dono}/enderecos/{id_endereco}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> excluir(@PathVariable("id_dono") Long idDono, @RequestBody EnderecoRequest enderecoRequest, @PathVariable("id_endereco") Long id) {

        ContatoPessoal dono = this.gestaoContatos.pegaUm(idDono);
        Endereco endereco = enderecoRequest.trasforma(id,dono);
        //to do: definir catch de erro endereco
        try {
            enderecoService.deletarEndereco(endereco);
            return ResponseEntity.ok().header("Custom-Header", "foo").body("Excluido o id " + id);
        } catch (Exception e) {
            return ResponseEntity.ok().header("Custom-Header", "foo").body("Excluido o id " + id);
        }
        
        
        
    }



}
