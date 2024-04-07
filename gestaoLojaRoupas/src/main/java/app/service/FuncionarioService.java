package app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcionario;
import app.respository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// SALVAR
	public String save(Funcionario funcionario) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		funcionario.setMatricula(myRandom.substring(0, 8));

		this.funcionarioRepository.save(funcionario);
		return "Funcionário " + funcionario.getNome() + " cadastrado com sucesso!";
	}

	// ALTERAR
	public String update(long id, Funcionario funcionario) {
		funcionario.setId(id);
		if (id < 0) {
			throw new RuntimeException("Id inválido.");
		} else {
			this.funcionarioRepository.save(funcionario);
			return "Cadastro de funcionário alterado com sucesso!";
		}
	}

	// DELETAR
	public String delete(long id) {
		if (id < 0) {
			throw new RuntimeException("Id inválido.");
		} else {
			this.funcionarioRepository.deleteById(id);
			return "Cadastro do funcionário deletado com sucesso!";
		}
	}

	// MOSTRAR TODOS OS FUNCIONÁRIOS CADASTRADOS
	public List<Funcionario> listAll() {
		return this.funcionarioRepository.findAll();
	}

	// MOSTRAR FUNCIONÁRIO CADASTRADO DE ACORDO COM O ID SELECIONADO
	public Funcionario findById(long id) {
		Funcionario funcionario = this.funcionarioRepository.findById(id).get();
		return funcionario;
	}

	// BUSCAR FUNCIONÁRIOS COM O NOME SELECIONADO
	public List<Funcionario> findByNome(String nome) {
		return this.funcionarioRepository.findByNome(nome);
	}

	// BUSCAR FUNCIONÁRIO COM A MATRICULA SELECIONADA
	public List<Funcionario> findByMatricula(String matricula) {
		return this.funcionarioRepository.findByMatricula(matricula);
	}

	// BUSCAR FUNCIONARIOS QUE TEM A IDADE ESPECIFICA
	public List<Funcionario> findByIdade(int idade) {
		if (idade < 0) {
			throw new RuntimeException("Idade precisa ser maior que 0");
		} else {
			return this.funcionarioRepository.findByIdade(idade);
		}
	}

	// MOSTAR LISTA DE FUNCIONÁRIOS QUE VENDERAM VALOR ESPECIFICO
	public List<Funcionario> findByVendaValorTotal(double valorTotal) {
		if (valorTotal < 0) {
			throw new RuntimeException("Valor não pode ser negativo");
		} else {
			return this.funcionarioRepository.findByVendasValorTotal(valorTotal);
		}
	}

	// IDADE MAIOR QUE
	public List<Funcionario> findByIdadeAcima(int idade) {
		return this.funcionarioRepository.findByIdadeAcima(idade);
	}

	// IDADE MENOR QUE
	public List<Funcionario> findByIdadeAbaixo(int idade) {
		return this.funcionarioRepository.findByIdadeAbaixo(idade);
	}

	// MOSTRAR FUNCIONARIOS COM VENDAS ACIMA DE
	public List<Funcionario> findByVendasAcima(double valorTotal) {
		return this.funcionarioRepository.findByVendasAcima(valorTotal);
	}

	// MOSTRAR FUNCIONARIOS COM VENDAS ABAIXO DE
	public List<Funcionario> findByVendasAbaixo(double valorTotal) {
		return this.funcionarioRepository.findByVendasAbaixo(valorTotal);
	}

	// NOME QUE CONTENHA
	public List<Funcionario> findByTrechoNome(String nome) {
		return this.funcionarioRepository.findByTrechoNome(nome);
	}

	// MATRICULA QUE CONTENHA
	public List<Funcionario> findByTrechoMatricula(String matricula) {
		return this.funcionarioRepository.findByTrechoMatricula(matricula);
	}

}
