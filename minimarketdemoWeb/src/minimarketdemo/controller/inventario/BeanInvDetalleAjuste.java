/**
 * 
 */
package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.core.entities.InvAjusteProducto;
import minimarketdemo.model.core.entities.InvDetalleAjusteProducto;
import minimarketdemo.model.inventario.managers.ManagerAjusteDetalleProd;

@Named
@SessionScoped
/**
 * @author fedora
 *
 */
public class BeanInvDetalleAjuste implements Serializable {

	@EJB
	private ManagerAjusteDetalleProd mAjusteProd;	
	
	private InvDetalleAjusteProducto nuevoDetalleAjuste;
	private List<InvDetalleAjusteProducto> listaDetalleAjuste;
	////
	private InvAjusteProducto nuevoAjuste;
	private List<InvAjusteProducto> listaAjuste;
	
	
	/**
	 * 
	 */
	public BeanInvDetalleAjuste() {
		// TODO Auto-generated constructor stub
	}
	
	
	@PostConstruct
	public void cargar() {
		listaDetalleAjuste = mAjusteProd.findAllDetalle();
		nuevoDetalleAjuste = new InvDetalleAjusteProducto();
		/// 
		///listaAjuste = mAjusteProd.findAllAjustes();
///		nuevoAjuste = new InvAjusteProducto();
		
	}

	public ManagerAjusteDetalleProd getmAjusteProd() {
		return mAjusteProd;
	}


	public void setmAjusteProd(ManagerAjusteDetalleProd mAjusteProd) {
		this.mAjusteProd = mAjusteProd;
	}


	public InvDetalleAjusteProducto getNuevoDetalleAjuste() {
		return nuevoDetalleAjuste;
	}


	public void setNuevoDetalleAjuste(InvDetalleAjusteProducto nuevoDetalleAjuste) {
		this.nuevoDetalleAjuste = nuevoDetalleAjuste;
	}


	public List<InvDetalleAjusteProducto> getListaDetalleAjuste() {
		return listaDetalleAjuste;
	}


	public void setListaDetalleAjuste(List<InvDetalleAjusteProducto> listaDetalleAjuste) {
		this.listaDetalleAjuste = listaDetalleAjuste;
	}


	public InvAjusteProducto getNuevoAjuste() {
		return nuevoAjuste;
	}


	public void setNuevoAjuste(InvAjusteProducto nuevoAjuste) {
		this.nuevoAjuste = nuevoAjuste;
	}


	public List<InvAjusteProducto> getListaAjuste() {
		return listaAjuste;
	}


	public void setListaAjuste(List<InvAjusteProducto> listaAjuste) {
		this.listaAjuste = listaAjuste;
	}
	
	
	
	

}
