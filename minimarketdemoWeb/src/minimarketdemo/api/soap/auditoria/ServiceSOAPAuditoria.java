package minimarketdemo.api.soap.auditoria;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.utils.ModelUtil;

//Transforma a la clase en api soap protocolo tcp/ip
@WebService(serviceName = "ServiceAuditoria")
public class ServiceSOAPAuditoria {
	@EJB
	private ManagerAuditoria mAuditoria;

	//especificar lo estrictamente necesario
	//permite ejecutar metodos void y llamar datos
	@WebMethod(operationName = "findBitacoraAyer")
	public List<AudBitacora> findBitacoraAyer(){
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}
	@WebMethod(operationName = "eliminarBitacota")
	public void eliminarBitacora() {
		mAuditoria.eliminarBitacora();
	}

	
}
