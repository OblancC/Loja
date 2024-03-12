package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

	@Query("FROM Cliente c WHERE c.nome LIKE %:nome%")
	public List<Cliente> findByNameLike(String nome);
	
	public List<Cliente> findByName(String nome);
	
	@Query("FROM Cliente c WHERE c.idade> :idade")
	public List<Cliente> findByOlder(int idade);

	public Cliente findByCpf(String cpf);
	

}
