package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Funcionario;
import app.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionario funcionario){
		try {
			System.out.println(funcionario.getNome());
			String mensagem = this.funcionarioService.save(funcionario);
			return new ResponseEntity<String>(mensagem , HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String> ("Erro Detectado", HttpStatus.BAD_REQUEST);
			
		}
	}
		@GetMapping("/findById/{id}")
		public ResponseEntity<Funcionario> findById(@PathVariable Long id){
			try {
				Funcionario funcionario = this.funcionarioService.findById(id);
				return new ResponseEntity<>(funcionario, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		@PutMapping("/update/{id}")
		public ResponseEntity<String> update(@PathVariable long id, @RequestBody Funcionario Funcionario){
			try {
				String mensagem = this.funcionarioService.update(Funcionario, id);
				return new ResponseEntity<>(mensagem , HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByAge")
		public ResponseEntity<List<Funcionario>> findByAge(@RequestParam int idade){
			try {
				List<Funcionario> lista = this.funcionarioService.findByAge(idade);
				return new ResponseEntity<>(lista,HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByMatricula")
		public ResponseEntity<Funcionario> findByMatricula(@RequestParam int matricula){
			try {
				Funcionario lista = this.funcionarioService.findByMatricula(matricula);
				return new ResponseEntity<>(lista,HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByName")
		public ResponseEntity<List<Funcionario>> findByName(@RequestParam String name){
			try {
				List<Funcionario> lista = this.funcionarioService.findByName(name);
				return new ResponseEntity<>(lista,HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
		}
		
}
