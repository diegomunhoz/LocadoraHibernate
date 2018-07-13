package br.com.choice.locadora.backingbean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.locadora.bean.Vendedor;
import br.com.choice.locadora.dao.VendedorDAO;
import br.com.choice.locadora.util.JSFUtil;

public class VendedorBacking {

	private Vendedor vendedor = new Vendedor();
	private Vendedor vendedorSelecionado = new Vendedor();
	private int codigo;
	private String nome;
	private int idade;
	private String cep;
	private String cpf;
	List<Vendedor> listaVendedor;
	private List<Vendedor> listaVendedorTotal;
	private int scrollerPage;
	private int qtdLinhas;
	private int paginaTotal;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}
	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}
	public List<Vendedor> getListaVendedor() {
		return listaVendedor;
	}
	public void setListaVendedor(List<Vendedor> listaVendedor) {
		this.listaVendedor = listaVendedor;
	}
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

	public String salvar(){
		if (validar()) {
			vendedor = new Vendedor();
			vendedor.setNome(nome);
			vendedor.setIdade(idade);
			vendedor.setCep(cep);
			vendedor.setCpf(cpf);
			try {
				VendedorDAO dao = new VendedorDAO();
				dao.salvar(vendedor);
				limparDados();
				JSFUtil.addInfoMessage("Vendedor salvo com sucesso!");
			} catch (SQLException e) {
				JSFUtil.addInfoMessage("Erro ao salvar Vendedor!");
			}
		}
		return "";
	}

	public String alterar(){
		if (validarAlterar()) {
			try {
				VendedorDAO dao = new VendedorDAO();
				dao.salvar(vendedorSelecionado);
				limparDados();
				JSFUtil.addInfoMessage("Vendedor alterado com sucesso!");
			} catch (SQLException e) {
				JSFUtil.addInfoMessage("Erro ao alterar Vendedor!");
			}
		}
		return "";
	}

	public boolean validar(){
		if (nome.equals("") || nome.isEmpty()) {
			JSFUtil.addInfoMessage("Informe o nome, campo obrigatório!");
			return false;
		}else {
			vendedor.setNome(nome);
		}
		if (idade == 0) {
			JSFUtil.addInfoMessage("Informe o idade, campo obrigatório!");
			return false;
		}else {
			vendedor.setIdade(idade);
		}
		if (cep.equals(null) || cep.isEmpty() || cep.equals(" ")) {
			JSFUtil.addInfoMessage("Informe o CEP, campo obrigatório!");
			return false;
		}else {
			vendedor.setCep(cep);
		}
		if (cpf.equals(null) || cpf.isEmpty() || cpf.equals(" ")) {
			JSFUtil.addInfoMessage("Informe a CEP, campo obrigatório!");
			return false;			
		}else {
			vendedor.setCpf(cpf);
		}
		return true;
	}

	public boolean validarAlterar(){
		if (nome.equals("") || nome.isEmpty()) {
			JSFUtil.addInfoMessage("Informe o nome, campo obrigatório!");
			return false;
		}else {
			vendedorSelecionado.setNome(nome);
		}
		if (idade == 0) {
			JSFUtil.addInfoMessage("Informe o idade, campo obrigatório!");
			return false;
		}else {
			vendedorSelecionado.setIdade(idade);
		}
		if (cep.equals(null) || cep.isEmpty() || cep.equals(" ")) {
			JSFUtil.addInfoMessage("Informe o CEP, campo obrigatório!");
			return false;
		}else {
			vendedorSelecionado.setCep(cep);
		}
		if (cpf.equals(null) || cpf.isEmpty() || cpf.equals(" ")) {
			JSFUtil.addInfoMessage("Informe a CEP, campo obrigatório!");
			return false;			
		}else {
			vendedorSelecionado.setCpf(cpf);
		}
		return true;
	}
	
	public void limparDados(){
		nome = null;
		idade = 0;
		cep = null;
		cpf = null;
	}
	public String cadastrarVendedor(){
		return "";
	}
	
	public String consultarVendedor(){
		listarVendedor();
		return "";
	}

	public String alterarVendedor(){
		nome = vendedorSelecionado.getNome();
		idade = vendedorSelecionado.getIdade();
		cep = vendedorSelecionado.getCep();
		cpf = vendedorSelecionado.getCpf();
		return "alterar";
	}

	public String excluirVendedor(){
		try {
			VendedorDAO dao = new VendedorDAO();
			dao.excluir(vendedorSelecionado);
			limparDados();
			JSFUtil.addInfoMessage("Vendedor excluído com sucesso!");
		} catch (SQLException e) {
			JSFUtil.addInfoMessage("Erro ao excluir Vendedor!");
		}
		listarVendedor();
		return "";
	}

	public String listarVendedor(){
		try {
			scrollerPage = 1;
			qtdLinhas = 10;
			listaVendedorTotal = new VendedorDAO().buscarTodos();
			if (listaVendedorTotal.size()%10==0){
				paginaTotal = listaVendedorTotal.size()/10;
			}else{
				paginaTotal = (listaVendedorTotal.size()/10)+1;
			}
			montarLista();
		} catch (Exception e) {
			JSFUtil.addInfoMessage("Erro na busca.");
		}
		return "";
	}

	public String pesquisarVendedor(){
		try {
			scrollerPage = 1;
			qtdLinhas = 10;
			listaVendedorTotal = new VendedorDAO().pesquisarPorNome(nome);
			if (listaVendedorTotal.size()%10==0){
				paginaTotal = listaVendedorTotal.size()/10;
			}else{
				paginaTotal = (listaVendedorTotal.size()/10)+1;
			}
			montarLista();
		} catch (Exception e) {
			JSFUtil.addInfoMessage("Erro na busca.");
		}
		return "";
	}

	
	public String voltar(){
		if (scrollerPage>1){
			scrollerPage--;
			montarLista();
			return "atualizar";
		}else{
			JSFUtil.addInfoMessage("Não há mais páginas para voltar.");
			return "";
		}		
	}
	
	public String avancar(){
		if (scrollerPage<paginaTotal){
			scrollerPage++;
			montarLista();
			return "atualizar";
		}else{
			JSFUtil.addInfoMessage("Não há mais páginas para avançar.");
			return "";
		}
	}
	
	public void montarLista (){
		listaVendedor = new ArrayList<Vendedor>();
		int contador = 0;
		int contCurso = 0;
		for (Vendedor v : listaVendedorTotal){
			contCurso++;
			if (contador==qtdLinhas)
				break;
			if ((contCurso<=(qtdLinhas*scrollerPage))&&
			    (contCurso>(qtdLinhas*(scrollerPage-1)))){
				listaVendedor.add(v);
				contador++;
			}
		}
	}

	public String vendedorSelecionado() {
		return "";
	}

	public String cancelar(){
		nome = "";
		listarVendedor();
		return "cancelar";
	}

}