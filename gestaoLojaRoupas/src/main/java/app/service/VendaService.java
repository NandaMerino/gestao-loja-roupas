package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.entity.Funcionario;
import app.entity.Produto;
import app.entity.Venda;
import app.respository.VendaRepository;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRepository;

	// SALVAR
	public String save(Venda venda) {
		double valorTotal = this.calcularValorTotal(venda.getProdutos());
		venda.setValorTotal(valorTotal);
		this.vendaRepository.save(venda);
		return "Venda cadastrada com sucesso!";
	}

	// ALTERAR
	public String update(long id, Venda venda) {
		venda.setId(id);
		double valorTotal = this.calcularValorTotal(venda.getProdutos());
		venda.setValorTotal(valorTotal);
		this.vendaRepository.save(venda);
		return "Venda alterada com sucesso!";
	}

	// DELETAR
	public String delete(long id) {
		if (id < 0) {
			throw new RuntimeException("Id inválido.");
		} else {
			this.vendaRepository.deleteById(id);
			return "Venda deletada com sucesso!";
		}
	}

	// MOSTRAR TODAS AS VENDAS CADASTRADOS
	public List<Venda> listAll() {
		return this.vendaRepository.findAll();
	}

	// MOSTRAR VENDA DE ACORDO COM O ID SELECIONADO
	public Venda findById(long id) {
		Venda venda = this.vendaRepository.findById(id).get();
		if (id < 0) {
			throw new RuntimeException("Id inválido.");
		} else {
			return venda;
		}
	}

	// BUSCAR PRODUTOS POR VALOR EXATO
	public List<Venda> findByValorTotal(double valorTotal) {
		return this.vendaRepository.findByValorTotal(valorTotal);
	}

	// BUSCAR ENDERECO DE ENTREGA EXATO
	public List<Venda> findByEnderecoEntrega(String enderecoEntrega) {
		return this.vendaRepository.findByEnderecoEntrega(enderecoEntrega);
	}

	// VENDA DE ACORDO COM NOME DO CLIENTE
	public List<Venda> findByClienteNome(String nome) {
		return this.vendaRepository.findByClienteNome(nome);
	}

	// VENDA DE ACORDO COM CPF DO CLIENTE
	public List<Venda> findByClienteCpf(String cpf) {
		return this.vendaRepository.findByClienteCpf(cpf);
	}

	// VENDA DE ACORDO COM IDADE DO CLIENTE
	public List<Venda> findByClienteIdade(int idade) {
		return this.vendaRepository.findByClienteIdade(idade);
	}

	// VENDA DE ACORDO COM TELEFONE DO CLIENTE
	public List<Venda> findByClienteTelefone(String telefone) {
		return this.vendaRepository.findByClienteTelefone(telefone);
	}

	// VENDA DE ACORDO COM ID DO CLIENTE
	public List<Venda> findByCliente(long id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		return this.vendaRepository.findByCliente(cliente);
	}

	// VENDA QUE CONTEM O PRODUTO ESPECIFICO
	public List<Venda> findByProdutosNome(String nome) {
		return this.vendaRepository.findByProdutosNome(nome);
	}

	// VENDA QUE CONTEM PRODUTO COM VALOR ESPECIFICO
	public List<Venda> findByProdutosValor(double valor) {
		return this.vendaRepository.findByProdutosValor(valor);
	}

	// VENDA DE ACORDO COM ID ESPECIFICO DO PRODUTO
	public List<Venda> findByProdutos(long id) {
		Produto produto = new Produto();
		produto.setId(id);
		return this.vendaRepository.findByProdutos(produto);
	}

	// VENDA DE ACORDO COM NOME DO FUNCIONÁRIO
	public List<Venda> findByFuncionarioNome(String nome) {
		return this.vendaRepository.findByFuncionarioNome(nome);
	}

	// VENDA DE ACORDO COIM IDADE DO FUNCIONÁRIO
	public List<Venda> findByFuncionarioIdade(int idade) {
		return this.vendaRepository.findByFuncionarioIdade(idade);
	}

	// VENDA DE ACORDO COM MATRICULA DO FUNCIONÁRIO
	public List<Venda> findByFuncionarioMatricula(String matricula) {
		return this.vendaRepository.findByFuncionarioMatricula(matricula);
	}

	// VENDA DE ACORDO COM ID ESPECIFICO DO FUNCIONÁRIO
	public List<Venda> findByFuncionario(long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		if(id < 0) {
			throw new RuntimeException("Id inválido.");
		}else {
			return this.vendaRepository.findByFuncionario(funcionario);
		}
	}

	// VENDAS COM VALORES ACIMA DE
	public List<Venda> findByValorTotalAcima(double valorTotal) {
		return this.vendaRepository.findByValorTotalAcima(valorTotal);
	}

	// VENDAS COM VALORES ABAIXO DE
	public List<Venda> findByValorTotalAbaixo(double valorTotal) {
		return this.vendaRepository.findByValorTotalAbaixo(valorTotal);
	}

	// VENDAS COM TRECHO DE ENDERECO DE ENTREGA
	public List<Venda> findByTrechoEnderecoEntrega(String enderecoVenda) {
		return this.vendaRepository.findByTrechoEnderecoEntrega(enderecoVenda);
	}

	// ************** ENTREGA DE TESTES **************
	public double calcularValorTotal(List<Produto> produtos) {
		double soma = 0;
		if (produtos != null) {
			for (int i = 0; i < produtos.size(); i++) {
				soma += produtos.get(i).getValor();
			}
		}
		return soma;
	}

	public Venda verificarStatus(Venda venda) {
		if (venda.getStatus().equals("Cancelado")) {
			venda.setValorTotal(0);
			venda.setProdutos(null);
		}
		return venda;
	}

}
