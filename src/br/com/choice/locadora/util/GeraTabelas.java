package br.com.choice.locadora.util;

public class GeraTabelas {
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory().openSession();
	}
}