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

import app.entity.Produto;
import app.service.ProdutoService;
import app.entity.Venda;

@RestController

@RequestMapping("/api/produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	// SALVAR
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Produto produto) {
		try {
			String mensagem = this.produtoService.save(produto);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar o cadastro do produto",
					HttpStatus.BAD_REQUEST);
		}
	}

	// ALTERAR
	@PutMapping("/update/{id}") ResponseEntity<String> update(@RequestBody Produto produto, @PathVariable long id) {
		try {
			String mensagem = this.produtoService.update(id, produto);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar alterar cadastro do produto: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// DELETAR
	@DeleteMapping("/delete/{id}") ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.produtoService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar deletar cadastro do produto" + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// MOSTRAR TODOS OS PRODUTOS CADASTRADOS
	@GetMapping("/listAll") ResponseEntity<List<Produto>> listAll() {
		try {
			List<Produto> lista = this.produtoService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// MOSTRAR PRODUTO CADASTRADO DE ACORDO COM O ID SELECIONADO
	@GetMapping("/findById/{id}")
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		try {
			Produto produto = this.produtoService.findById(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// BUSCAR PRODUTOS PELO NOME
	@GetMapping("/findByNome")
	public ResponseEntity<List<Produto>> findByNome(@RequestParam String nome) {
		try {
			List<Produto> lista = this.produtoService.findByNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// BUSCAR PRODUTOS POR VALOR EXATO
	@GetMapping("/findByValor")
	public ResponseEntity<List<Produto>> findByValor(@RequestParam double valor) {
		try {
			List<Produto> lista = this.produtoService.findByValor(valor);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VALOR ACIMA DE
	@GetMapping("/findByValorAcima")
	public ResponseEntity<List<Produto>> findByValorAcima(@RequestParam double valor) {
		try {
			List<Produto> lista = this.produtoService.findByValorAcima(valor);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VALOR ABAIXO DE
	@GetMapping("/findByValorAbaixo")
	public ResponseEntity<List<Produto>> findByValorAbaixo(@RequestParam double valor) {
		try {
			List<Produto> lista = this.produtoService.findByValorAbaixo(valor);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// TRECHO NOME
	@GetMapping("/findByTrechoNome")
	public ResponseEntity<List<Produto>> findByTrechoNome(@RequestParam String nome) {
		try {
			List<Produto> lista = this.produtoService.findByTrechoNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
