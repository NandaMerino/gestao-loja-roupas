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
import app.respository.ClienteRepository;
import app.respository.VendaRepository;

@SpringBootTest
public class ClienteControllerTest {
	@Autowired
	ClienteController clienteController;
	
	@MockBean
	ClienteRepository clienteRepository;
	
	@BeforeEach
	void setup() {
		List<Cliente>listaCliente = new ArrayList<>();
		listaCliente.add(new Cliente(7,"nome cliente","123.123.123-12", 35,"(45) 99999-0599",null));
		listaCliente.add(new Cliente(9,"nome cliente","123.123.123-12", 35,"(45) 99999-0599",null));
		listaCliente.add(null);

		
		long id = 0;
		
		Cliente cliente = new Cliente();
		
		when(this.clienteRepository.findAll()).thenReturn(listaCliente);
		when(this.clienteRepository.save(cliente)).thenReturn(cliente);
		when(this.clienteRepository.findAllById(null)).thenReturn(listaCliente);
	}
	
	@Test
	@DisplayName("Teste de integração mocando o respository para o método save")
	void testSave() {
		Cliente cliente = new Cliente();
		ResponseEntity<String>response = clienteController.save(cliente);
		assertTrue(response.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	@DisplayName("Teste de integração mocando o respository para o método save com exception")
	void testSaveException() {
		Cliente cliente = new Cliente();
		ResponseEntity<String>response = clienteController.save(null);
		assertTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	@DisplayName("Teste de integração mocando o respository para o método update")
	void testUpdate() {
		List<Venda>listaVenda = new ArrayList<>();
		listaVenda.add(new Venda(25,"endereço teste update venda",333,"Ok", null, null, null));
		listaVenda.add(new Venda(25,"endereço teste update venda",333,"Ok", null, null, null));
		listaVenda.add(null);
		
		Cliente cliente = new Cliente(9,"nome cliente","123.123.123-12", 35,"(45) 99999-0599",null);
		long id = 0;
		ResponseEntity<String>response = clienteController.update(cliente, id);
		assertEquals(HttpStatus.OK, response.getStatusCode());		
	}
	
	@Test
	@DisplayName("Teste de integração mocando o respository para o método update com exception")
	void testUpdateException() {
		Cliente cliente = new Cliente();
		long id = -1;
		
		ResponseEntity<String>response = clienteController.update(cliente,id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
		
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete")
	void testDelete() {
		long id = 0;
		ResponseEntity<String>response = clienteController.delete(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método delete com exception")
	void testDeleteException() {
		long id = -1;
		ResponseEntity<String>response = clienteController.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findAll")
	void testFindAll() {
		ResponseEntity<List<Cliente>>response = this.clienteController.listAll();
		List<Cliente>lista = response.getBody();
		
		assertEquals(3, lista.size());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findById com exception")
	void testFindByIdException() {
		long id = 0;
		ResponseEntity<Cliente>response = clienteController.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByVendaValorTotal")
	void testFindByVendaValorTotal() {
		double valorTotal = 0;
		ResponseEntity<List<Cliente>>response = clienteController.findByVendasValorTotal(valorTotal);
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByVendaValorTotal com exception")
	void testFindByVendaValorTotalException() {
		double valorTotal = -1;
		ResponseEntity<List<Cliente>>response = clienteController.findByVendasValorTotal(valorTotal);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	//findByIdade
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByIdade")
	void testFindByIdade() {
		int idade = 0;
		ResponseEntity<List<Cliente>>response = clienteController.findByIdade(idade);
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByIdade com exception")
	void testFindByIdadeException() {
		int idade = -1;
		ResponseEntity<List<Cliente>>response = clienteController.findByIdade(idade);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	//findByVenda
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByVenda")
	void testFindByVenda() {
		long id = 0;
		ResponseEntity<List<Cliente>>response = clienteController.findByVendas(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	@DisplayName("Teste de integração mocando o repository para o método findByVenda com exception")
	void testFindByVendaException() {
		long id = -1;
		ResponseEntity<List<Cliente>>response = clienteController.findByVendas(id);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
}
