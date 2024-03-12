package app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public String save(Produto produto) {
		this.produtoRepository.save(produto);
		return "Autor salvo com sucesso";
	}
	
	public Produto findById(Long id) {
		Optional<Produto> produto = this.produtoRepository.findById(id);
		return produto.get();
	}
	
	public List<Produto> listAll() {
		List<Produto> produto = this.produtoRepository.findAll();
		return produto;
	}
	
	public String delete(Long id) {
		this.produtoRepository.deleteById(id);
		return "Produto deletado com sucesso";
	}
	
	public String update(Produto produto, Long id) {
		produto.setId(id);
		this.produtoRepository.save(produto);
		return "Produto atualizado com sucesso!";
	}
}
