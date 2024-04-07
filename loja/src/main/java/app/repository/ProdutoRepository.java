package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List<Produto> findByValor(double valor);
	
	public List<Produto> findByCategoria(String categoria);
	
	@Query("FROM Produto p WHERE p.nome LIKE %:nome%")
	public List<Produto> findByNome(String nome);
}
