package br.jus.tse.administrativa.controllers;

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

import br.jus.tse.administrativa.contato.ContatoPessoal;
import br.jus.tse.administrativa.contato.ContatoPessoalService;
import br.jus.tse.administrativa.contato.rest.ContatoPessoalRestController;
import br.jus.tse.administrativa.exceptions.CustomException;

@WebMvcTest(ContatoPessoalRestController.class)
public class ContatoPessoalRequestTest {
    @Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContatoPessoalService contatoService;

	@Test 
	void buscarTodosTest() throws Exception {

		List<ContatoPessoal> contatos = new ArrayList<>();

		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
		long id = 3;
        ContatoPessoal contato = new ContatoPessoal(id,nome,cpf,nascimento);
        
		contatos.add(contato);


		when(contatoService.buscarTodos()).thenReturn(contatos);

		this.mockMvc.perform(get("/contatos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1));
	}

	@Test
	void buscarPorIdTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
		long id = 3;
        ContatoPessoal contato = new ContatoPessoal(id,nome,cpf,nascimento);

		when(contatoService.buscarPorId(id)).thenReturn(Optional.of(contato));
		this.mockMvc.perform(get("/contatos/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.nomeCompleto").value("Guilherme Augusto Theo Viana"))
				.andExpect(jsonPath("$.cpf").value("233.449.674-01"))
				.andExpect(jsonPath("$.nascimento").value("2018-07-22"));
	}

	@Test
	void criarTest() throws Exception {
		Long id = (long) 1;
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
		ContatoPessoal aux = new ContatoPessoal(nome,cpf,nascimento);
        ContatoPessoal contato = new ContatoPessoal(id,nome,cpf,nascimento);
		String contatoJson = "{ \"nome_completo\": \"Guilherme Augusto Theo Viana\",  \"cpf\": \"233.449.674-01\",\"data_nascimento\": \"22/07/2018\"}";

		when(contatoService.salvar(aux)).thenReturn(contato);

		this.mockMvc.perform(post("/contatos").contentType(MediaType.APPLICATION_JSON).content(contatoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nomeCompleto").value("Guilherme Augusto Theo Viana"))
				.andExpect(jsonPath("$.cpf").value("233.449.674-01"))
				.andExpect(jsonPath("$.nascimento").value("2018-07-22"));
	}

	@Test
	void atualizarTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
		long id = 3;
        ContatoPessoal contato = new ContatoPessoal(id,nome,cpf,nascimento);

		String contatoJson = "{ \"id\": \"3\", \"cpf\": \"123.329.192-01\", \"nome_completo\": \"Donna N. Hamrick\",\"data_nascimento\": \"22/07/2003\"}";
		when(contatoService.buscarPorId(id)).thenReturn(Optional.of(contato));

		this.mockMvc.perform(put("/contatos").contentType(MediaType.APPLICATION_JSON).content(contatoJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.nomeCompleto").value("Donna N. Hamrick"))
				.andExpect(jsonPath("$.cpf").value("123.329.192-01"))
				.andExpect(jsonPath("$.nascimento").value("2003-07-22"));
	}

	@Test
	void deletarTest() throws Exception {
		String nome = "Guilherme Augusto Theo Viana";
        String cpf = "233.449.674-01";
        LocalDate nascimento = LocalDate.of(2018, 07, 22);
		long id = 3;
        ContatoPessoal contato = new ContatoPessoal(id,nome,cpf,nascimento);

		when(contatoService.buscarPorId(id)).thenReturn(Optional.of(contato));

		this.mockMvc.perform(delete("/contatos/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.nomeCompleto").value("Guilherme Augusto Theo Viana"))
				.andExpect(jsonPath("$.cpf").value("233.449.674-01"))
				.andExpect(jsonPath("$.nascimento").value("2018-07-22"));
	}

	@Test
	void errorTest() throws Exception {
        long id = 3;
		when(contatoService.buscarPorId(id)).thenThrow(new CustomException("testando"));

		this.mockMvc.perform(delete("/contatos/3"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("Erro interno: /contatos/3"));
	}


}



