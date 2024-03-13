package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
	
	public List<Vendas> findByDataVenda(String dataVenda);
	
	public List<Vendas> findByValorTotal(double valorTotal);
	
	@Query("FROM Vendas v WHERE v.enderecoEntrega LIKE %:enderecoEntrega%")
	public List<Vendas> findByEnderecoEntrega(String enderecoEntrega);
	

}
