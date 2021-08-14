package minimarketdemo.model.producto.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public class DAOInventario {

	/// El valor puede ser variable bajo cualquier circunstancia, si graba o no IVA.
	private BigDecimal ivaManager = new BigDecimal(0.12);

	/**
	 * Default constructor.
	 */
	@EJB
	private ManagerDAO mDAO;

	@PersistenceContext
	private EntityManager em;
	
	//////Metodos de el API rest

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
	
	public InvProductoAPI findByIdApi(Integer id) {
		return em.find(InvProductoAPI.class, id);
	}

	public void update(InvProductoAPI todo) {
		em.merge(todo);
	}

	public void create(InvProductoAPI todo) {
		em.persist(todo);
	}

	public void delete(InvProductoAPI todo) {
		if (!em.contains(todo)) {
			todo = em.merge(todo);
		}
		em.remove(todo);
	}
	
	
	
}
