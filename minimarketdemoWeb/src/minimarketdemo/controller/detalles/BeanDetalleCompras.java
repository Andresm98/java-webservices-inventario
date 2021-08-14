package minimarketdemo.controller.detalles;

import java.io.Serializable;
import java.security.Provider.Service;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.api.rest.auditoria.ServicioRESTAuditoria;
import minimarketdemo.api.rest.cliente.detalle.ServiceRestClienteDetalleCompras;
import minimarketdemo.model.detalle.managers.*;

@Named
@SessionScoped
public class BeanDetalleCompras implements Serializable {


	
	private List<DetalleCompras> liscDetalleC;

	public BeanDetalleCompras() {

	}


	public String mostrar() {
		ServiceRestClienteDetalleCompras sra = new ServiceRestClienteDetalleCompras();
		liscDetalleC = sra.init();
		return "detalle";
	}

	public List<DetalleCompras> getLiscDetalleC() {
		return liscDetalleC;
	}

	public void setLiscDetalleC(List<DetalleCompras> liscDetalleC) {
		this.liscDetalleC = liscDetalleC;
	}
}
