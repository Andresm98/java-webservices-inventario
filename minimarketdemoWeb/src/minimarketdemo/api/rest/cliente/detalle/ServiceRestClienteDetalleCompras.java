package minimarketdemo.api.rest.cliente.detalle;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import minimarketdemo.model.detalle.managers.*;

@RequestScoped
@Path("detalle")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRestClienteDetalleCompras {

	private Client client;
    private WebTarget target;
    //@PostConstruct
    public List<DetalleCompras> init() {
        client = ClientBuilder.newClient();
        //query params: ?q=Turku&cnt=10&mode=json&units=metric
        target = client.target("http://node246599-grupocompras.j.layershift.co.uk/minimarketdemoWeb/apirest/cajero/listaFacturaProducto")
           .queryParam("cnt", "10")
           .queryParam("mode", "json")
           .queryParam("units", "metric");
        System.out.println(target.request(MediaType.APPLICATION_JSON).get(String.class));
        
        String json = target.request(MediaType.APPLICATION_JSON).get(String.class);
        List<DetalleCompras> listDetalleC = null;
        
        try {
            Gson gson = new Gson();
            listDetalleC = gson.fromJson(json, new TypeToken<List<DetalleCompras>>(){}.getType());
            
        } catch (Exception e) {
            System.out.println("******************\nError: "+e.getMessage()+"\n*****************************");
        }
        return listDetalleC;
    }
}
