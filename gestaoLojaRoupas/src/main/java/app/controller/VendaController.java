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

import app.entity.Venda;
import app.service.VendaService;
import app.entity.Produto;

@RestController
@RequestMapping("/api/venda")
public class VendaController {
	@Autowired
	private VendaService vendaService;

	// SALVAR
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Venda venda) {
		try {
			String mensagem = this.vendaService.save(venda);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Algo deu errado ao tentar salvar a venda", HttpStatus.BAD_REQUEST);
		}
	}

	// ALTERAR
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Venda venda, @PathVariable long id) {
		try {
			String mensagem = this.vendaService.update(id, venda);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar alterar a venda: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// DELETAR
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.vendaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar deletar venda: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// MOSTRAR TODAS AS VENDAS CADASTRADOS
	@GetMapping("/listAll")
	public ResponseEntity<List<Venda>> listAll() {
		try {
			List<Venda> lista = this.vendaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// MOSTRAR VENDA DE ACORDO COM O ID SELECIONADO
	@GetMapping("/findById/{id}")
	public ResponseEntity<Venda> findById(@PathVariable long id) {
		try {
			Venda venda = this.vendaService.findById(id);
			return new ResponseEntity<>(venda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// BUSCAR PRODUTOS POR VALOR EXATO
	@GetMapping("/findByValorTotal")
	public ResponseEntity<List<Venda>> findByVendaTotal(@RequestParam double valorTotal) {
		try {
			List<Venda> lista = this.vendaService.findByValorTotal(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// BUSCAR ENDERECO DE ENTREGA EXATO
	@GetMapping("/findByEnderecoEntrega")
	public ResponseEntity<List<Venda>> findByEnderecoEntrega(@RequestParam String enderecoEntrega) {
		try {
			List<Venda> lista = this.vendaService.findByEnderecoEntrega(enderecoEntrega);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM NOME DO CLIENTE
	@GetMapping("/findByClienteNome")
	public ResponseEntity<List<Venda>> findByClienteNome(@RequestParam String nome) {
		try {
			List<Venda> lista = this.vendaService.findByClienteNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM CPF DO CLIENTE
	@GetMapping("/findByClienteCpf")
	public ResponseEntity<List<Venda>> findByClienteCpf(@RequestParam String cpf) {
		try {
			List<Venda> lista = this.vendaService.findByClienteCpf(cpf);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM IDADE DO CLIENTE
	@GetMapping("/findByClienteIdade")
	public ResponseEntity<List<Venda>> findByClienteIdade(@RequestParam int idade) {
		try {
			List<Venda> lista = this.vendaService.findByClienteIdade(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// VENDA DE ACORDO COM TELEFONE DO CLIENTE
	@GetMapping("/findByClienteTelefone")
	public ResponseEntity<List<Venda>> findByClienteTelefone(@RequestParam String telefone) {
		try {
			List<Venda> lista = this.vendaService.findByClienteTelefone(telefone);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM ID DO CLIENTE
	@GetMapping("/findByCliente")
	public ResponseEntity<List<Venda>> findByCliente(@RequestParam long id) {
		try {
			List<Venda> lista = this.vendaService.findByCliente(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA QUE CONTEM O PRODUTO ESPECIFICO
	@GetMapping("/findByProdutosNome")
	public ResponseEntity<List<Venda>> findByProdutosNome(@RequestParam String nome) {
		try {
			List<Venda> lista = this.vendaService.findByProdutosNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA QUE CONTEM PRODUTO COM VALOR ESPECIFICO
	@GetMapping("/findByProdutosValor")
	public ResponseEntity<List<Venda>> findByProdutosValor(@RequestParam double valor) {
		try {
			List<Venda> lista = this.vendaService.findByProdutosValor(valor);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM ID ESPECIFICO DO PRODUTO
	@GetMapping("/findByProdutos")
	public ResponseEntity<List<Venda>> findByProdutos(@RequestParam long id) {
		try {
			List<Venda> lista = this.vendaService.findByProdutos(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM NOME DO FUNCIONÁRIO
	@GetMapping("/findByFuncionarioNome")
	public ResponseEntity<List<Venda>> findByFuncionarioNome(@RequestParam String nome) {
		try {
			List<Venda> lista = this.vendaService.findByFuncionarioNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COIM IDADE DO FUNCIONÁRIO
	@GetMapping("/findByFuncionarioIdade")
	public ResponseEntity<List<Venda>> findByFuncionarioIdade(@RequestParam int idade) {
		try {
			List<Venda> lista = this.vendaService.findByFuncionarioIdade(idade);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM MATRICULA DO FUNCIONÁRIO
	@GetMapping("/findByFuncionarioMatricula")
	public ResponseEntity<List<Venda>> findByFuncionarioMatricula(@RequestParam String matricula) {
		try {
			List<Venda> lista = this.vendaService.findByFuncionarioMatricula(matricula);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDA DE ACORDO COM ID ESPECIFICO DO FUNCIONÁRIO
	@GetMapping("/findByFuncionario")
	public ResponseEntity<List<Venda>> findByFuncionario(@RequestParam long id) {
		try {
			List<Venda> lista = this.vendaService.findByFuncionario(id);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDAS COM VALORES ACIMA DE
	@GetMapping("/findByValorTotalAcima")
	public ResponseEntity<List<Venda>> findByValorTotalAcima(@RequestParam double valorTotal) {
		try {
			List<Venda> lista = this.vendaService.findByValorTotalAcima(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDAS COM VALORES ABAIXO DE
	@GetMapping("/findByValorTotalAbaixo")
	public ResponseEntity<List<Venda>> findByValorTotalAbaixo(@RequestParam double valorTotal) {
		try {
			List<Venda> lista = this.vendaService.findByValorTotalAbaixo(valorTotal);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// VENDAS COM TRECHO DE ENDERECO DE ENTREGA
	@GetMapping("/findByTrechoEnderecoEntrega")
	public ResponseEntity<List<Venda>> findByTrechoEnderecoEntrega(@RequestParam String enderecoEntrega) {
		try {
			List<Venda> lista = this.vendaService.findByTrechoEnderecoEntrega(enderecoEntrega);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
