package app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Cliente;
import app.entity.Venda;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// ----- MÉTODOS AUTOMÁTICOS -----
	// BUSCAR CLIENTES COM O NOME ESPECIFICO
	public List<Cliente> findByNome(String nome);

	// BUSCAR CLIENTE PELO CPF ESPECIFICO
	public List<Cliente> findByCpf(String cpf);

	// BUSCAR CLIENTES QUE TEM A IDADE ESPECIFICA
	public List<Cliente> findByidade(int idade);

	// BUSCAR CLIENTE PELO NÚMERO DE TELEFONE ESPECIFICO
	public List<Cliente> findByTelefone(String telefone);

	// BUSCAR CLIENTE QUE CONTEM O ID DA VENDA SELECIONADA
	public List<Cliente> findByVendas(Venda venda);

	// BUSCAR CLIENTES DE ACORDO COM O VALOR TOTAL DE VENDA
	public List<Cliente> findByVendasValorTotal(double valorTotal);

	// -----JPQL-----
	// IDADE ACIMA DE
	@Query("SELECT c FROM Cliente c WHERE c.idade > :idade ORDER BY idade")
	public List<Cliente> findByIdadeAcima(int idade);

	// IDADE ABAIXO DE
	@Query("SELECT c FROM Cliente c WHERE c.idade < :idade ORDER BY idade")
	public List<Cliente> findByIdadeAbaixo(int idade);

	// TELEFONE QUE CONTENHA TRECHO
	@Query("SELECT c FROM Cliente c WHERE c.telefone LIKE CONCAT ('%', :telefone, '%')")
	public List<Cliente> findByTrechoTelefone(String telefone);

	// VALOR TOTAL DE VENDA MAIOR QUE
	@Query("SELECT DISTINCT c FROM Cliente c JOIN c.vendas v WHERE v.valorTotal > :valorTotal")
	public List<Cliente> findByVendasValorAcima(double valorTotal);

	// VALOR TOTAL DE VENDA MENOR QUE
	@Query("SELECT DISTINCT c FROM Cliente c JOIN c.vendas v WHERE v.valorTotal < :valorTotal")
	public List<Cliente> findByVendasValorAbaixo(double valorTotal);

	// NOME DE CLIENTE QUE CONTENHA
	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE CONCAT ('%', :nome, '%')")
	public List<Cliente> findByTrechoNome(String nome);
}
