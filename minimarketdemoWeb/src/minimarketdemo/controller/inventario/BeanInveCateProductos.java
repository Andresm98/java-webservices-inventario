package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvCategoriaProducto;
import minimarketdemo.model.inventario.managers.ManagerCatInventario;

@Named
@SessionScoped
public class BeanInveCateProductos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerCatInventario mCatInventario;
	private List<InvCategoriaProducto> listaCategoriaProductos;
	
	private InvCategoriaProducto nuevaCategoria;
	private InvCategoriaProducto edicionCategoria;
	
	private String nombre;
	private String descripcion;
	private String codigoCategoria;
	
	public BeanInveCateProductos() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void inicializar() {
		listaCategoriaProductos= mCatInventario.findAllCategoriaProducto();
		nuevaCategoria = new InvCategoriaProducto();
		edicionCategoria = new InvCategoriaProducto();
	}
	
	public void actionListenerSeleccionarCategoria(InvCategoriaProducto c) {
		edicionCategoria = c;
	}
	
	public void actionListenerCrearCategoria() {
		try {
			mCatInventario.crearCategoria(nuevaCategoria, nombre, descripcion, codigoCategoria);
			listaCategoriaProductos = mCatInventario.findAllCategoriaProducto();
			nuevaCategoria = new InvCategoriaProducto();
			JSFUtil.crearMensajeINFO("Categoría creada correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarCategoria(Integer idCategoria) {
		try {
			mCatInventario.eliminarCategoria(idCategoria);
			listaCategoriaProductos = mCatInventario.findAllCategoriaProducto();
			JSFUtil.crearMensajeINFO("La categoría se ha eliminado correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerActualizarCategoria(InvCategoriaProducto categoria) {
		try {
			mCatInventario.actualizarCategoria(categoria);
			listaCategoriaProductos = mCatInventario.findAllCategoriaProducto();
			
			JSFUtil.crearMensajeINFO("Categoría actualizada correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<InvCategoriaProducto> getListaCategoriaProductos() {
		return listaCategoriaProductos;
	}

	public void setListaCategoriaProductos(List<InvCategoriaProducto> listaCategoriaProductos) {
		this.listaCategoriaProductos = listaCategoriaProductos;
	}
	public InvCategoriaProducto getNuevaCategoria() {
		return nuevaCategoria;
	}
	public void setNuevaCategoria(InvCategoriaProducto nuevaCategoria) {
		this.nuevaCategoria = nuevaCategoria;
	}
	public InvCategoriaProducto getEdicionCategoria() {
		return edicionCategoria;
	}
	public void setEdicionCategoria(InvCategoriaProducto edicionCategoria) {
		this.edicionCategoria = edicionCategoria;
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
	public String getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	
	
}
