package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Cliente;
import app.service.ClienteService;

@RestController

@RequestMapping("/api/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	//SALVAR
	@PostMapping("/save")
	public ResponseEntity<String>save(@RequestBody Cliente cliente){
		try {
			String mensagem = this.clienteService.save(cliente);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro de cliente", HttpStatus.BAD_REQUEST);
		}
	}
	
	//ALTERAR
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@RequestBody Cliente cliente, @PathVariable long id){
		try {
			String mensagem = this.clienteService.update(id,cliente);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar alterar o cadastro de cliente: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//DELETAR
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable long id){
		try {
			String mensagem = this.clienteService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar deletar cadastro de cliente: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//MOSTRAR TODOS OS CLIENTES CADASTRADOS
	@GetMapping("/listAll")
	public ResponseEntity<List<Cliente>>listAll(){
		try {
			List<Cliente>lista = this.clienteService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);			
		}
	}
	
	//MOSTRAR CLIENTE CADASTRADO DE ACORDO COM O ID SELECIONADO
	@GetMapping("/findById/{id}")
	public ResponseEntity<Cliente>findById(@PathVariable long id){
		try {
			Cliente cliente = this.clienteService.findById(id);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//BUSCAR CLIENTES COM O NOME SELECIONADO
	@GetMapping("/findByNome")
	public ResponseEntity <List<Cliente>>findByNome(@RequestParam String nome){
		try {
			List<Cliente>lista = this.clienteService.findByNome(nome);
			return new ResponseEntity <>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//BUSCAR CLIENTE PELO CPF ESPECIFICO
		@GetMapping("/findByCpf")
		public ResponseEntity <List<Cliente>>findByCpf(@RequestParam String cpf){
			try {
				List<Cliente>lista = this.clienteService.findByCpf(cpf);
				return new ResponseEntity<>(lista, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
		
		//BUSCAR CLIENTES QUE TEM A IDADE ESPECIFICA
		@GetMapping("/findByIdade")
		public ResponseEntity<List<Cliente>>findByIdade(@RequestParam int idade){
			try {
				List<Cliente>lista = this.clienteService.findByIdade(idade);
				return new ResponseEntity<>(lista, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
		
		//BUSCAR CLIENTE PELO NÚMERO DE TELEFONE ESPECIFICO
		@GetMapping("/findByTelefone")
		public ResponseEntity<List<Cliente>>findByTelefone(@RequestParam String telefone){
			try {
				List<Cliente>lista = this.clienteService.findByTelefone(telefone);
				return new ResponseEntity<>(lista, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
	
	//BUSCAR CLIENTE QUE CONTEM O ID DA VENDA SELECIONADA
	@GetMapping("/findByVenda")
	public ResponseEntity<List<Cliente>>findByVendas(@RequestParam long id){
		try {
			List<Cliente>lista = this.clienteService.findByVenda(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//BUSCAR CLIENTE DE ACORDO COM O VALOR TOTAL DE VENDA
	@GetMapping("/findByValorTotalVenda")
	public ResponseEntity<List<Cliente>>findByVendasValorTotal(@RequestParam double valorTotal){
		try {
			List<Cliente>lista = this.clienteService.findByVendaValorTotal(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	//IDADE ACIMA DE
	@GetMapping("/findByIdadeAcima")
	public ResponseEntity<List<Cliente>>findByIdadeAcima(@RequestParam int idade){
		try {
			List<Cliente>lista = this.clienteService.findByIdadeAcima(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//IDADE ABAIXO DE
	@GetMapping("/findByIdadeAbaixo")
	public ResponseEntity<List<Cliente>>findByIdadeAbaixo(@RequestParam int idade){
		try {
			List<Cliente>lista = this.clienteService.findByIdadeAbaixo(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//TELEFONE QUE CONTENHA TRECHO
	@GetMapping("/findByTrechoTelefone")
	public ResponseEntity<List<Cliente>>findByTrechoTelefone(@RequestParam String telefone){
		try {
			List<Cliente>lista = this.clienteService.findByTrechoTelefone(telefone);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//VALOR TOTAL DE VENDA MAIOR QUE
	@GetMapping("/findByVendasValorAcima")
	public ResponseEntity<List<Cliente>>findByVendasValorAcima(@RequestParam double valorTotal){
		try {
			List<Cliente>lista = this.clienteService.findByVendasValorAcima(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//VALOR TOTAL DE VENDA MENOR QUE
	@GetMapping("/findByVendasValorAbaixo")
	public ResponseEntity<List<Cliente>>findByVendasValorAbaixo(@RequestParam double valorTotal){
		try {
			List<Cliente>lista = this.clienteService.findByVendasValorAbaixo(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//NOME DE CLIENTE QUE CONTENHA
	@GetMapping("/findByTrechoNome")
		public ResponseEntity<List<Cliente>>findByTrechoNome(@RequestParam String nome){
			try {
				List<Cliente>lista = this.clienteService.findByTrechoNome(nome);
				return new ResponseEntity<>(lista, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
}
