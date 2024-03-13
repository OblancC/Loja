package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Vendas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Este campo não pode ser nulo")
	private double valorTotal;
	private String dataVenda;
	private String enderecoEntrega;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("vendas")
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "vendas_produto")
	private List<Produto> produto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("vendas")
	private Funcionario funcionario;
}
