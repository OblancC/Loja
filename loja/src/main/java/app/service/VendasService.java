package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.entity.Vendas;
import app.repository.VendasRepository;
import jakarta.validation.Valid;

@Service
public class VendasService {
	
	@Autowired
	private VendasRepository vendasRepository;
		
	public String save(@Valid Vendas vendas) {
		double valorTotal = this.calcularValorTotal(vendas.getProduto());
		vendas.setValorTotal(valorTotal);
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
		try {
			this.vendasRepository.deleteById(id);
			return "Item deletado com sucesso!";	
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public String update(Vendas vendas, Long id) {
		vendas.setId(id);
		if(vendas.getStatus().equals("CANCELADO")) {
			vendas.setProduto(null);
			vendas.setValorTotal(0);
			return "Produto Foi Cancelado";
		}else {
			double valorTotal = this.calcularValorTotal(vendas.getProduto());
			vendas.setValorTotal(valorTotal);
			vendas = this.verificarStatus(vendas);
			this.vendasRepository.save(vendas);
			return "Item atualizado com sucesso!";
		}
	}
	
	public Vendas verificarStatus(Vendas vendas) {
		if(vendas.getStatus().equals("CANCELADO")) {
			vendas.setValorTotal(0);
			vendas.setProduto(null);
		}
		return vendas;
	}
	
	public double calcularValorTotal(List<Produto> produtos) {
		double soma = 0;
		if(produtos!= null)
			for(int i=0;i<produtos.size();i++) {
				soma += produtos.get(i).getValor();
			}
		return soma;
	}
	

	public List<Vendas> findByEnderecoEntrega(String enderecoEntrega){
		List<Vendas> vendas = vendasRepository.findByEnderecoEntrega(enderecoEntrega);
		return vendas;
	}
	
	
	
	public List<Vendas> findByValorTotal(double valorTotal){
		List<Vendas> vendas = vendasRepository.findByValorTotal(valorTotal);
		return vendas;
	}
	
	public List<Vendas> findByDataVenda(String dataVenda){
		List<Vendas> vendas = vendasRepository.findByDataVenda(dataVenda);
		return vendas;
	}
	
}
