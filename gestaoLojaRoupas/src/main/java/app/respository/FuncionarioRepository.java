package app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	// ----- MÉTODOS AUTOMÁTICOS -----
	// BUSCAR FUNCIONÁRIOS COM O NOME SELECIONADO
	public List<Funcionario> findByNome(String nome);

	// BUSCAR FUNCIONÁRIO COM A MATRICULA SELECIONADA
	public List<Funcionario> findByMatricula(String matricula);

	// BUSCAR FUNCIONARIOS QUE TEM A IDADE ESPECIFICA
	public List<Funcionario> findByIdade(int idade);

	// MOSTAR LISTA DE FUNCIONÁRIOS QUE VENDERAM VALOR ESPECIFICO
	public List<Funcionario> findByVendasValorTotal(double vendaTotal);

	// -----JPQL-----
	// IDADE MAIOR QUE
	@Query("SELECT f FROM Funcionario f WHERE f.idade > :idade ORDER BY idade")
	public List<Funcionario> findByIdadeAcima(int idade);

	// IDADE MENOR QUE
	@Query("SELECT f FROM Funcionario f WHERE f.idade < :idade ORDER BY idade")
	public List<Funcionario> findByIdadeAbaixo(int idade);

	// MOSTRAR FUNCIONARIOS COM VENDAS ACIMA DE
	@Query("SELECT DISTINCT f FROM Funcionario f JOIN f.vendas v WHERE v.valorTotal > :valorTotal")
	public List<Funcionario> findByVendasAcima(double valorTotal);

	// MOSTRAR FUNCIONARIOS COM VENDAS ABAIXO DE
	@Query("SELECT DISTINCT f FROM Funcionario f JOIN f.vendas v WHERE v.valorTotal < :valorTotal")
	public List<Funcionario> findByVendasAbaixo(double valorTotal);

	// NOME QUE CONTENHA
	@Query("SELECT f FROM Funcionario f WHERE f.nome LIKE CONCAT ('%', :nome, '%')")
	public List<Funcionario> findByTrechoNome(String nome);

	// MATRICULA QUE CONTENHA
	@Query("SELECT f FROM Funcionario f WHERE f.matricula LIKE CONCAT ('%', :matricula, '%')")
	public List<Funcionario> findByTrechoMatricula(String matricula);

}
