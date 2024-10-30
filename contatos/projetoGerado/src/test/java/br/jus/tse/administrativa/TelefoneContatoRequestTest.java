  






 /*
* Created on 2024-10-30 ( 11:31:29 )
* Generated by Telosys ( https://www.telosys.org/ ) version 4.1.0
*/





package br.jus.tse.administrativa;
/*
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.jus.tse.administrativa.TelefoneContato;
import br.jus.tse.administrativa.TelefoneContatoService;
import br.jus.tse.administrativa.rest.TelefoneContatoRestController;

@WebMvcTest(TelefoneContatoRestController.class)
public class TelefoneContatoRequestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TelefoneContatoService repository;

    @Test
    void findAllTest() throws Exception {

        List<TelefoneContato> listTelefoneContato = new ArrayList<>();

        
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

                                                                                                                          
        Long idTelefone = (long) 1;

          
        String nrTelefone = "texto a ser definido";

          
        Long fkidPessoa = (long) 2;

                                                          
        TelefoneContato objTelefoneContato = new TelefoneContato( idTelefone,     nrTelefone,     objPessoaFisica     );

        
        listTelefoneContato.add(objTelefoneContato);


        when(repository.findAll()).thenReturn(listTelefoneContato);

        this.mockMvc.perform(get("///telefonecontato"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(idTelefone));
    }

    @Test
    void findByIdTest() throws Exception {

    
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

     
        Long idTelefone = (long) 1;

          
        String nrTelefone = "texto a ser definido";

      
        Long fkidPessoa = (long) 2;

     
                                             
        TelefoneContato objTelefoneContato = new TelefoneContato( idTelefone,     nrTelefone,     objPessoaFisica     );

    
        when(repository.findById(idTelefone)).thenReturn(Optional.of(objTelefoneContato));
        this.mockMvc.perform(get("//telefonecontato/idTelefone"))
            .andExpect(status().isOk())
         
            .andExpect(jsonPath("$.idTelefone").value(1)) 
         
            .andExpect(jsonPath("$.nrTelefone").value("texto a ser definido")) 
         
            .andExpect(jsonPath("$.fkidPessoa").value(2));

        
        
}

    @Test
    void saveTest() throws Exception {

        
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

          
        Long idTelefone = (long) 1;

           
        String nrTelefone = "texto a ser definido";

           
        Long fkidPessoa = (long) 2;

         
                                             
        TelefoneContato objTelefoneContato = new TelefoneContato( idTelefone,     nrTelefone,     objPessoaFisica     );

    
        String objTelefoneContatoJson = "{ defina o corpo do objeto em formato Json}";

        this.mockMvc.perform(post("//telefonecontato").contentType(MediaType.APPLICATION_JSON).content(objTelefoneContatoJson))
            .andExpect(status().isOk())

             
            .andExpect(jsonPath("$.idTelefone").value(1)) 
             
            .andExpect(jsonPath("$.nrTelefone").value("texto a ser definido")) 
             
            .andExpect(jsonPath("$.fkidPessoa").value(2));

            
                }

    @Test
    void updateTest() throws Exception {

    
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

     
        Long idTelefone = (long) 1;

          
        String nrTelefone = "texto a ser definido";

      
        Long fkidPessoa = (long) 2;

         
                                             
        TelefoneContato objTelefoneContato = new TelefoneContato( idTelefone,     nrTelefone,     objPessoaFisica     );
    
        String objTelefoneContatoJson = "{ defina o corpo do objeto em formato Json}";

        when(repository.findById(idTelefone)).thenReturn(Optional.of(objTelefoneContato));

        this.mockMvc.perform(put("//telefonecontato").contentType(MediaType.APPLICATION_JSON).content(objTelefoneContatoJson))
            .andExpect(status().isOk())

             
            .andExpect(jsonPath("$.idTelefone").value(1)) 
             
            .andExpect(jsonPath("$.nrTelefone").value("texto a ser definido")) 
             
            .andExpect(jsonPath("$.fkidPessoa").value(2));

            
                }

    @Test
    void deleteTest() throws Exception {

    
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

     
        Long idTelefone = (long) 1;

      
        String nrTelefone = "texto a ser definido";

      
        Long fkidPessoa = (long) 2;

     
                                             
        TelefoneContato objTelefoneContato = new TelefoneContato( idTelefone,     nrTelefone,     objPessoaFisica     );

    
        when(repository.findById(idTelefone)).thenReturn(Optional.of(objTelefoneContato));

        this.mockMvc.perform(delete("//telefonecontato/idTelefone"))
            .andExpect(status().isOk())

             
            .andExpect(jsonPath("$.idTelefone").value(1)) 
             
            .andExpect(jsonPath("$.nrTelefone").value("texto a ser definido")) 
             
            .andExpect(jsonPath("$.fkidPessoa").value(2));

            
                }


}

                                                                                                                                                            

*/