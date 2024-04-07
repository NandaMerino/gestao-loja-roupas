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

import app.entity.Funcionario;
import app.service.FuncionarioService;

@RestController

@RequestMapping("/api/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;
	
	//SALVAR
	@PostMapping("/save")
	public ResponseEntity<String>save(@RequestBody Funcionario funcionario){
		try {
			String mensagem = this.funcionarioService.save(funcionario);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro de funcionário", HttpStatus.BAD_REQUEST);
		}
	}
	
	//ALTERAR
	@PutMapping("/update/{id}")
	public ResponseEntity<String>update(@RequestBody Funcionario funcionario, @PathVariable long id){
		try {
			String mensagem = this.funcionarioService.update(id,funcionario);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar alterar o cadastro de funcionário: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//DELETAR
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>delete(@PathVariable long id){
		try {
			String mensagem = this.funcionarioService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar deletar cadastro de cliente: "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//MOSTRAR TODOS OS FUNCIONÁRIOS CADASTRADOS
	@GetMapping("/listAll")
	public ResponseEntity<List<Funcionario>>listAll(){
		try {
			List<Funcionario>lista = this.funcionarioService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//MOSTRAR FUNCIONÁRIO CADASTRADO DE ACORDO COM O ID SELECIONADO
	@GetMapping("/findById/{id}")
	public ResponseEntity<Funcionario>findById(@PathVariable long id){
		try {
			Funcionario funcionario = this.funcionarioService.findById(id);
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//BUSCAR FUNCIONÁRIOS COM O NOME SELECIONADO
	@GetMapping("/findByNome")
	public ResponseEntity<List<Funcionario>>findByNome(@RequestParam String nome){
		try {
			List<Funcionario>lista = this.funcionarioService.findByNome(nome);
			return new ResponseEntity <>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//BUSCAR FUNCIONÁRIO COM A MATRICULA SELECIONADA
	@GetMapping("/findByMatricula")
	public ResponseEntity<List<Funcionario>>findByMatricula(@RequestParam String matricula){
		try {
			List<Funcionario>lista = this.funcionarioService.findByMatricula(matricula);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//BUSCAR FUNCIONARIOS QUE TEM A IDADE ESPECIFICA
	@GetMapping("/findByIdade")
	public ResponseEntity<List<Funcionario>>findByIdade(@RequestParam int idade){
		try {
			List<Funcionario>lista = this.funcionarioService.findByIdade(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//MOSTAR LISTA DE FUNCIONÁRIOS QUE VENDERAM VALOR ESPECIFICO
	@GetMapping("/findByValorTotalVenda")
	public ResponseEntity<List<Funcionario>>findByVendasValorTotal(@RequestParam double valorTotal){
		try {
			List<Funcionario>lista = this.funcionarioService.findByVendaValorTotal(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//IDADE MAIOR QUE
	@GetMapping("/findByIdadeAcima")
	public ResponseEntity<List<Funcionario>>findByIdadeAcima(@RequestParam int idade){
		try {
			List<Funcionario>lista = this.funcionarioService.findByIdadeAcima(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//IDADE MENOR QUE
	@GetMapping("/findByIdadeAbaixo")
	public ResponseEntity<List<Funcionario>>findByIdadeAbaixo(@RequestParam int idade){
		try {
			List<Funcionario>lista = this.funcionarioService.findByIdadeAbaixo(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//MOSTRAR FUNCIONARIOS COM VENDAS ACIMA DE
	@GetMapping("/findByVendasAcima")
	public ResponseEntity<List<Funcionario>>findByVendasAcima(@RequestParam double valorTotal){
		try {
			List<Funcionario>lista = this.funcionarioService.findByVendasAcima(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//MOSTRAR FUNCIONARIOS COM VENDAS ABAIXO DE
	@GetMapping("/findByVendasAbaixo")
	public ResponseEntity<List<Funcionario>>findByVendasAbaixo(@RequestParam double valorTotal){
		try {
			List<Funcionario>lista = this.funcionarioService.findByVendasAbaixo(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//NOME QUE CONTENHA
	@GetMapping("/findByTrechoNome")
	public ResponseEntity<List<Funcionario>>findByTrechoNome(@RequestParam String nome){
		try {
			List<Funcionario>lista = this.funcionarioService.findByTrechoNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//MATRICULA QUE CONTENHA
	@GetMapping("/findByTrechoMatricula")
	public ResponseEntity<List<Funcionario>>findByTrechoMatricula(@RequestParam String matricula){
		try {
			List<Funcionario>lista = this.funcionarioService.findByTrechoMatricula(matricula);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
