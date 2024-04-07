package app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Cliente;
import app.entity.Funcionario;
import app.entity.Produto;
import app.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	// ----- MÉTODOS AUTOMÁTICOS -----
	// BUSCAR VENDAS POR VALOR TOTAL EXATO
	public List<Venda> findByValorTotal(double valor_total);

	// BUSCAR ENDERECO DE ENTREGA EXATO
	public List<Venda> findByEnderecoEntrega(String enderecoEntrega);

	// VENDA DE ACORDO COM NOME DO CLIENTE
	public List<Venda> findByClienteNome(String nome);

	// VENDA DE ACORDO COM CPF DO CLIENTE
	public List<Venda> findByClienteCpf(String cpf);

	// VENDA DE ACORDO COM IDADE DO CLIENTE
	public List<Venda> findByClienteIdade(int idade);

	// VENDA DE ACORDO COM TELEFONE DO CLIENTE
	public List<Venda> findByClienteTelefone(String telefone);

	// VENDA DE ACORDO COM ID DO CLIENTE
	public List<Venda> findByCliente(Cliente cliente);

	// VENDA QUE CONTEM PRODUTO COM NOME ESPECIFICO
	public List<Venda> findByProdutosNome(String nome);

	// VENDA QUE CONTEM PRODUTO COM VALOR ESPECIFICO
	public List<Venda> findByProdutosValor(double valor);

	// VENDA DE ACORDO COM ID ESPECIFICO DO PRODUTO
	public List<Venda> findByProdutos(Produto produto);

	// VENDA DE ACORDO COM NOME DO FUNCIONÁRIO
	public List<Venda> findByFuncionarioNome(String nome);

	// VENDA DE ACORDO COIM IDADE DO FUNCIONÁRIO
	public List<Venda> findByFuncionarioIdade(int idade);

	// VENDA DE ACORDO COM MATRICULA DO FUNCIONÁRIO
	public List<Venda> findByFuncionarioMatricula(String matricula);

	// VENDA DE ACORDO COM ID ESPECIFICO DO FUNCIONÁRIO
	public List<Venda> findByFuncionario(Funcionario funcionario);

	// -----JPQL-----
	// VENDAS COM VALORES ACIMA DE
	@Query("SELECT v FROM Venda v WHERE v.valorTotal > :valorTotal ORDER BY valorTotal")
	public List<Venda> findByValorTotalAcima(double valorTotal);

	// VENDAS COM VALORES ABAIXO DE
	@Query("SELECT v FROM Venda v WHERE v.valorTotal < :valorTotal ORDER BY valorTotal")
	public List<Venda> findByValorTotalAbaixo(double valorTotal);

	// VENDAS COM TRECHO DE ENDERECO DE ENTREGA
	@Query("SELECT v FROM Venda v WHERE v.enderecoEntrega LIKE CONCAT ('%', :enderecoEntrega, '%')")
	public List<Venda> findByTrechoEnderecoEntrega(String enderecoEntrega);

}
