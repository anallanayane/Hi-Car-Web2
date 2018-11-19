package com.example.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servico")
public class Servico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "tempoMedio")
	private Integer tempoMedio;

	@Column(name = "valor")
	private Double valor;
	
	/*@ManyToOne
	@JoinColumn(name="module_id")
	private Module module;
	*/
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTempoMedio() {
		return tempoMedio;
	}

	public void setTempoMedio(Integer tempoMedio) {
		this.tempoMedio = tempoMedio;
	}

}
