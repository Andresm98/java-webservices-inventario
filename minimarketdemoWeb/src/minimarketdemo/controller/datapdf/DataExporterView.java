package minimarketdemo.controller.datapdf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

@Named
@ViewScoped
public class DataExporterView implements Serializable {

    private List<Product> products;

    private Exporter<DataTable> textExporter;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = service.getProducts(100);
        textExporter = new TextExporter();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }

    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }


}