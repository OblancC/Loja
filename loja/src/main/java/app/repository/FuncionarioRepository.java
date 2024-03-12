package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	List<Funcionario> findByAge(int idade);
	
	Funcionario findByMatricula(int matricula);
	
	@Query("FROM Funcionario f WHERE f.nome LIKE %:nome%")
	List<Funcionario> findByName(String nome);
}
