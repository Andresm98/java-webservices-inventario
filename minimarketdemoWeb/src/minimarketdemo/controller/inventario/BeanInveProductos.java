package minimarketdemo.controller.inventario;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvAjusteProducto;
import minimarketdemo.model.core.entities.InvCategoriaProducto;
import minimarketdemo.model.core.entities.InvDetalleAjusteProducto;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.inventario.managers.ManagerInventario;
import minimarketdemo.model.productos.dtos.DTODetalleAjusteProductos;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

@Named
@SessionScoped
public class BeanInveProductos implements Serializable {

	@EJB
	private ManagerInventario mInventario;

	private InvProducto nuevoProducto;
	private InvProducto edicionProducto;
	private InvAjusteProducto nuevoAjusteProducto;
	private InvAjusteProducto edicionAjusteProducto;
	private InvDetalleAjusteProducto nuevoDetalleAjusteProducto;
	private InvDetalleAjusteProducto edicionDetalleAjusteProducto;
	private DTODetalleAjusteProductos dtoObListaDetalleAjuste;

	public DTODetalleAjusteProductos getDtoObListaDetalleAjuste() {
		return dtoObListaDetalleAjuste;
	}

	public void setDtoObListaDetalleAjuste(DTODetalleAjusteProductos dtoObListaDetalleAjuste) {
		this.dtoObListaDetalleAjuste = dtoObListaDetalleAjuste;
	}

	private List<InvProducto> listaProductos;
	private List<InvAjusteProducto> listaAjusteProducto;
	private List<InvDetalleAjusteProducto> listaDetalleAjusteProducto;
	private List<DTODetalleAjusteProductos> dtoListaDetalleAjustes;

	private Integer idCategoriaProducto;
	private String codigoProducto;
	private String nombreProducto;
	private String descripcionProducto;
	private Boolean ivaProducto;
	private BigDecimal costroProducto;
	private BigDecimal pvpProducto;
	private Boolean estadoProducto;
	private Integer stockProducto;
	private Integer stockActualizadoProducto;
	///
	private Integer idInvAjusteProducto;
	private Integer idInvProducto;
	private boolean estadoDelAjuste;

	public Integer getIdInvAjusteProducto() {
		return idInvAjusteProducto;
	}

	public void setIdInvAjusteProducto(Integer idInvAjusteProducto) {
		this.idInvAjusteProducto = idInvAjusteProducto;
	}

	public Integer getIdInvProducto() {
		return idInvProducto;
	}

	public void setIdInvProducto(Integer idInvProducto) {
		this.idInvProducto = idInvProducto;
	}

	private InvProducto productoSeleccionado;
	private InvAjusteProducto ajusteSelecciondo;
	private Integer idAjusteProducto;
	private List<String> listaTipoAjuste;
	// Ajuste de Productos
	private String motivo;
	private String tipoAjuste;
	///

	@PostConstruct
	public void cargar() {
		estadoInicial = false;
		listaProductos = mInventario.findAllProducto();
		listaAjusteProducto = mInventario.findAllAjustesProductos();
		listaDetalleAjusteProducto = mInventario.findAllDetalleAjusteProducto();
		nuevoProducto = new InvProducto();
		edicionProducto = new InvProducto();
		nuevoAjusteProducto = new InvAjusteProducto();
		edicionAjusteProducto = new InvAjusteProducto();
		nuevoDetalleAjusteProducto = new InvDetalleAjusteProducto();
		edicionDetalleAjusteProducto = new InvDetalleAjusteProducto();
		listaTipoAjuste = new ArrayList<String>();
		System.out.println("cargooooo");
	}

	/// metodo para
	public String reiniciarData() {
		listaAjusteProducto = mInventario.findAllAjustesProductos();
		listaDetalleAjusteProducto = mInventario.findAllDetalleAjusteProducto();
		nuevoAjusteProducto = new InvAjusteProducto();
		edicionAjusteProducto = new InvAjusteProducto();
		nuevoDetalleAjusteProducto = new InvDetalleAjusteProducto();
		edicionDetalleAjusteProducto = new InvDetalleAjusteProducto();
		return "ajustes_productosL?faces-redirect=true";
	}

	public void actionListenerCrearProducto() {
		try {
			mInventario.crearInventarioProducto(nuevoProducto, idCategoriaProducto, codigoProducto, nombreProducto,
					descripcionProducto, ivaProducto, costroProducto, pvpProducto, estadoProducto, stockProducto);
			listaProductos = mInventario.findAllProducto();
			JSFUtil.crearMensajeINFO("Producto creado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarProducto(Integer idProducto) {
		try {
			System.out.println("BeanEliminar");
			mInventario.eliminarInventarioProducto(idProducto);
			listaProductos = mInventario.findAllProducto();
			JSFUtil.crearMensajeINFO("El producto se ha eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerSeleccionarProducto(InvProducto ipr) {
		edicionProducto = ipr;
		System.out.println("producto seleecionado...." + edicionProducto.getNombreProducto());
	}

	public void actionListenerActualizarProducto(InvProducto edicionProducto) {

		try {
			mInventario.actualizarProducto(edicionProducto);
			listaProductos = mInventario.findAllProducto();
			JSFUtil.crearMensajeINFO("Producto actualizado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerActivarDesactivar(InvProducto activarDesactivarProducto) {
		try {
			mInventario.activarDesactivarProducto(activarDesactivarProducto);
			listaProductos = mInventario.findAllProducto();
			JSFUtil.crearMensajeINFO("" + mInventario.activarDesactivarProducto(activarDesactivarProducto));
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("El producto no pudo ser activado/desactivado.");
		}
	}

	public void actionListenerActualizarAjusteProducto(Integer actual) throws Exception {

		try {
			System.out.println("Estado del Ajuste Detalle en el actualizar producto." + estadoDelAjuste);
			if (estadoDelAjuste == true) {
				Integer anterior = edicionProducto.getStock();
				Integer nuevo = anterior + actual;
				idInvAjusteProducto = ajusteSelecciondo.getIdAjuste();
				idInvProducto = edicionProducto.getIdProducto();
				System.out.println("valor actual: " + nuevo);
				mInventario.actualizarProductoAjusteDetalle(nuevoDetalleAjusteProducto, idInvAjusteProducto,
						idInvProducto, nuevo, "El stock del producto " + edicionProducto.getNombreProducto() + " fue actualizado de "+anterior+" a "+nuevo );
				// listaReportePorAjuste =
				// mInventario.listaReportePorAjuste(this.ajusteSelecciondo.getIdAjuste());

				/*
				 * for (int i = 0; i < listaReportePorAjuste.size(); i++) {
				 * System.out.println("Hola ->>>>>>>>>" + listaReportePorAjuste.toArray()); }
				 */
				nuevoDetalleAjusteProducto = new InvDetalleAjusteProducto();
				JSFUtil.crearMensajeINFO("Ajuste realizado, éxito.");
				actual = 0;

			} else {
				if (edicionProducto.getStock() > 0 & edicionProducto.getStock() >= actual) {
					Integer anterior = edicionProducto.getStock();
					Integer nuevo = anterior - actual;
					idInvAjusteProducto = ajusteSelecciondo.getIdAjuste();
					idInvProducto = edicionProducto.getIdProducto();
					System.out.println("valor actual: " + nuevo);
					mInventario.actualizarProductoAjusteDetalle(nuevoDetalleAjusteProducto, idInvAjusteProducto,
							idInvProducto, nuevo,"El stock del producto " + edicionProducto.getNombreProducto() + " fue actualizado de "+anterior+" a "+nuevo );
					// listaReportePorAjuste =
					// mInventario.listaReportePorAjuste(this.ajusteSelecciondo.getIdAjuste());

					/*
					 * System.out.println("-------> "+this.ajusteSelecciondo.getIdAjuste());
					 * //listaReportePorAjuste =
					 * mInventario.listaReportePorAjuste(this.ajusteSelecciondo.getIdAjuste());
					 * 
					 * for (int i = 0; i < listaReportePorAjuste.size(); i++) {
					 * System.out.println("Hola ->>>>>>>>>" +listaDetalleAjusteProducto.toArray());
					 * }
					 */
					nuevoDetalleAjusteProducto = new InvDetalleAjusteProducto();
					JSFUtil.crearMensajeINFO("Ajuste realizado, éxito.");
					actual = 0;
				} else {
					JSFUtil.crearMensajeWARN("No se pudo realizar el ajuste no hay stock disponible.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionListenerSeleccionarAjusteProducto(InvAjusteProducto ajuspr) {
		edicionAjusteProducto = ajuspr;
		mInventario.actualizarAjusteProducto(edicionAjusteProducto);
		System.out.println("ajuste pruducto...." + edicionAjusteProducto.getMotivo());
	}
	////////////////////////////////////////////
	//// Gestion de los Ajustes de los Productos
	////////////////////////////////////////////

	public String actionSeleccionarAjusteProductoByProducto(InvProducto producto) {
		System.out.println("SI LLLEGO AQUIIII ");
		listaAjusteProducto = mInventario.findArticulosById(producto.getIdProducto());
		listaTipoAjuste = mInventario.findTipoAjusteProducto();
		productoSeleccionado = producto;
		///// llamar al ajuste del producto
		return "ajustes_edicion?faces-redirect=true";
	}

	public void actionListenerCrearAjuste() {
		try {
			ajusteSelecciondo = new InvAjusteProducto();
			mInventario.crearInventarioAjuste(nuevoAjusteProducto, nuevoAjusteProducto.getMotivo(),
					nuevoAjusteProducto.getTipoAjuste(), nuevoAjusteProducto.getTipoAjuste());
			System.out.println("listener crear ajuste   " + nuevoAjusteProducto.getMotivo());
			JSFUtil.crearMensajeINFO("Ajuste creado.");
			listaAjusteProducto = mInventario.findAjusteById(nuevoAjusteProducto.getIdAjuste());
			// setear el objeto
			ajusteSelecciondo = nuevoAjusteProducto;
			System.out.println("Estado impreso: " + nuevoAjusteProducto.getTipoAjuste());
			if (nuevoAjusteProducto.getTipoAjuste().equals("N")) {
				System.out.println("Nooooo");
				estadoDelAjuste = false;
			} else {
				estadoDelAjuste = true;
			}
			System.out.println("Estado del Ajuste: " + estadoDelAjuste);
			nuevoAjusteProducto = new InvAjusteProducto();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	private boolean estadoInicial = false;

	public boolean actionListenerForm() {
		System.out.println("Variable nueva del formulario es: " + estadoInicial);
		return estadoInicial = true;
	}

	public boolean isEstadoInicial() {

		return estadoInicial;
	}

	public void setEstadoInicial(boolean estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public String actionAbrirTodosAjustes() {
		listaAjusteProducto = mInventario.findAllAjustesProductos();
		///// llamar al ajuste del producto
		return "ajustes_productosImpre?faces-redirect=true";
	}

	public Integer getStockActualizadoProducto() {
		return stockActualizadoProducto;
	}

	public void setStockActualizadoProducto(Integer stockActualizadoProducto) {
		this.stockActualizadoProducto = stockActualizadoProducto;
	}

	public void actionListenerSeleccionarArticulo(InvAjusteProducto ajuspr) {
		edicionAjusteProducto = ajuspr;
	}

	public InvAjusteProducto getEdicionAjusteProducto() {
		return edicionAjusteProducto;
	}

	public void setEdicionAjusteProducto(InvAjusteProducto edicionAjusteProducto) {
		this.edicionAjusteProducto = edicionAjusteProducto;
	}

	public List<InvAjusteProducto> getListaAjusteProducto() {
		return listaAjusteProducto;
	}

	public void setListaAjusteProducto(List<InvAjusteProducto> listaAjusteProducto) {
		this.listaAjusteProducto = listaAjusteProducto;
	}

	public List<InvProducto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<InvProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public InvProducto getNuevoProducto() {
		return nuevoProducto;
	}

	public void setNuevoProducto(InvProducto nuevoProducto) {
		this.nuevoProducto = nuevoProducto;
	}

	public Integer getIdCategoriaProducto() {
		return idCategoriaProducto;
	}

	public void setIdCategoriaProducto(Integer idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public Boolean getIvaProducto() {
		return ivaProducto;
	}

	public void setIvaProducto(Boolean ivaProducto) {
		this.ivaProducto = ivaProducto;
	}

	public BigDecimal getCostroProducto() {
		return costroProducto;
	}

	public void setCostroProducto(BigDecimal costroProducto) {
		this.costroProducto = costroProducto;
	}

	public BigDecimal getPvpProducto() {
		return pvpProducto;
	}

	public void setPvpProducto(BigDecimal pvpProducto) {
		this.pvpProducto = pvpProducto;
	}

	public Boolean getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(Boolean estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public Integer getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(Integer stockProducto) {
		this.stockProducto = stockProducto;
	}

	public InvProducto getEdicionProducto() {
		return edicionProducto;
	}

	public void setEdicionProducto(InvProducto edicionProducto) {
		this.edicionProducto = edicionProducto;
	}

	public InvProducto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(InvProducto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
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

	public InvAjusteProducto getNuevoAjusteProducto() {
		return nuevoAjusteProducto;
	}

	public void setNuevoAjusteProducto(InvAjusteProducto nuevoAjusteProducto) {
		this.nuevoAjusteProducto = nuevoAjusteProducto;
	}

	public Integer getIdAjusteProducto() {
		return idAjusteProducto;
	}

	public void setIdAjusteProducto(Integer idAjusteProducto) {
		this.idAjusteProducto = idAjusteProducto;
	}

	public List<String> getListaTipoAjuste() {
		return listaTipoAjuste;
	}

	public void setListaTipoAjuste(List<String> listaTipoAjuste) {
		this.listaTipoAjuste = listaTipoAjuste;
	}

	public InvDetalleAjusteProducto getNuevoDetalleAjusteProducto() {
		return nuevoDetalleAjusteProducto;
	}

	public void setNuevoDetalleAjusteProducto(InvDetalleAjusteProducto nuevoDetalleAjusteProducto) {
		this.nuevoDetalleAjusteProducto = nuevoDetalleAjusteProducto;
	}

	public InvDetalleAjusteProducto getEdicionDetalleAjusteProducto() {
		return edicionDetalleAjusteProducto;
	}

	public void setEdicionDetalleAjusteProducto(InvDetalleAjusteProducto edicionDetalleAjusteProducto) {
		this.edicionDetalleAjusteProducto = edicionDetalleAjusteProducto;
	}

	public List<InvDetalleAjusteProducto> getListaDetalleAjusteProducto() {
		return listaDetalleAjusteProducto;
	}

	public void setListaDetalleAjusteProducto(List<InvDetalleAjusteProducto> listaDetalleAjusteProducto) {
		this.listaDetalleAjusteProducto = listaDetalleAjusteProducto;
	}

	public InvAjusteProducto getAjusteSelecciondo() {
		return ajusteSelecciondo;
	}

	public void setAjusteSelecciondo(InvAjusteProducto ajusteSelecciondo) {
		this.ajusteSelecciondo = ajusteSelecciondo;
	}

	public boolean isEstadoDelAjuste() {
		return estadoDelAjuste;
	}

	public void setEstadoDelAjuste(boolean estadoDelAjuste) {
		this.estadoDelAjuste = estadoDelAjuste;
	}

	/// Geters and seters metodos ajuste detalle producto

	private List<InvProducto> listaUnCategoria;

	public List<InvProducto> getListaUnCategoria() {
		return listaUnCategoria;
	}

	public void setListaUnCategoria(List<InvProducto> listaUnCategoria) {
		this.listaUnCategoria = listaUnCategoria;
	}

	public List<DTODetalleAjusteProductos> getDtoListaDetalleAjustes() {
		return dtoListaDetalleAjustes;
	}

	public void setDtoListaDetalleAjustes(List<DTODetalleAjusteProductos> dtoListaDetalleAjustes) {
		this.dtoListaDetalleAjustes = dtoListaDetalleAjustes;
	}

	private List<String> listaReportePorAjuste;

	public List<String> getListaReportePorAjuste() {
		return listaReportePorAjuste;
	}

	public void setListaReportePorAjuste(List<String> listaReportePorAjuste) {
		this.listaReportePorAjuste = listaReportePorAjuste;
	}

	public BeanInveProductos() {
		// TODO Auto-generated constructor stub
	}

}
