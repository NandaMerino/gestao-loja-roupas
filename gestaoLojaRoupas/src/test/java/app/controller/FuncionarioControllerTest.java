package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Cliente;
import app.entity.Funcionario;
import app.entity.Produto;
import app.entity.Venda;
import app.respository.FuncionarioRepository;
import app.respository.VendaRepository;

@SpringBootTest
public class FuncionarioControllerTest {
	@Autowired
	FuncionarioController funcionarioController;

	@MockBean
	FuncionarioRepository funcionarioRepository;

	@BeforeEach
	void setup() {
		List<Funcionario> listaFuncionario = new ArrayList<>();
		listaFuncionario.add(new Funcionario(3, "nome funcionario", 35, "matricula", null));
		listaFuncionario.add(new Funcionario(13, "nome funcionario", 35, "matricula", null));
		listaFuncionario.add(null);

		long id = 0;

		Funcionario funcionario = new Funcionario(0, "nome funcionario", 35, "matricula", null);

		when(this.funcionarioRepository.findAll()).thenReturn(listaFuncionario);
		when(this.funcionarioRepository.save(funcionario)).thenReturn(funcionario);
		when(this.funcionarioRepository.findAllById(null)).thenReturn(listaFuncionario);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método save")
	void testSave() {
		Funcionario funcionario = new Funcionario();
		ResponseEntity<String> response = funcionarioController.save(funcionario);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método save com exception")
	void testSaveException() {
		Funcionario funcionario = new Funcionario();
		ResponseEntity<String> response = funcionarioController.save(null);
		assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método update")
	void testUpdate() {
		Funcionario funcionario = new Funcionario();
		long id = 0;

		ResponseEntity<String> response = funcionarioController.update(funcionario, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método update com exception")
	void testUpdateException() {
		Funcionario funcionario = new Funcionario();
		long id = -1;

		ResponseEntity<String> response = funcionarioController.update(funcionario, id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String> response = funcionarioController.delete(0);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;

		ResponseEntity<String> response = funcionarioController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Funcionario>> response = this.funcionarioController.listAll();
		List<Funcionario> lista = response.getBody();

		assertEquals(3, lista.size());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Funcionario> response = funcionarioController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByIdade")
	void testfindByIdade() {
		int idade = 0;
		ResponseEntity<List<Funcionario>> response = funcionarioController.findByIdade(idade);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByIdade com exception")
	void testfindByIdadeException() {
		int idade = -1;
		ResponseEntity<List<Funcionario>> response = funcionarioController.findByIdade(idade);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByVendasValorTotal")
	void testfindByVendasValorTotal() {
		double valorTotal = 0;
		ResponseEntity<List<Funcionario>> response = funcionarioController.findByVendasValorTotal(valorTotal);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByVendasValorTotal com exception")
	void testfindByVendasValorTotalException() {
		double valorTotal = -1;
		ResponseEntity<List<Funcionario>> response = funcionarioController.findByVendasValorTotal(valorTotal);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

}
