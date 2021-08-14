package minimarketdemo.model.detalle.managers;

import java.io.Serializable;

public class DetalleCompras implements Serializable {

	private int cantidadproducto;
	private String codigoproducto ;
	private String detalleFacturaProducto;
	private int id_factura;
	
	public DetalleCompras() {
		
	}

	public int getCantidadproducto() {
		return cantidadproducto;
	}

	public void setCantidadproducto(int cantidadproducto) {
		this.cantidadproducto = cantidadproducto;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public String getDetalleFacturaProducto() {
		return detalleFacturaProducto;
	}

	public void setDetalleFacturaProducto(String detalleFacturaProducto) {
		this.detalleFacturaProducto = detalleFacturaProducto;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	

}
