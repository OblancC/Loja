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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Vendas;
import app.service.VendasService;

@RestController
@RequestMapping("/api/vendas")
public class VendasController {
	@Autowired
	private VendasService vendasService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Vendas vendas){
		try {
			String mensagem = this.vendasService.save(vendas);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String> ("Erro Detectado", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			String mensagem = this.vendasService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>("Ocorreu esse erro: " + e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Vendas> findById(@PathVariable Long id){
		try {
			Vendas vendas = this.vendasService.findById(id);
			return new ResponseEntity<>(vendas, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<List<Vendas>> listAll(){
		try {
			List<Vendas> vendas = this.vendasService.listAll();
			return new ResponseEntity<> (vendas, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Vendas vendas){
		try {
			String mensagem = this.vendasService.update(vendas,id);
			return new ResponseEntity<>(mensagem,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByEndereco")
	public ResponseEntity<List<Vendas>> findByEnderecoEntrega(@RequestParam String enderecoEntrega){
		try {
			List<Vendas> lista = this.vendasService.findByEnderecoEntrega(enderecoEntrega);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByDataVendas")
	public ResponseEntity<List<Vendas>> findByDataVenda(@RequestParam String dataVenda){
		try {
			List<Vendas> lista = this.vendasService.findByDataVenda(dataVenda);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByValorVendas")
	public ResponseEntity<List<Vendas>> findByValorVenda(@RequestParam double valorTotal){
		try {
			List<Vendas> lista =this.vendasService.findByValorTotal(valorTotal);
			return new ResponseEntity<>(lista,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
}
