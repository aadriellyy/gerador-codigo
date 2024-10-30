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
import br.jus.tse.administrativa.contato.TelefoneService;
import br.jus.tse.administrativa.contato.Telefone;
import br.jus.tse.administrativa.contato.rest.TelefoneRestController;
import br.jus.tse.administrativa.exceptions.CustomException;

@WebMvcTest(TelefoneRestController.class)
public class TelefoneRequestTest {
        @Autowired
	private MockMvc mockMvc;

	@MockBean
	private TelefoneService telefoneService;

	@MockBean
	private ContatoPessoalService contatoPessoalService;

	@Test 
	void buscarTodosTest() throws Exception {

		List<Telefone> telefones = new ArrayList<>();


		
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String nr_telefone = "(27) 98972-6441";
		Telefone telefone = new Telefone(id,nr_telefone,dono);
		telefones.add(telefone);


		when(telefoneService.buscarTodos()).thenReturn(telefones);

		this.mockMvc.perform(get("/contatos/1/telefones"))
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
        String nr_telefone = "(27) 98972-6441";
		Telefone telefone = new Telefone(id,nr_telefone,dono);

		when(telefoneService.buscarPorId(id)).thenReturn(Optional.of(telefone));
		this.mockMvc.perform(get("/contatos/1/telefones/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.telefone").value("(27) 98972-6441"));
	}

	@Test
	void criarTest() throws Exception {
		Long idDono = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
		long id = 3;
        String nr_telefone = "(27) 98972-6441";
		Telefone aux = new Telefone(nr_telefone,dono);
		Telefone telefone = new Telefone(id,nr_telefone,dono);

		String telefoneJson = "{ \"telefone\": \"(27) 98972-6441\"}";

		when(contatoPessoalService.buscarPorId(idDono)).thenReturn(Optional.of(dono));
		when(telefoneService.adicionar(aux)).thenReturn(telefone);

		this.mockMvc.perform(post("/contatos/1/telefones").contentType(MediaType.APPLICATION_JSON).content(telefoneJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.telefone").value("(27) 98972-6441"));
	}

	@Test
	void atualizarTest() throws Exception {
		Long idDono = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String nr_telefone = "(27) 98972-6441";
		Telefone telefone = new Telefone(id,nr_telefone,dono);

		String telefoneJson = "{ \"id\": \"3\", \"telefone\": \"(62) 97670-2135\"}";
		when(telefoneService.buscarPorId(id)).thenReturn(Optional.of(telefone));
		when(contatoPessoalService.buscarPorId(idDono)).thenReturn(Optional.of(dono));

		this.mockMvc.perform(put("/contatos/1/telefones").contentType(MediaType.APPLICATION_JSON).content(telefoneJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.telefone").value("(62) 97670-2135"));
	}

	@Test
	void deletarTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String nr_telefone = "(27) 98972-6441";
		Telefone telefone = new Telefone(id,nr_telefone,dono);

		when(telefoneService.buscarPorId(id)).thenReturn(Optional.of(telefone));

		this.mockMvc.perform(delete("/contatos/1/telefones/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.telefone").value("(27) 98972-6441"));
	}

	@Test
	void errorTest() throws Exception {
        long id = 3;
		when(telefoneService.buscarPorId(id)).thenThrow(new CustomException("testando"));

		this.mockMvc.perform(delete("/contatos/1/telefones/3"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Erro interno: /contatos/1/telefones/3"));
	}


}



