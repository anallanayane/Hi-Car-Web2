package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "os")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "servico", nullable = false)
	private String servico;

	@Column(name = "veiculo")
	private String veiculo;

	@Column(name = "funcionario")
	private String funcionario;
	

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getServico() {
		return servico;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
