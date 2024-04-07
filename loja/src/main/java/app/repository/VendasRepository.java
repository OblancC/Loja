package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entity.Vendas;
import jakarta.validation.constraints.NotNull;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long> {
	
	public List<Vendas> findByDataVenda(@NotNull String dataVenda);
	
	public List<Vendas> findByValorTotal(double valorTotal);
	
	@Query("FROM Vendas v WHERE v.enderecoEntrega LIKE %:enderecoEntrega%")
	public List<Vendas> findByEnderecoEntrega(String enderecoEntrega);
	

}
