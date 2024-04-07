package app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	// ----- MÉTODOS AUTOMÁTICOS -----
	// BUSCAR PRODUTOS PELO NOME
	public List<Produto> findByNome(String nome);

	// BUSCAR PRODUTOS POR VALOR
	public List<Produto> findByValor(double valor);

	// -----JPQL-----
	// VALOR ACIMA DE
	@Query("SELECT p FROM Produto p WHERE p.valor > :valor ORDER BY valor")
	public List<Produto> findByValorAcima(double valor);

	// VALOR ABAIXO DE
	@Query("SELECT p FROM Produto p WHERE p.valor < :valor ORDER BY valor")
	public List<Produto> findByValorAbaixo(double valor);

	// TRECHO NOME
	@Query("SELECT p FROM Produto p WHERE p.nome LIKE CONCAT('%', :nome, '%')")
	public List<Produto> findByTrechoNome(String nome);

}
