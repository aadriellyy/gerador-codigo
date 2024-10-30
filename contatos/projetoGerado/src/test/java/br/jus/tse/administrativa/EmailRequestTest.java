  






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

import br.jus.tse.administrativa.Email;
import br.jus.tse.administrativa.EmailService;
import br.jus.tse.administrativa.rest.EmailRestController;

@WebMvcTest(EmailRestController.class)
public class EmailRequestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService repository;

    @Test
    void findAllTest() throws Exception {

        List<Email> listEmail = new ArrayList<>();

        
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

                                                                                                                          
        Long idEmail = (long) 1;

          
        String cxEmail = "texto a ser definido";

          
        Long fkidPessoa = (long) 2;

                                                          
        Email objEmail = new Email( idEmail,     cxEmail,     objPessoaFisica     );

        
        listEmail.add(objEmail);


        when(repository.findAll()).thenReturn(listEmail);

        this.mockMvc.perform(get("///email"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(idEmail));
    }

    @Test
    void findByIdTest() throws Exception {

    
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

     
        Long idEmail = (long) 1;

          
        String cxEmail = "texto a ser definido";

      
        Long fkidPessoa = (long) 2;

     
                                             
        Email objEmail = new Email( idEmail,     cxEmail,     objPessoaFisica     );

    
        when(repository.findById(idEmail)).thenReturn(Optional.of(objEmail));
        this.mockMvc.perform(get("//email/idEmail"))
            .andExpect(status().isOk())
         
            .andExpect(jsonPath("$.idEmail").value(1)) 
         
            .andExpect(jsonPath("$.cxEmail").value("texto a ser definido")) 
         
            .andExpect(jsonPath("$.fkidPessoa").value(2));

        
        
}

    @Test
    void saveTest() throws Exception {

        
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

          
        Long idEmail = (long) 1;

           
        String cxEmail = "texto a ser definido";

           
        Long fkidPessoa = (long) 2;

         
                                             
        Email objEmail = new Email( idEmail,     cxEmail,     objPessoaFisica     );

    
        String objEmailJson = "{ defina o corpo do objeto em formato Json}";

        this.mockMvc.perform(post("//email").contentType(MediaType.APPLICATION_JSON).content(objEmailJson))
            .andExpect(status().isOk())

             
            .andExpect(jsonPath("$.idEmail").value(1)) 
             
            .andExpect(jsonPath("$.cxEmail").value("texto a ser definido")) 
             
            .andExpect(jsonPath("$.fkidPessoa").value(2));

            
                }

    @Test
    void updateTest() throws Exception {

    
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

     
        Long idEmail = (long) 1;

          
        String cxEmail = "texto a ser definido";

      
        Long fkidPessoa = (long) 2;

         
                                             
        Email objEmail = new Email( idEmail,     cxEmail,     objPessoaFisica     );
    
        String objEmailJson = "{ defina o corpo do objeto em formato Json}";

        when(repository.findById(idEmail)).thenReturn(Optional.of(objEmail));

        this.mockMvc.perform(put("//email").contentType(MediaType.APPLICATION_JSON).content(objEmailJson))
            .andExpect(status().isOk())

             
            .andExpect(jsonPath("$.idEmail").value(1)) 
             
            .andExpect(jsonPath("$.cxEmail").value("texto a ser definido")) 
             
            .andExpect(jsonPath("$.fkidPessoa").value(2));

            
                }

    @Test
    void deleteTest() throws Exception {

    
         
         
                                                                                                        
         
        String nomeCompleto = "texto a ser definido";

          
        LocalDate dtNascimento = LocalDate.of(2010, 10, 10);

          
        String nrCpf = "texto a ser definido";

         
        PessoaFisica objPessoaFisica = new PessoaFisica( nomeCompleto,   dtNascimento,   nrCpf  );

     

     
        Long idEmail = (long) 1;

      
        String cxEmail = "texto a ser definido";

      
        Long fkidPessoa = (long) 2;

     
                                             
        Email objEmail = new Email( idEmail,     cxEmail,     objPessoaFisica     );

    
        when(repository.findById(idEmail)).thenReturn(Optional.of(objEmail));

        this.mockMvc.perform(delete("//email/idEmail"))
            .andExpect(status().isOk())

             
            .andExpect(jsonPath("$.idEmail").value(1)) 
             
            .andExpect(jsonPath("$.cxEmail").value("texto a ser definido")) 
             
            .andExpect(jsonPath("$.fkidPessoa").value(2));

            
                }


}

                                                                                                                                                            

*/