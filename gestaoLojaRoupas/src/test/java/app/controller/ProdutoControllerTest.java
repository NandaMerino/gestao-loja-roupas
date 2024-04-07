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
import app.respository.ProdutoRepository;
import app.respository.VendaRepository;

@SpringBootTest
public class ProdutoControllerTest {
	@Autowired
	ProdutoController produtoController;

	@MockBean
	ProdutoRepository produtoRepository;

	@BeforeEach
	void setup() {
		List<Produto> listaProduto = new ArrayList<>();
		listaProduto.add(new Produto(7, "produto teste exception", 25));
		listaProduto.add(new Produto(9, "produto teste exception", 7));
		listaProduto.add(null);

		long id = 0;
		Produto produto = new Produto();

		when(this.produtoRepository.findAll()).thenReturn(listaProduto);
		when(this.produtoRepository.save(produto)).thenReturn(produto);
		when(this.produtoRepository.findAllById(null)).thenReturn(listaProduto);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método save")
	void testSave() {
		Produto produto = new Produto();
		ResponseEntity<String> response = produtoController.save(produto);
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método save com exception")
	void testSaveException() {
		Produto produto = new Produto();
		ResponseEntity<String> response = produtoController.save(null);
		assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método update")
	void testUpdate() {
		Produto produto = new Produto(1, "nome produto", 55);
		long id = 0;

		ResponseEntity<String> response = produtoController.update(produto, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o respository para o método update com exception")
	void testUpdateException() {
		Produto produto = new Produto();
		long id = -1;

		ResponseEntity<String> response = produtoController.update(produto, id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String> response = produtoController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;

		ResponseEntity<String> response = produtoController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Produto>> response = this.produtoController.listAll();
		List<Produto> lista = response.getBody();

		assertEquals(3, lista.size());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Produto> response = produtoController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByValor")
	void testFindByValor() {
		double valor = 0;
		ResponseEntity<List<Produto>> response = produtoController.findByValor(valor);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByValor com exception")
	void testFindByValorException() {
		double valor = -1;
		ResponseEntity<List<Produto>> response = produtoController.findByValor(valor);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

}
