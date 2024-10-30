package br.jus.tse.administrativa.contato.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/")
public class OlaRestController {
    private static final  String TOKEN_CLIMA_TEMPO = "";
    
    @GetMapping(value="/codigos", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> cidades() {
        RestClient defaultClient = RestClient.create();
        String result = defaultClient.get() 
                .uri("http://apiadvisor.climatempo.com.br/api/v1/locale/city?province=df&token=".concat(TOKEN_CLIMA_TEMPO)) 
                .retrieve() 
                .body(String.class); 

        
        return new ResponseEntity<>(result, HttpStatus.OK);
        
    }
    
    @GetMapping(value="/previsao", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> previsaoDoClima(@RequestParam("code") String codigoDaCidade) {
        String url = String.format("http://apiadvisor.climatempo.com.br/api/v1/forecast/locale/%s/days/15?token=%s", codigoDaCidade, TOKEN_CLIMA_TEMPO);
        
        RestClient defaultClient = RestClient.create();
        String result = defaultClient.get() 
                .uri(url) 
                .retrieve() 
                .body(String.class); 

        
        return new ResponseEntity<>(result, HttpStatus.OK);
        
    }
    
    @GetMapping(value="/ativo", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String,String>> ativo() {
        Map<String,String> response = new HashMap<>();
        response.put("msg", "Estou escutando");
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }
}
