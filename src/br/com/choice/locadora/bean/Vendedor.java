package br.com.choice.locadora.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="vendedor")
public class Vendedor {

	@Id
	@GeneratedValue
	private int codigo;

	@Column (name="nome")
	private String nome;

	@Column (name="idade")
	private int idade;
	
	@Column (name="cep")
	private String cep;
	
	@Column (name="cpf")
	private String cpf;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
