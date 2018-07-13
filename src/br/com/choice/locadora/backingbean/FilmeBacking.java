package br.com.choice.locadora.backingbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.locadora.bean.Filme;
import br.com.choice.locadora.dao.FilmeDAO;
import br.com.choice.locadora.util.JSFUtil;

public class FilmeBacking {

	private Filme filme = new Filme();
	private Filme filmeSelecionado = new Filme();
	private int codigo;
	private String genero;
	private String nome;
	private String disponivel;
	private double valor;
	private List<Filme> listaFilme;

	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public List<Filme> getListaFilme() {
		return listaFilme;
	}
	public void setListaFilme(List<Filme> listaFilme) {
		this.listaFilme = listaFilme;
	}

	public String salvar(){
		if (!validarDados()) {
			filme = new Filme();
			filme.setNome(nome);
			filme.setValor(valor);
			filme.setGenero(genero);
			filme.setDisponivel(disponivel);
			try {
				FilmeDAO dao = new FilmeDAO();
				dao.salvar(filme);
				limparDados();
				JSFUtil.addInfoMessage("Filme salvo com sucesso!");
			} catch (SQLException e) {
				JSFUtil.addInfoMessage("Erro ao salvar FILME!");
			}
		}
		return "";
	}
	
	public String filmePesquisar(){
		listaFilme = new ArrayList<Filme>();
		
		FilmeDAO dao = new FilmeDAO();
		try {
			listaFilme = dao.pesquisarPorNome(nome);
		} catch (Exception e) {
			JSFUtil.addInfoMessage("Erro ao listar Filmes!");
		}
		return "";
	}
	
	public boolean validarDados(){
		if (nome.equals(null)) {
			JSFUtil.addInfoMessage("Informe o nome, campo obrigatório!");
			return true;
		}else {
			filme.setNome(nome);
		}
		if (valor == 0) {
			JSFUtil.addInfoMessage("Informe o valor, campo obrigatório!");
			return true;
		}else {
			filme.setValor(valor);
		}
		if (genero.equals(null)) {
			JSFUtil.addInfoMessage("Informe o genero, campo obrigatório!");
			return true;
		}else {
			filme.setGenero(genero);
		}
		if (disponivel.equals("") || disponivel.isEmpty()) {
			JSFUtil.addInfoMessage("Informe a disponibilidade, campo obrigatório!");
			return true;			
		}else {
			filme.setDisponivel(disponivel);
		}
		return false;
	}

	public String filmeSelecionado() {
		return "";
	}
	
	public String filmeAlterar(){
		System.out.println(filmeSelecionado.getNome());
		return "";
	}

	public String filmeExcluir(){
		System.out.println(filmeSelecionado.getNome());
		return "";
	}
	
	public String cadastrarFilme(){
		return "";
	}
	
	public String consultarFilme(){
		return "";
	}
	
	public String cancelar(){
		return "cancelar";
	}
	
	public void limparDados(){
		nome = null;
		disponivel = null;
		genero = null;
		valor = 0.0;
	}

}