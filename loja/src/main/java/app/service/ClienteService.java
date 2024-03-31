package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cliente;
import app.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public String save(Cliente cliente) {
		this.clienteRepository.save(cliente);
		return "Cliente salvo com sucesso!";
	}
	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		return cliente.get();
	}
	public List<Cliente> listAll(){
		List<Cliente> cliente = this.clienteRepository.findAll();
		return cliente;
	}
	
	public String delete(Long id) {
		this.clienteRepository.deleteById(id);
		return "Cliente Deletado com Sucesso";
	}
	
	public String update(Cliente cliente, long id) {
		cliente.setId(id);
		this.clienteRepository.save(cliente);
		return "Cliente Atualizado com sucesso!";
	}

	public Cliente findByCpf(String cpf) {
		Cliente cliente = clienteRepository.findByCpf(cpf);
		return cliente;
	}
	
	public List<Cliente> findByNome(String nome) {
		List<Cliente> lista = clienteRepository.findByNome(nome);
		return lista;
	}

	public List<Cliente> findByOlder(int idade) {
		List<Cliente> lista = clienteRepository.findByOlder(idade);
		return lista;
	}
	
	public List<Cliente> findByNomeLike(String nome) {
		List<Cliente> lista = clienteRepository.findByNomeLike(nome);
		return lista;
	}
}
