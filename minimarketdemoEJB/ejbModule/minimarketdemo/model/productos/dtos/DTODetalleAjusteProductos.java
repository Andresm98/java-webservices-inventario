package minimarketdemo.model.productos.dtos;

import java.io.Serializable;
import java.util.Date;

public class DTODetalleAjusteProductos{

	/**
	 * 
	 */
	
//private 
	private String motivo;
	private String tipoAjuste;
	private Date fecha;
	private String nombre;
	private String descripcion;
	private Integer stock;
	public DTODetalleAjusteProductos(String motivo, String tipoAjuste, Date fecha, String nombre, String descripcion,
			Integer stock) {
		super();
		this.motivo = motivo;
		this.tipoAjuste = tipoAjuste;
		this.fecha = fecha;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getTipoAjuste() {
		return tipoAjuste;
	}
	public void setTipoAjuste(String tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	

	
	
}
