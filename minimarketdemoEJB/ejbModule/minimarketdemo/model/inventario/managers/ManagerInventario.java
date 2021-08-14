package minimarketdemo.model.inventario.managers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.entities.InvAjusteProducto;
import minimarketdemo.model.core.entities.InvCategoriaProducto;
import minimarketdemo.model.core.entities.InvDetalleAjusteProducto;
import minimarketdemo.model.core.entities.InvProducto;
import minimarketdemo.model.core.entities.InvProductoAPI;

import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.productos.dtos.DTODetalleAjusteProductos;
import minimarketdemo.model.thumano.dtos.DTOThmCargo;

/**
 * Session Bean implementation class ManagerInventario
 */
@Stateless
@LocalBean
public class ManagerInventario {

	/// El valor puede ser variable bajo cualquier circunstancia, si graba o no IVA.
	private BigDecimal ivaManager = new BigDecimal(0.12).setScale(2,RoundingMode.HALF_UP);

	/**
	 * Default constructor.
	 */
	@EJB
	private ManagerDAO mDAO;

	@PersistenceContext
	private EntityManager em;

	public ManagerInventario() {
		// TODO Auto-generated constructor stub
	}

	//// Metodos de la clase MInventario
	public void eliminarInventarioProducto(Integer idProducto) throws Exception {

		// Buscar clave primaria
		InvProducto i = em.find(InvProducto.class, idProducto);
		// if (i == null)
		// throw new Exception("No se puede eliminar el producto con el id" +
		// idProducto);
		// no se puede eliminar mientras el stock 2 manzanas
		if (i.getStock() > 0)
			throw new Exception("No se puede eliminar porque aun tiene stock.");

		em.remove(i);
	}

	public void crearInventarioProducto(InvProducto invenProducto, Integer idCategoriaProducto, String codigoProducto,
			String nombreProducto, String descripcionProducto, Boolean ivaProducto, BigDecimal costroProducto,
			BigDecimal pvpProducto, Boolean estadoProducto, Integer stockProducto) throws Exception {
		System.out.println("--------------------> " + codigoProducto);
		InvCategoriaProducto ic = em.find(InvCategoriaProducto.class, idCategoriaProducto);
		try {
			invenProducto.setCodigoProducto(codigoProducto);
			invenProducto.setInvCategoriaProducto(ic);
			invenProducto.setNombreProducto(nombreProducto);
			invenProducto.setDescripcion(descripcionProducto);
			if (ivaProducto == true) {
				invenProducto.setIva(ivaManager);
			} else {
				invenProducto.setIva(ivaManager.valueOf(0));
			}
			invenProducto.setCosto(costroProducto);
			invenProducto.setPvp(pvpProducto);
			invenProducto.setEstado(estadoProducto);
			invenProducto.setStock(stockProducto);

			em.persist(invenProducto);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void actualizarProducto(InvProducto invenProducto) throws Exception {
		InvProducto ipr = em.find(InvProducto.class, invenProducto.getIdProducto());
		if (ipr == null)
			throw new Exception("No existe su producto");
		System.out.println("EJBBB  " + invenProducto.getCosto());
		ipr.setCodigoProducto(invenProducto.getCodigoProducto());
		ipr.setNombreProducto(invenProducto.getNombreProducto());
		ipr.setDescripcion(invenProducto.getDescripcion());
		ipr.setIva(invenProducto.getIva());
		ipr.setCosto(invenProducto.getCosto());
		ipr.setPvp(invenProducto.getPvp());
		ipr.setEstado(invenProducto.getEstado());
		ipr.setStock(invenProducto.getStock());
		em.merge(ipr);
	}

	public String activarDesactivarProducto(InvProducto invenProducto) throws Exception {
		InvProducto producto = (InvProducto) mDAO.findById(InvProducto.class, invenProducto.getIdProducto());
		if (producto == null)
			throw new Exception("No existe el producto.");
		if (invenProducto.getEstado() == true) {
			producto.setEstado(!producto.getEstado());
			System.out.println("activar/desactivar " + producto.getEstado());
			mDAO.actualizar(producto);
			return "El producto fue desactivado";
		} else {
			producto.setEstado(!producto.getEstado());
			System.out.println("activar/desactivar " + producto.getEstado());
			mDAO.actualizar(producto);
			return "El producto fue activado";
		}
	}

	/// Metodos para implementar los ajustes de los productos

	// METODOS PARA EL MANEJO DE ARTICULOS
	/**
	 * Finder para consultar todos los ajustes de un producto
	 * 
	 * @param id.del Producto
	 * @return Lista de articulos.
	 */

	public List<InvAjusteProducto> findAllAjustesProductos() {
		return em.createNamedQuery("InvAjusteProducto.findAll", InvAjusteProducto.class).getResultList();
	}

	public List<InvAjusteProducto> findArticulosById(Integer idProducto) {
		Query q = em.createQuery("select i from InvAjusteProducto i where i.invProducto.idProducto=:idProducto",
				InvAjusteProducto.class);
		q.setParameter("idProducto", idProducto);
		return q.getResultList();
	}

	public List<String> findTipoAjusteProducto() {
		List<String> lista = new ArrayList<String>();
		lista.add("Positivo");
		lista.add("Negativo");
		return lista;
	}

	public void crearInventarioAjuste(InvAjusteProducto nuevoAjusteProducto, String motivo, String tipoAjuste,
			String estadoAjuste) throws Exception {
		if (em.contains(nuevoAjusteProducto))
			throw new Exception("El ajuste indicado ya existe en la base de datos");
		// InvProducto a = em.find(InvProducto.class, idProducto);
		// asignamos las clases foraneas (objetos relacionados)
		List<InvAjusteProducto> listaSetear = findAllAjustesProductos();

		nuevoAjusteProducto.setMotivo(motivo);
		nuevoAjusteProducto.setTipoAjuste(tipoAjuste);
		Timestamp t = new Timestamp(new Date().getTime());
		nuevoAjusteProducto.setFecha(t);
		nuevoAjusteProducto.setNumeroAjuste("AJUS-" + estadoAjuste + "-" + (listaSetear.size() + 1));
		nuevoAjusteProducto.setImpreso(false);
		em.persist(nuevoAjusteProducto);
	}

	public void actualizarAjusteProducto(InvAjusteProducto ajusteEdit) {
		// buscamos el articulo final
		InvAjusteProducto a = em.find(InvAjusteProducto.class, ajusteEdit.getIdAjuste());
		// objetos relacionados
		// actualizamos los campos permitidos
		a.setMotivo(ajusteEdit.getMotivo());

		a.setTipoAjuste(ajusteEdit.getTipoAjuste());
		em.merge(a);
	}

	//////////////////////// DetalleAjusteProducto
	/////////////////////

	public List<DTODetalleAjusteProductos> listaReportePorAjuste(Integer id_ajuste) {
		try {
			String query = "SELECT a,b,c FROM "
					+ "		inv_ajuste_producto a	INNER JOIN inv_detalle_ajuste_producto b "
					+ "		ON a.id_ajuste = b.id_ajuste INNER JOIN inv_producto c "
					+ "		ON b.id_producto = c.id_producto and b.id_ajuste = 78 ";
			// Create
			Query q = em.createNativeQuery(query);
			/*
			 * List<Tuple> tuples = q.getResultList(); return tuples.stream().map(t -> new
			 * DTODetalleAjusteProductos(t.get("motivo", String.class), t.get("tipoAjuste",
			 * String.class), t.get("fecha", Date.class), t.get("nombre", String.class),
			 * t.get("descripcion", String.class), t.get("stock",
			 * Integer.class))).collect(Collectors.toList());
			 */
			for (int i = 0; i < q.getResultList().size(); i++) {
				System.out.println("Hola ->>>>>>>>>" + q.getResultList().get(i).toString());
			}
			return q.getResultList();
		} catch (Exception e) {
			System.out.println("No regresa nada " + e.getMessage());
			return null;

		}

	}

	public List<InvDetalleAjusteProducto> findAllDetalleAjusteProducto() {
		return em.createNamedQuery("InvDetalleAjusteProducto.findAll", InvDetalleAjusteProducto.class).getResultList();
	}

	public List<InvAjusteProducto> findAjusteById(Integer idAjuste) {
		Query q = em.createQuery("select i from InvAjusteProducto i where i.idAjuste=:idAjuste",
				InvAjusteProducto.class);
		q.setParameter("idAjuste", idAjuste);
		return q.getResultList();
	}

	public InvCategoriaProducto findByIdThmCargo(Integer idThmCargo) throws Exception {
		return (InvCategoriaProducto) mDAO.findById(InvCategoriaProducto.class, idThmCargo);
	}

	public void actualizarProductoAjusteDetalle(InvDetalleAjusteProducto detipr, Integer invenAjusteProducto,
			Integer invenProducto, Integer stockNuevo, String descripcion) throws Exception {
		InvProducto ipr = em.find(InvProducto.class, invenProducto);
		InvAjusteProducto apr = em.find(InvAjusteProducto.class, invenAjusteProducto);
		if (em.contains(detipr))
			throw new Exception("El ajuste indicado ya existe en la base de datos");
		System.out.println("cantidad actual manager:" + stockNuevo);
		/// producto actualiza
		ipr.setStock(stockNuevo);
		em.merge(ipr);
		// crear detalle
		detipr.setInvAjusteProducto(apr);
		detipr.setInvProducto(ipr);
		detipr.setDescripcion(descripcion);
		em.persist(detipr);
	}

	////// Metodos de el API rest

	public List<InvProducto> findAllProducto() {
		return em.createNamedQuery("InvProducto.findAll", InvProducto.class).getResultList();
	}

	public List<InvProducto> findAllProductoApi() {
		Query q = mDAO.getEntityManager().createNamedQuery("InvProducto.findAll", InvProducto.class);
		return q.getResultList();
	}

	public List<InvProductoAPI> findAllProductoApi2() {
		Query q = mDAO.getEntityManager().createNamedQuery("InvProductoAPI.findAll", InvProductoAPI.class);
		return q.getResultList();
	}

}
