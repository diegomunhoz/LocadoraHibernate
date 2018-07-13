package br.com.choice.locadora.teste;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.choice.locadora.bean.Filme;
import br.com.choice.locadora.dao.FilmeDAO;

public class TestaFilme {

	public static void main(String[] args) {
		Filme filme = new Filme();
		FilmeDAO dao = new FilmeDAO();
		filme.setNome("A Era do Gelo 3");
		filme.setValor(1.50);
		filme.setDisponivel("SIM");
		filme.setGenero("Animação");
		try {
			dao.salvar(filme);
			JOptionPane.showMessageDialog(null, "Filme salvo com sucesso");
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}