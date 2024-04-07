package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Produto;
import app.entity.Venda;

/*
 * 
 * 02 Testes de Unidade para o método calcularValorTotal do Service de Vendas. Fazer 1 cenário em que 
 * o cálculo é retornado e um cenário em que causa uma Exception.
 * 
 * 
 * 
 * Testes de Unidade para o método verificarStatus do Service de Vendas. Fazer 1 cenário em que o status da venda é 
 * CANCELADO, que a lista de produtos esteja nula e que o valor total é igual a zero. Fazer outro cenário para causar 
 * uma Exception de Validation com o atributo status.
 * 
 * 
 * 
 * Testes de Integração para cada um dos métodos que existem na Controller de Venda. 
 * Lembrando que para estes testes vocês deverão utilizar o Mockito.
 *
 */

@SpringBootTest
public class VendaServiceTest {
	@Autowired
	VendaService vendaService;
	
	@Test
	@DisplayName("Teste unitário retornando valor total 500")
	void test1ValorTotal() {
		List<Produto>lista = new ArrayList<>();
		lista.add(new Produto(3,"produto teste", 200));
		lista.add(new Produto(5,"produto teste",300));
		
		double retorno = this.vendaService.calcularValorTotal(lista);
		
		assertEquals(500, retorno);
	}
	
	@Test
	@DisplayName("Teste unitário retornando uma exception")
	void test2ValorTotal() {
		List<Produto>lista = new ArrayList<>();
		lista.add(new Produto(7,"produto teste exception", 25));
		lista.add(new Produto(9,"produto teste exception", 7));
		lista.add(null);
		
		assertThrows(Exception.class, () -> {
			vendaService.calcularValorTotal(lista);
		});
	}
	
	@Test
	@DisplayName("Teste unitário retornando status CANCELADO com lista de produtos nula e valor total zero")
	void test1Status() {
		Venda venda = new Venda(3,"endereco teste venda",250,"Cancelado", null,null,null);
		Venda retorno = this.vendaService.verificarStatus(venda);
		assertEquals(0, retorno.getValorTotal());
	}
	
	@Test
	@DisplayName("Teste unitário retornando uma exception na validation do status")
	void test2Status() {
		Venda venda = new Venda(7,"endereco teste venda exception", 777,"", null, null, null);
		assertNotNull(vendaService.verificarStatus(venda));
	}
	

	

}
