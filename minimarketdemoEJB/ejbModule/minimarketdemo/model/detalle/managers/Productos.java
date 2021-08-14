package minimarketdemo.model.detalle.managers;

import java.io.Serializable;

public class Productos implements Serializable {

	private String codproducto;
	private String nombre;
	private int cantidad;
	private double iva;
	private double precio;
	
	public Productos() {
		
	}

	

	public String getCodproducto() {
		return codproducto;
	}



	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
