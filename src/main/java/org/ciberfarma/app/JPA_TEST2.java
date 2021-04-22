package org.ciberfarma.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ciberfarma.modelo.Producto;

public class JPA_TEST2 {
	
	public static void name(String[] args) {
		
		Producto p = new Producto();
		//p.setIdprod(12);
		p.setDescripcion("Papel noble");
		p.setStock(35);
		p.setPrecio(8.60);
		p.setIdcategoria(4);
		p.setEstado(1);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("secion_1");
		EntityManager em=factory.createEntityManager();
		
		em.getTransaction().begin();
		//em.persist(p);
		em.merge(p);
		em.getTransaction().commit();
		
	    List<Producto> lsProducto = em.createNamedQuery("Select p from Producto p", Producto.class).getResultList();
	    if(lsProducto==null) {
	    	System.out.println("Producto no existente");
	    }else {
	    	for (Producto producto : lsProducto) {
	    		System.out.println(producto.getIdprod()+" , "+producto.getDescripcion());
				
			}
	    }
		
	}


}
