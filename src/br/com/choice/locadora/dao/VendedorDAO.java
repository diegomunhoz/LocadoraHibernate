package br.com.choice.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.choice.locadora.bean.Vendedor;
import br.com.choice.locadora.util.HibernateUtil;

public class VendedorDAO extends GenericDAO{
	
	public Vendedor buscarPorCodigo (Long codigo) throws Exception{
		Session sessao = null;
		Vendedor vendedor = null;

		sessao = HibernateUtil.getSessionFactory().openSession();
		vendedor = (Vendedor) sessao.get(Vendedor.class, codigo);

		return vendedor;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Vendedor> buscarTodos() throws Exception{
		ArrayList<Vendedor> listaRetorno = new ArrayList<Vendedor>();
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Criteria criteria = sessao.createCriteria(Vendedor.class);
		
		criteria.addOrder (Order.asc("codigo"));
		
		listaRetorno = (ArrayList<Vendedor>) criteria.list();
		
		return listaRetorno;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Vendedor> pesquisarPorNome(String nome) throws Exception{
		ArrayList<Vendedor> listaRetorno = new ArrayList<Vendedor>();
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Criteria criteria = sessao.createCriteria(Vendedor.class);

		criteria.add(Restrictions.ilike("nome", "%" + nome + "%"));

		criteria.addOrder(Order.asc("nome"));
		
		listaRetorno = (ArrayList<Vendedor>) criteria.list();
		
		return listaRetorno;
	}
}