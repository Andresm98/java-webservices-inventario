package minimarketdemo.model.inventario.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.InvDetalleAjusteProducto;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerAjusteProd
 */
@Stateless
@LocalBean
public class ManagerAjusteProd {

    /**
     * Default constructor. 
     */
	@EJB
	private ManagerDAO mDAO;
	
	
    public ManagerAjusteProd() {
        // TODO Auto-generated constructor stub
    }

    //public List<InvDetalleAjusteProducto>
    
}
