package minimarketdemo.api.rest.auditoria;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.utils.ModelUtil;

@RequestScoped
@Path("auditoria")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRESTAuditoria {

	// Esto es una prueba
	@EJB
	private ManagerAuditoria mAuditoria;
	
	@GET
	@Path(value = "bitacora")
	public List<AudBitacora> findBitacoraAyer(){
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(
				new Date(),-1),new Date());
	}
	
	@POST
	@Path(value = "bitacora")
	public String crearPistaAuditoria() {
		System.out.println("Se ha creado la pista de auditoria");
		return "{\"mensaje\":\"se ha creador okey.\"}";
	}
	
	@POST
	@Path (value = "bitacora2")
	public String crearDelAuditorita() {
		return  "{\"mensaje\":\"se ha creador kaya.\"}";
	}
	
	@POST
	@Path(value = "bitacoraPrueba")
	public String crearDelAuditoritaCommit() {
		return  "{\"mensaje\":\"esta es una prueba del commit.\"}";
	}
	// esta es otra prueba
	
}
