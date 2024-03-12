package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Vendas;
import app.repository.VendasRepository;

@Service
public class VendasService {
	
	@Autowired
	private VendasRepository vendasRepository;
		
	public String save(Vendas vendas) {
		this.vendasRepository.save(vendas);
		return "Vendas salva com sucesso";
	}
	
	public Vendas findById(Long id) {
		Optional<Vendas> vendas = this.vendasRepository.findById(id);
		return vendas.get();
	}
	
	public List<Vendas> listAll(){
		List<Vendas> vendas = this.vendasRepository.findAll();
		return vendas;
	}
	
	public String delete(Long id) {
		this.vendasRepository.deleteById(id);
		return "Item deletado com sucesso!";
	}
	
	public String update(Vendas vendas, Long id) {
		vendas.setId(id);
		this.vendasRepository.save(vendas);
		return "Item atualizado com sucesso!";
	}
}
