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
import app.respository.VendaRepository;

@SpringBootTest
public class VendaControllerTest {
	@Autowired
	VendaController vendaController;

	@MockBean
	VendaRepository vendaRepository;

	@BeforeEach
	void setup() {
		List<Venda> lista = new ArrayList<>();
		lista.add(new Venda(3, "endereco teste listAll venda", 500, "Ok", null, null, null));
		lista.add(new Venda(13, "endereco teste listAll venda", 150, "Ok", null, null, null));
		lista.add(null);

		// POSSO COLOCAR TODOS OS LISTS AQUI?

		List<Produto> listaProduto = new ArrayList<>();
		listaProduto.add(new Produto(7, "produto teste exception", 25));
		listaProduto.add(new Produto(9, "produto teste exception", 7));
		listaProduto.add(null);

		List<Cliente> listaCliente = new ArrayList<>();
		listaCliente.add(new Cliente(7, "nome cliente", "123.123.123-12", 35, "(45) 99999-0599", null));
		listaCliente.add(new Cliente(9, "nome cliente", "123.123.123-12", 35, "(45) 99999-0599", null));
		listaCliente.add(null);

		long id = 0;
		int idade = 0;
		String nome = null;

		Venda venda = new Venda(25, "endereço teste update venda", 333, "Ok", null, null, listaProduto);

		when(this.vendaRepository.findAll()).thenReturn(lista);
		when(this.vendaRepository.save(venda)).thenReturn(venda);
		when(this.vendaRepository.findById(id)).thenReturn(null);
		when(this.vendaRepository.findAllById(null)).thenReturn(lista);
		when(this.vendaRepository.findByClienteCpf(null)).thenReturn(lista);
		when(this.vendaRepository.findByClienteIdade(idade)).thenReturn(lista);
		when(this.vendaRepository.findByFuncionarioNome(nome)).thenReturn(lista);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método save")
	void testSave() {
		Venda venda = new Venda();
		ResponseEntity<String> response = vendaController.save(venda);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método save com exception")
	void testSaveException() {
		Venda venda = new Venda();
		ResponseEntity<String> response = vendaController.save(null);
		assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método update")
	void testUpdate() {
		List<Funcionario> lista = new ArrayList<>();
		lista.add(new Funcionario(7, "produto teste exception", 0, "matricula", null));
		lista.add(new Funcionario(9, "produto teste exception", 0, "matricula", null));
		lista.add(null);

		Venda venda = new Venda(25, "endereço teste update venda", 333, "Ok", null, null, null);
		long id = 0;
		ResponseEntity<String> response = vendaController.update(venda, 25);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método update com exception")
	void testUpdateException() {
		List<Produto> listaProduto = new ArrayList<>();
		listaProduto.add(new Produto(7, "produto teste exception", 25));
		listaProduto.add(new Produto(9, "produto teste exception", 7));
		listaProduto.add(null);

		Venda venda = new Venda(25, "endereço teste update venda", 333, "Ok", null, null, listaProduto);

		ResponseEntity<String> response = vendaController.update(venda, 0);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		ResponseEntity<String> response = vendaController.delete(0);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;

		ResponseEntity<String> response = vendaController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Venda>> response = this.vendaController.listAll();
		List<Venda> lista = response.getBody();

		assertEquals(3, lista.size());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = -1;
		ResponseEntity<Venda> response = vendaController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByClienteCpf")
	void testFindByClienteCpf() {
		ResponseEntity<List<Venda>> response = vendaController.findByClienteCpf(null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByClienteIdade")
	void testFindByClienteIdade() {
		int idade = 0;
		ResponseEntity<List<Venda>> response = vendaController.findByClienteIdade(idade);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByFuncionarioNome")
	void testFindByFuncionarioNome() {
		ResponseEntity<List<Venda>> response = vendaController.findByFuncionarioNome(null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByProdutosNome")
	void testFindByProdutosNome() {
		ResponseEntity<List<Venda>> response = vendaController.findByProdutosNome(null);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByFuncionarioIdade")
	void testFindByFuncionarioIdade() {
		ResponseEntity<List<Venda>> response = vendaController.findByFuncionarioIdade(0);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByFuncionario")
	void testFindByFuncionario() {
		long id = 0;
		ResponseEntity<List<Venda>> response = vendaController.findByFuncionario(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByFuncionario com exception")
	void testFindByFuncionarioException() {
		long id = -1;
		ResponseEntity<List<Venda>> response = vendaController.findByFuncionario(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

}
