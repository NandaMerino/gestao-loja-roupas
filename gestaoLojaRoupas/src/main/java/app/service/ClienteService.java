package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.entity.Venda;
import app.respository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	// SALVAR
	public String save(Cliente cliente) {
		this.clienteRepository.save(cliente);
		return "Cliente " + cliente.getNome() + " cadastrado com sucesso!";
	}

	// ALTERAR
	public String update(long id, Cliente cliente) {
		cliente.setId(id);
		if (id < 0) {
			throw new RuntimeException("Id inválido.");
		} else {
			this.clienteRepository.save(cliente);
			return "Cadastro do cliente alterado com sucesso!";
		}
	}

	// DELETAR
	public String delete(long id) {
		if (id < 0) {
			throw new RuntimeException("Id inválido.");
		} else {
			this.clienteRepository.deleteById(id);
			return "Cadastro do cliente deletado com sucesso!";
		}
	}

	// MOSTRAR TODOS OS CLIENTES CADASTRADOS
	public List<Cliente> listAll() {
		return this.clienteRepository.findAll();
	}

	// MOSTRAR CLIENTE CADASTRADO DE ACORDO COM O ID SELECIONADO
	public Cliente findById(long id) {
		Cliente cliente = this.clienteRepository.findById(id).get();
		return cliente;
	}

	// BUSCAR CLIENTES COM O NOME SELECIONADO
	public List<Cliente> findByNome(String nome) {
		return this.clienteRepository.findByNome(nome);
	}

	// BUSCAR CLIENTE PELO CPF ESPECIFICO
	public List<Cliente> findByCpf(String cpf) {
		return this.clienteRepository.findByCpf(cpf);
	}

	// BUSCAR CLIENTES QUE TEM A IDADE ESPECIFICA
	public List<Cliente> findByIdade(int idade) {
		if(idade < 0) {
			throw new RuntimeException("Idade precisa ser maior que 0");
		}else {
			return this.clienteRepository.findByidade(idade);
		}
		
	}

	// BUSCAR CLIENTE PELO NÚMERO DE TELEFONE ESPECIFICO
	public List<Cliente> findByTelefone(String telefone) {
		return this.clienteRepository.findByTelefone(telefone);
	}

	// BUSCAR CLIENTE QUE CONTEM O ID DA VENDA SELECIONADA
	public List<Cliente> findByVenda(long id) {
		Venda venda = new Venda();
		venda.setId(id);
		if(id < 0) {
			throw new RuntimeException("Id inválido");
		}else {
			return this.clienteRepository.findByVendas(venda);
		}
		
	}

	// BUSCAR CLIENTE DE ACORDO COM O VALOR TOTAL DE VENDA
	public List<Cliente> findByVendaValorTotal(double valorTotal) {
		if (valorTotal < 0) {
			throw new RuntimeException("Valor inválido.");
		} else {
			return this.clienteRepository.findByVendasValorTotal(valorTotal);
		}
	}

	// IDADE ACIMA DE
	public List<Cliente> findByIdadeAcima(int idade) {
		return this.clienteRepository.findByIdadeAcima(idade);
	}

	// IDADE ABAIXO DE
	public List<Cliente> findByIdadeAbaixo(int idade) {
		return this.clienteRepository.findByIdadeAbaixo(idade);
	}

	// TELEFONE QUE CONTENHA TRECHO
	public List<Cliente> findByTrechoTelefone(String telefone) {
		return this.clienteRepository.findByTrechoTelefone(telefone);
	}

	// VALOR TOTAL DE VENDA MAIOR QUE (findByClienteVendaAcima)
	public List<Cliente> findByVendasValorAcima(double valorTotal) {
		return this.clienteRepository.findByVendasValorAcima(valorTotal);
	}

	// VALOR TOTAL DE VENDA MENOR QUE (findByVendasValorAbaixo)
	public List<Cliente> findByVendasValorAbaixo(double valorTotal) {
		return this.clienteRepository.findByVendasValorAbaixo(valorTotal);
	}

	// NOME DE CLIENTE QUE CONTENHA
	public List<Cliente> findByTrechoNome(String nome) {
		return this.clienteRepository.findByTrechoNome(nome);
	}
}
