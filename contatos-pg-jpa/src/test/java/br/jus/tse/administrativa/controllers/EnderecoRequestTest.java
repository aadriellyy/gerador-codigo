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
import br.jus.tse.administrativa.contato.EnderecoService;
import br.jus.tse.administrativa.contato.Endereco;
import br.jus.tse.administrativa.contato.rest.EnderecoRestController;
import br.jus.tse.administrativa.exceptions.CustomException;

@WebMvcTest(EnderecoRestController.class)
class EnderecoRequestTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EnderecoService enderecoService;

	@MockBean
	private ContatoPessoalService contatoPessoalService;

	@Test 
	void buscarTodosTest() throws Exception {

		List<Endereco> enderecos = new ArrayList<>();
		
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cep = "65082-722";
        String logradouro = "Vila Nova"; 
        String complemento = "Casa 404";
		Endereco endereco = new Endereco(id,cep,logradouro,complemento,dono);
		enderecos.add(endereco);


		when(enderecoService.buscarTodos()).thenReturn(enderecos);

		this.mockMvc.perform(get("/contatos/1/enderecos"))
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
        String cep = "65082-722";
        String logradouro = "Vila Nova"; 
        String complemento = "Casa 404";
		Endereco endereco = new Endereco(id,cep,logradouro,complemento,dono);

		when(enderecoService.buscarPorId(id)).thenReturn(Optional.of(endereco));
		this.mockMvc.perform(get("/contatos/1/enderecos/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.cep").value("65082-722"))
				.andExpect(jsonPath("$.logradouro").value("Vila Nova"))
				.andExpect(jsonPath("$.complemento").value("Casa 404"));
	}

	@Test
	void criarTest() throws Exception {
		Long idDono = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
		long id = 3;
        String cep = "65082-722";
        String logradouro = "Vila Nova"; 
        String complemento = "Casa 404";
		Endereco aux = new Endereco(cep,logradouro,complemento,dono);
		Endereco endereco = new Endereco(id,cep,logradouro,complemento,dono);

		String enderecoJson = "{ \"cep\": \"65082-722\", \"logradouro\": \"Vila Nova\", \"complemento\": \"Casa 404\" }";

		when(enderecoService.criarEndereco(aux)).thenReturn(endereco);
		when(contatoPessoalService.buscarPorId(idDono)).thenReturn(Optional.of(dono));

		this.mockMvc.perform(post("/contatos/1/enderecos").contentType(MediaType.APPLICATION_JSON).content(enderecoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cep").value("65082-722"))
				.andExpect(jsonPath("$.logradouro").value("Vila Nova"))
				.andExpect(jsonPath("$.complemento").value("Casa 404"));
	}

	@Test
	void atualizarTest() throws Exception {
		Long idDono = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cep = "65082-722";
        String logradouro = "Vila Nova"; 
        String complemento = "Casa 404";
		Endereco endereco = new Endereco(id,cep,logradouro,complemento,dono);

		String enderecoJson = "{ \"id\": \"3\", \"cep\": \"65000-722\", \"logradouro\": \"Vila Novissima\", \"complemento\": \"Casa 200\" }";

		when(enderecoService.buscarPorId(id)).thenReturn(Optional.of(endereco));
		when(contatoPessoalService.buscarPorId(idDono)).thenReturn(Optional.of(dono));

		this.mockMvc.perform(put("/contatos/1/enderecos").contentType(MediaType.APPLICATION_JSON).content(enderecoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.cep").value("65000-722"))
				.andExpect(jsonPath("$.logradouro").value("Vila Novissima"))
				.andExpect(jsonPath("$.complemento").value("Casa 200"));
	}

	@Test
	void deletarTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
        ContatoPessoal dono = new ContatoPessoal(nome,cpf,nascimento);
        long id = 3;
        String cep = "65082-722";
        String logradouro = "Vila Nova"; 
        String complemento = "Casa 404";
		Endereco endereco = new Endereco(id,cep,logradouro,complemento,dono);

		when(enderecoService.buscarPorId(id)).thenReturn(Optional.of(endereco));

		this.mockMvc.perform(delete("/contatos/1/enderecos/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.cep").value("65082-722"))
				.andExpect(jsonPath("$.logradouro").value("Vila Nova"))
				.andExpect(jsonPath("$.complemento").value("Casa 404"));
	}

	@Test
	void errorTest() throws Exception {
        long id = 3;
		when(enderecoService.buscarPorId(id)).thenThrow(new CustomException("testando"));

		this.mockMvc.perform(delete("/contatos/1/enderecos/3"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Erro interno: /contatos/1/enderecos/3"));
	}


}