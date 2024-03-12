package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Este campo não pode ser nulo")
	private String nome;
	@NotNull(message = "Este campo não pode ser nulo")
	private int idade;
	@NotNull(message = "Este campo não pode ser nulo")
	private int matricula;
	
	@OneToMany(mappedBy = "funcionario")
	@JsonIgnoreProperties("funcionario")
	private List<Vendas> vendas;
}
