package minimarketdemo.model.inventario.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import minimarketdemo.model.core.entities.InvCategoriaProducto;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCatInventario
 */
@Stateless
@LocalBean
public class ManagerCatInventario {

	@EJB
	private ManagerDAO mDAO;
	/**
	 * Default constructor.
	 */
	@PersistenceContext
	private EntityManager em;
	private List<String> var;

	public ManagerCatInventario() {
		// TODO Auto-generated constructor stub
	}

	public List<InvCategoriaProducto> findAllCategoriaProducto() {
		return mDAO.findAll(InvCategoriaProducto.class);
	}
 
	
	public List<InvCategoriaProducto> findByAtri(String codigoProd){
		Query q = em.createQuery("select i from InvCategoriaProducto i where i.codigoProd=:codigoProd");
	    q.setParameter("codigoProd", codigoProd);
	    return q.getResultList();
	}
	public void crearCategoria(InvCategoriaProducto categoria, String nombre, String descripcion, String codigoProd)
			throws Exception {
		InvCategoriaProducto c = new InvCategoriaProducto();
		c.setNombreCategoria(nombre);
		c.setDescripcionCategoria(descripcion);
		if (findByAtri(codigoProd) != null)
			throw new Exception("El código ya existe, intentelo de nuevo con un dato diferente.");
		c.setCodigoProd(codigoProd);
		mDAO.insertar(c);
	}

	public void eliminarCategoria(Integer idCategoria) throws Exception {
		mDAO.eliminar(InvCategoriaProducto.class, idCategoria);
	}

	public void actualizarCategoria(InvCategoriaProducto categoria) throws Exception {
		InvCategoriaProducto c = (InvCategoriaProducto) mDAO.findById(InvCategoriaProducto.class,
				categoria.getIdCategoriaProducto());
		if (c == null)
			throw new Exception("No existe la categoría");
		c.setNombreCategoria(categoria.getNombreCategoria());
		c.setDescripcionCategoria(categoria.getDescripcionCategoria());

		c.setCodigoProd(categoria.getCodigoProd());
		mDAO.actualizar(c);
		;
	}

}
