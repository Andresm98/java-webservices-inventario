package minimarketdemo.api.soap.thumano;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.thumano.dtos.DTOThmCargo;
import minimarketdemo.model.thumano.managers.ManagerTHumano;

@WebService(serviceName = "ServicioTHumano")
public class ServiceTHumano {

	@EJB
	private ManagerTHumano mTHumano;
	
	//DTO es encapsulador, que permite transferir solo los campos necesarios
	@WebMethod(operationName = "findAllThmCargo")
	public List<DTOThmCargo> findAllThmCargo(){
		return mTHumano.findAllDTOThmCargo();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
