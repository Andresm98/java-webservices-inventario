package minimarketdemo.model.productos.dtos;

import java.util.List;

import minimarketdemo.model.core.entities.InvCategoriaProducto;

public class DTOInvProductos {

	private String codigoProducto;
	private double costo;
	private String descripcion;
	private Boolean estado;
	private double iva;
	private String nombreProducto;
	private double pvp;
	private Integer stock;
	private Integer idCategoriaProducto;

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getIdCategoriaProducto() {
		return idCategoriaProducto;
	}

	public void setIdCategoriaProducto(Integer idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public DTOInvProductos(String codigoProducto, double costo, String descripcion, Boolean estado, double iva,
			String nombreProducto, double pvp, Integer stock, Integer idCategoriaProducto) {
		super();
		this.codigoProducto = codigoProducto;
		this.costo = costo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.iva = iva;
		this.nombreProducto = nombreProducto;
		this.pvp = pvp;
		this.stock = stock;
		this.idCategoriaProducto = idCategoriaProducto;
	}
		

}
