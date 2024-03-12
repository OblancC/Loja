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

import app.entity.Cliente;
import app.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Cliente cliente){
		
		try {
			System.out.println(cliente.getNome());
			String mensagem = this.clienteService.save(cliente);
			return new ResponseEntity<String>(mensagem , HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String> ("Foi encontrado um Erro!", HttpStatus.BAD_REQUEST);
		}
	}
	
		@GetMapping("/findById/{id}")
		public ResponseEntity<Cliente> findById(@PathVariable Long id){
			try {
				Cliente cliente = this.clienteService.findById(id);
				return new ResponseEntity<>(cliente, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/listAll")
		public ResponseEntity<List<Cliente>> findAll(){
			try {
				List<Cliente> cliente = this.clienteService.listAll();
				return new ResponseEntity<>(null,HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@PutMapping("/update/{id}")
		public ResponseEntity<String> update(@PathVariable long id,@RequestBody Cliente cliente){
			try {
				String mensagem = this.clienteService.update(cliente, id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByCpf")
		public ResponseEntity<Cliente> findByCpf (@RequestParam String cpf){
			try {
				Cliente cliente = this.clienteService.findByCpf(cpf);
				return new ResponseEntity<>(cliente,HttpStatus.CREATED);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByName")
		public ResponseEntity<List<Cliente>> findByName (@RequestParam String nome){
			try {
				List<Cliente> lista = this.clienteService.findByName(nome);
				return new ResponseEntity<>(lista,HttpStatus.CREATED);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByOlder")
		public ResponseEntity<List<Cliente>> findByOlder (@RequestParam int idade){
			try {
				List<Cliente> lista = this.clienteService.findByOlder(idade);
				return new ResponseEntity<>(lista,HttpStatus.CREATED);
			}catch(Exception e){
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
				
			}
		}
		
		@GetMapping("/findByNameLike")
		public ResponseEntity<List<Cliente>> findByNameLike (@RequestParam String nome){
			try {
				List<Cliente> lista = this.clienteService.findByName(nome);
				return new ResponseEntity<>(lista,HttpStatus.CREATED);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		
}
