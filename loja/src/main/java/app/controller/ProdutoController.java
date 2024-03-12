package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Produto;
import app.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Produto produto){
		try {
			String mensagem = this.produtoService.save(produto);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Erro detectado", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		try {
			Produto produto = this.produtoService.findById(id);
			return new ResponseEntity<>(produto,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Produto>> listAll() {
		try {
			List<Produto> produto = this.produtoService.listAll();
			return new ResponseEntity<>(null,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id,@RequestBody Produto produto){
		try {
			String mensagem = this.produtoService.update(produto, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
}
