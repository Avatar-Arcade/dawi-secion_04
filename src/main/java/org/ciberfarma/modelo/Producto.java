package org.ciberfarma.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_productos")
@NamedQuery(name="Producto.fiendall",query = "Select p from Producto p")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idprod",unique = true,nullable = false)
	private String idprod;
	
	@Column(name="descripcion",length = 45)
	private String descripcion;
	
	@Column(name="stock")
	private int stock;
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name="idcategoria")
	private int idcategoria;
	
	@Column(name="estado")
	private int estado;
	
	
	public String getIdprod() {
		return idprod;
	}
	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	

}
