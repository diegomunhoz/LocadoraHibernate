package br.com.choice.locadora.backingbean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.choice.locadora.util.Conexao;

public class RelatorioBacking {

	private String saida;

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	/**
	 * Método para preencher o PDF do relatório
	 * 
	 * @param print JasperPrint
	 * @throws JRException
	 */
	private void preenchePdf(JasperPrint print) throws JRException {
		// Pego o caminho completo do PDF desde a raiz
		saida = getDiretorioReal("/pdf/relatorio.pdf");
		// Exporto para PDF
		JasperExportManager.exportReportToPdfFile(print, saida);
		/*
		 * Jogo na variável saída o nome da aplicação mais o 
		 * caminho para o PDF. Essa variável será utilizada pela view 
		 */
		saida = getContextPath() + "/pdf/relatorio.pdf";
	}

	/**
	 * Método para retornar o caminho completo do diretório onde se encontra o 
	 * arquivo 'jasper' e o arquivo 'pdf'
	 *  
	 * @param diretorio String diretório a ser localizado na aplicação
	 * @return String caminho completo
	 */
	private String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}
	
	/**
	 * Método para retornar o nome da aplicação
	 *  
	 * @return String nome da aplicacao 
	 */
	private String getContextPath() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getContextPath();
	}

	public String geraRelatorio() {
			saida = null;
			String jasper = getDiretorioReal("/jasper/filme.jasper");
			Connection conexao = null;

			try {
				// Abro a conexão com o banco
				conexao = new Conexao().getConexao();
				// Gero o ResultSet que será enviado para o Jasper a partir da conexão aberta
				JRResultSetDataSource jrsds = new JRResultSetDataSource(getResultSet(conexao));				
				// Mando o jasper gerar o relatório
				JasperPrint print = JasperFillManager.fillReport(jasper, null, jrsds);
				// Gero o PDF
				preenchePdf(print);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					// Sempre mando fechar a conexão, mesmo que tenha dado erro
					if (conexao != null)
						conexao.close();
				} catch (SQLException e) {
					
				}
			}
			return "exibeRelatorio";
	}
	
	private ResultSet getResultSet(Connection conexao) throws SQLException, ClassNotFoundException {
		Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT filme.codigo AS filme_codigo, " +
				"filme.nome AS filme_nome, filme.genero AS filme_genero, " +
				"filme.valor AS filme_valor FROM filme filme ");
		return rs;
	}

}