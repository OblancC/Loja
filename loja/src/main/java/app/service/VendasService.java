package app.service;

import java.util.List;
import java.util.Optional;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.entity.Vendas;
import app.repository.VendasRepository;
import jakarta.validation.Valid;

@Service
public class VendasService {
	
	@Resource
	private VendasRepository vendasRepository;
		
	public String save(@Valid Vendas vendas) {
		double valorTotal = calcularValorTotal(vendas.getProduto());
		vendas.setValorTotal(valorTotal);
		vendasRepository.save(vendas);
		return "Vendas salva com sucesso";
	}
	
	public Vendas findById(Long id) {
		Optional<Vendas> vendas = vendasRepository.findById(id);
		return vendas.get();
	}
	
	public List<Vendas> listAll(){
		List<Vendas> vendas = vendasRepository.findAll();
		return vendas;
	}
	
	public String delete(Long id) {
		vendasRepository.deleteById(id);
		return "Item deletado com sucesso!";
	}
	
	public String update(Vendas vendas, Long id) {
		vendas.setId(id);
			double valorTotal = calcularValorTotal(vendas.getProduto());
			vendas.setValorTotal(valorTotal);
			vendas = verificarStatus(vendas);
			vendasRepository.save(vendas);
			return "Item atualizado com sucesso!";
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
        return vendasRepository.findByEnderecoEntrega(enderecoEntrega);
	}
	
	
	
	public List<Vendas> findByValorTotal(double valorTotal){
        return vendasRepository.findByValorTotal(valorTotal);
	}
	
	public List<Vendas> findByDataVenda(String dataVenda){
		if(dataVenda == null)
			throw new RuntimeException();
        return vendasRepository.findByDataVenda(dataVenda);
	}
	
}
