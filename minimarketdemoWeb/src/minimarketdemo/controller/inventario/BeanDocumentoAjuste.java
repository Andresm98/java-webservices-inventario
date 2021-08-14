/**
 * 
 */
package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvAjusteProducto;
import minimarketdemo.model.core.entities.InvDetalleAjusteDocumento;
import minimarketdemo.model.core.entities.InvDetalleAjusteProducto;
import minimarketdemo.model.inventario.managers.ManagerAjusteDetalleProd;
import minimarketdemo.model.inventario.managers.ManagerInventario;

@Named
@SessionScoped
/**
 * @author fedora
 *
 */
public class BeanDocumentoAjuste implements Serializable {

	@EJB
	private ManagerAjusteDetalleProd mAjusteDetalleDocumento;

	@EJB
	private ManagerAjusteDetalleProd mAjusteProd;
	
	@EJB
	private ManagerInventario mInventarioProducto;

	private InvDetalleAjusteDocumento nuevoDetalleAjusteDocumento;

	private List<InvDetalleAjusteDocumento> listaDetalleAjusteDocumento;
	private List<InvDetalleAjusteProducto> listaDetalleAjusteProducto;
	private List<InvDetalleAjusteProducto> listaDetalleAjusteProducto2;
	private InvDetalleAjusteProducto nuevoDetalleAjuste;
	private List<InvDetalleAjusteProducto> listaDetalleAjuste;
	private List<InvAjusteProducto> listaAjusteProducto;
	
	private InvAjusteProducto ajusteProductoSeleccionado;
	
	private List<InvDetalleAjusteDocumento> listaReportesDocumento;
	private Date fechaInicio;
	private Date fechaFin;

	/**
	 * 
	 */
	public BeanDocumentoAjuste() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void cargar() {
		listaDetalleAjusteDocumento = mAjusteDetalleDocumento.findAllAjusteDetalleDocumento();
	}

	public void actionListenerConsultarDocumentos() {
		listaReportesDocumento = mAjusteDetalleDocumento.findDocumentosByFecha(fechaInicio, fechaFin);
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaReportesDocumento.size());
	}
	
	
	public String detalleDelAjusteImpresion(Integer idAjusteProducto) {
		try {
			if (mAjusteDetalleDocumento.regresarEstadoDelAjusteDetalle(idAjusteProducto) == false) {
				mAjusteDetalleDocumento.imprimirAjusteDocumento(idAjusteProducto);
				// listaDetalleAjusteDocumento
				// =mAjusteDetalleDocumento.findAllAjusteDetalleDocumento();
				listaDetalleAjusteProducto = new ArrayList<InvDetalleAjusteProducto>();
				//listaAjusteProducto  = mInventarioProducto.findAjusteById(idAjusteProducto);				
				listaDetalleAjusteProducto = mAjusteProd.findAllDetalleById(idAjusteProducto);
				JSFUtil.crearMensajeINFO("El documento fue impreso corretamente, ya no podrá agregar más detalles.");
				return "ajustes_productosImpre?faces-redirect=true";
			} else {
				JSFUtil.crearMensajeERROR("El documento ya fue impresoo!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}


	public String detalleDelAjusteImpresionFin(Integer idAjusteProducto) {
				// listaDetalleAjusteDocumento
				// =mAjusteDetalleDocumento.findAllAjusteDetalleDocumento();
				listaDetalleAjusteProducto2 = new ArrayList<InvDetalleAjusteProducto>();
				//listaAjusteProducto  = mInventarioProducto.findAjusteById(idAjusteProducto);				
				listaDetalleAjusteProducto2 = mAjusteProd.findAllDetalleById(idAjusteProducto);
				JSFUtil.crearMensajeINFO("El documento fue impreso corretamente, ya no podrá agregar más detalles.");
				return "ajustes_productosImpre?faces-redirect=true";		
	}
	
	
	public List<InvDetalleAjusteProducto> getListaDetalleAjusteProducto2() {
		return listaDetalleAjusteProducto2;
	}

	public void setListaDetalleAjusteProducto2(List<InvDetalleAjusteProducto> listaDetalleAjusteProducto2) {
		this.listaDetalleAjusteProducto2 = listaDetalleAjusteProducto2;
	}

	public void imprimirAjusteDocumento(Integer idAjusteProducto) {
		try {
			// mAjusteDetalleDocumento.imprimirAjusteDocumento(idAjusteProducto);
			// ajustes_productosImpre
			// listaDetalleAjusteDocumento =
			// mAjusteDetalleDocumento.findAllAjusteDetalleDocumento();

			JSFUtil.crearMensajeINFO("El documento fue impreso corretamente, ya no podrá agreagar más detalles.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("El documento no pudo ser impreso correcamente");
		}
	}

	public InvDetalleAjusteDocumento getNuevoDetalleAjusteDocumento() {
		return nuevoDetalleAjusteDocumento;
	}

	public void setNuevoDetalleAjusteDocumento(InvDetalleAjusteDocumento nuevoDetalleAjusteDocumento) {
		this.nuevoDetalleAjusteDocumento = nuevoDetalleAjusteDocumento;
	}

	public List<InvDetalleAjusteDocumento> getListaDetalleAjusteDocumento() {
		return listaDetalleAjusteDocumento;
	}

	public void setListaDetalleAjusteDocumento(List<InvDetalleAjusteDocumento> listaDetalleAjusteDocumento) {
		this.listaDetalleAjusteDocumento = listaDetalleAjusteDocumento;
	}

	public List<InvDetalleAjusteProducto> getListaDetalleAjusteProducto() {
		return listaDetalleAjusteProducto;
	}

	public void setListaDetalleAjusteProducto(List<InvDetalleAjusteProducto> listaDetalleAjusteProducto) {
		this.listaDetalleAjusteProducto = listaDetalleAjusteProducto;
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

	public InvAjusteProducto getAjusteProductoSeleccionado() {
		return ajusteProductoSeleccionado;
	}

	public void setAjusteProductoSeleccionado(InvAjusteProducto ajusteProductoSeleccionado) {
		this.ajusteProductoSeleccionado = ajusteProductoSeleccionado;
	}

	public List<InvAjusteProducto> getListaAjusteProducto() {
		return listaAjusteProducto;
	}

	public void setListaAjusteProducto(List<InvAjusteProducto> listaAjusteProducto) {
		this.listaAjusteProducto = listaAjusteProducto;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<InvDetalleAjusteDocumento> getListaReportesDocumento() {
		return listaReportesDocumento;
	}

	public void setListaReportesDocumento(List<InvDetalleAjusteDocumento> listaReportesDocumento) {
		this.listaReportesDocumento = listaReportesDocumento;
	}

}
