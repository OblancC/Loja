package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public String save(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
		return "Autor salvo com sucesso!";
	}
	
	public Funcionario findById(Long id) {
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		return funcionario.get();
	}
	
	public List<Funcionario> listAll(){
		List<Funcionario> funcionario = this.funcionarioRepository.findAll();
		return funcionario;
	}
	
	public String delete(Long id) {
		this.funcionarioRepository.deleteById(id);
		return "Item deletado com Sucesso";
	}
	
	public String update(Funcionario funcionario, long id) {
		funcionario.setId(id);
		this.funcionarioRepository.save(funcionario);
		return "Item Atualizado com sucesso!";
	}
	
	public List<Funcionario> findByIdade(int idade){
		List<Funcionario> funcionario= funcionarioRepository.findByIdade(idade);
		return funcionario;
	}
	
	public Funcionario findByMatricula(int matricula){
		Funcionario	funcionario = funcionarioRepository.findByMatricula(matricula);
		return funcionario;
	}
	
	public List<Funcionario> findByNome(String nome){
		List<Funcionario> funcionario = funcionarioRepository.findByNome(nome);
		return funcionario;
	}
}
