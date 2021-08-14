package minimarketdemo.api.rest.productos;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.entities.InvProductoAPI;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.producto.dao.DAOInventario;

@RequestScoped
@Path("inventario")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRESTProductos {

	@EJB
	private ManagerAuditoria mAuditoria;

	@Inject
	private DAOInventario daoAuditoria;

	// comentario
	@GET
	@Path(value = "bitacora")
	public List<AudBitacora> findBitacoraAyer() {
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}

	@GET
	@Path(value = "productos")
	public List<InvProductoAPI> findProdutos() {
		return daoAuditoria.findAllProductoApi2();
	}

	@GET
	@Path("{id}")
	public Response getTodo(@PathParam("id") Integer id) {
		InvProductoAPI todo = daoAuditoria.findByIdApi(id);
		System.out.println("GET llega el parametro: "+id );
		return Response.ok(todo).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Integer id, InvProductoAPI todo) {
		InvProductoAPI updateTodo = daoAuditoria.findByIdApi(id);
		System.out.println("POST el id si llega: "+ id);
		updateTodo.setStock(todo.getStock());
		daoAuditoria.update(updateTodo);
		return Response.ok().build();
	}

	@POST
	public Response create(InvProductoAPI todo) {
		daoAuditoria.create(todo);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id) {
		InvProductoAPI getTodo = daoAuditoria.findByIdApi(id);
		daoAuditoria.delete(getTodo);
		return Response.ok().build();
	}
}
