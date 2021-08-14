package minimarketdemo.model.inventario.managers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.entities.InvAjusteProducto;
import minimarketdemo.model.core.entities.InvDetalleAjusteDocumento;
import minimarketdemo.model.core.entities.InvDetalleAjusteProducto;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAjusteDetalleProd
 */
@Stateless
@LocalBean
public class ManagerAjusteDetalleProd {
	@EJB
	private ManagerDAO mDAO;
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerAjusteDetalleProd() {
		// TODO Auto-generated constructor stub
	}

	//// Operaciones Inventario Detalle Ajuste

	public List<InvDetalleAjusteProducto> findAllDetalle() {
		return em.createNamedQuery("InvDetalleAjusteProducto.findAll", InvDetalleAjusteProducto.class).getResultList();
	}

	public List<InvDetalleAjusteProducto> findAllDetalleById(Integer idAjuste) {
		Query q = em.createQuery(
				"select i from InvDetalleAjusteProducto i where i.invAjusteProducto.idAjuste=:idAjuste");
		q.setParameter("idAjuste", idAjuste);
		for (int i = 0; i < q.getResultList().size(); i++) {
			System.out.println("---> datos " + q.getResultList().get(i));
		}
		return q.getResultList();
	}

	//// Operaciones Inventario Ajustes

	public List<InvAjusteProducto> findAllAjustes() {
		return em.createNamedQuery("InvAjusteProducto.findAll", InvAjusteProducto.class).getResultList();
	}

	public List<InvAjusteProducto> findAllAjustesById(Integer idAjuste) {
		Query q = em.createQuery("select i from InvAjusteProducto i where i.idAjuste=:idAjuste");
		q.setParameter("idAjuste", idAjuste);
		return q.getResultList();
	}

	//////////////////////////////////////
	///// Metodos para la tabla Ajuste Detalle Documento
	//////////////////////////////////////////

	public List<InvDetalleAjusteDocumento> findAllAjusteDetalleDocumento() {
		return em.createNamedQuery("InvDetalleAjusteDocumento.findAll", InvDetalleAjusteDocumento.class)
				.getResultList();
	}

	public List<InvDetalleAjusteDocumento> findAllDetalleAjusteDocumentoById(Integer idDetalleAjusteDocumento) {
		Query q = em.createQuery(
				"select i from InvDetalleAjusteDocumento i where i.idDetalleAjusteDocumento=:idDetalleAjusteDocumento");
		q.setParameter("idDetalleAjusteDocumento", idDetalleAjusteDocumento);
		return q.getResultList();
	}
	/// operacion para imprimir el documento necesario

	public Boolean regresarEstadoDelAjusteDetalle(Integer idAjusteProducto) {
		try {
			InvAjusteProducto ipr = em.find(InvAjusteProducto.class, idAjusteProducto);
			if (ipr.getImpreso() == false)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String imprimirAjusteDocumento(Integer idAjusteProducto) throws Exception {
		try {
			InvAjusteProducto ipr = em.find(InvAjusteProducto.class, idAjusteProducto);
			InvDetalleAjusteDocumento iprdoc = new InvDetalleAjusteDocumento();
			if (ipr.getImpreso() == false) {
				//// crear la fila del documento del ajuste
				SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Timestamp tiempo=new Timestamp(System.currentTimeMillis());
				iprdoc.setFechaImpresion(tiempo);
				iprdoc.setInvAjusteProducto(ipr);
				/// actualizar el estado de la impresion
				ipr.setImpreso(true);
				// crear el objeto en la base de datos
				em.persist(iprdoc);
				// actualizar el objeto en la bases de datos
				em.merge(ipr);
				return "El documento fue impreso con éxito";
			} else {
				return "El documento ya fue impreso";
			}
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
	

    public List<InvDetalleAjusteDocumento> findDocumentosByFecha(Date fechaInicio,Date fechaFin){
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	System.out.println("fecha inicio: "+format.format(fechaInicio));
    	System.out.println("fecha fin: "+format.format(fechaFin));
    	String consulta="select b from InvDetalleAjusteDocumento b where b.fechaImpresion between :fechaInicio and :fechaFin order by b.fechaImpresion";
    	Query q=mDAO.getEntityManager().createQuery(consulta, InvDetalleAjusteDocumento.class);
    	q.setParameter("fechaInicio", new Timestamp(fechaInicio.getTime()));
    	q.setParameter("fechaFin", new Timestamp(fechaFin.getTime()));
    	return q.getResultList();
   	
    }
	
	

}
