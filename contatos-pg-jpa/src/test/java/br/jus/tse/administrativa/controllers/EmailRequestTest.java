package br.jus.tse.administrativa.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.ContatoPessoalService;
import br.jus.tse.administrativa.contato.EmailService;
import br.jus.tse.administrativa.contato.Email;
import br.jus.tse.administrativa.contato.rest.EmailRestController;
import br.jus.tse.administrativa.exceptions.CustomException;

@WebMvcTest(EmailRestController.class)
public class EmailRequestTest {
    @Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmailService emailService;

	@MockBean
	private ContatoPessoalService contatoPessoalService;

	@Test 
	void buscarTodosTest() throws Exception {

		List<Email> emails = new ArrayList<>();


		
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cx_email = "gakosim917@rinseart.com";
		Email email = new Email(id,cx_email,dono);
		emails.add(email);


		when(emailService.buscarTodos()).thenReturn(emails);

		this.mockMvc.perform(get("/contatos/1/emails"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));
	}

	@Test
	void buscarPorIdTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cx_email = "gakosim917@rinseart.com";
		Email email = new Email(id,cx_email,dono);

		when(emailService.buscarPorId(id)).thenReturn(Optional.of(email));
		this.mockMvc.perform(get("/contatos/1/emails/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.email").value("gakosim917@rinseart.com"));
	}

	@Test
	void criarTest() throws Exception {
		Long idDono = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
		long id = 3;
        String cx_email = "gakosim921@rinseart.com";
		Email aux = new Email(cx_email,dono);
		Email email = new Email(id,cx_email,dono);

		String emailJson = "{ \"email\": \"gakosim921@rinseart.com\"}";

		when(contatoPessoalService.buscarPorId(idDono)).thenReturn(Optional.of(dono));
		when(emailService.criarEmail(aux)).thenReturn(email);

		this.mockMvc.perform(post("/contatos/1/emails").contentType(MediaType.APPLICATION_JSON).content(emailJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value("gakosim921@rinseart.com"));
	}

	@Test
	void atualizarTest() throws Exception {
		Long idDono = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cx_email = "gakosim917@rinseart.com";
		Email email = new Email(id,cx_email,dono);

		String emailJson = "{ \"id\": \"3\", \"email\": \"gakosim921@rinseart.com\"}";
		when(emailService.buscarPorId(id)).thenReturn(Optional.of(email));
		when(contatoPessoalService.buscarPorId(idDono)).thenReturn(Optional.of(dono));

		this.mockMvc.perform(put("/contatos/1/emails").contentType(MediaType.APPLICATION_JSON).content(emailJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.email").value("gakosim921@rinseart.com"));
	}

	@Test
	void deletarTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cx_email = "gakosim917@rinseart.com";
		Email email = new Email(id,cx_email,dono);

		when(emailService.buscarPorId(id)).thenReturn(Optional.of(email));

		this.mockMvc.perform(delete("/contatos/1/emails/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.email").value("gakosim917@rinseart.com"));
	}

	@Test
	void errorTest() throws Exception {
        long id = 3;
		when(emailService.buscarPorId(id)).thenThrow(new CustomException("testando"));

		this.mockMvc.perform(delete("/contatos/1/emails/3"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Erro interno: /contatos/1/emails/3"));
	}


}
