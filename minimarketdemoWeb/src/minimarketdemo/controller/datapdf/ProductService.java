package minimarketdemo.controller.datapdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.inventario.managers.ManagerCatInventario;
import minimarketdemo.model.inventario.managers.ManagerInventario;

@Named
@ApplicationScoped
public class ProductService {

    List<Product> products;

    @PostConstruct
    public void init() {
    	ManagerInventario mInventario = new ManagerInventario();
    	ManagerAuditoria mAuditoria = new ManagerAuditoria();
    	ManagerCatInventario mCategoria = new ManagerCatInventario();
    	mAuditoria.mostrarLog(null, getClass(), null, null);
    	mInventario.findAllProducto();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProducts(int size) {

        if (size > products.size()) {
            Random rand = new Random();

            List<Product> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
            return new ArrayList<>(products.subList(0, size));
        }

    }

	public List<Product> getClonedProducts(int size) {
		List<Product> results = new ArrayList<>();
		List<Product> originals = getProducts(size);
		for (Product original : originals) {
	
		}
		return results;
	}
}