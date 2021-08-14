package minimarketdemo.controller.datapdf;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.export.DataTableExporter;
import org.primefaces.component.export.ExportConfiguration;
import org.primefaces.util.EscapeUtils;



public class TextExporter  extends DataTableExporter {

    private OutputStreamWriter osw;
    private PrintWriter writer;

    @Override
    protected void preExport(FacesContext context, ExportConfiguration exportConfiguration) throws IOException {

        osw = new OutputStreamWriter(getOutputStream(), exportConfiguration.getEncodingType());
        writer = new PrintWriter(osw);

        if (exportConfiguration.getPreProcessor() != null) {
            exportConfiguration.getPreProcessor().invoke(context.getELContext(), new Object[]{writer});
        }
    }

    @Override
    protected void doExport(FacesContext context, DataTable table, ExportConfiguration exportConfiguration, int index) throws IOException {

        writer.append("" + table.getId() + "\n");

        if (exportConfiguration.isPageOnly()) {
            exportPageOnly(context, table, writer);
        } else if (exportConfiguration.isSelectionOnly()) {
            exportSelectionOnly(context, table, writer);
        } else {
            exportAll(context, table, writer);
        }

        writer.append("" + table.getId() + "");

        table.setRowIndex(-1);
    }

    @Override
    protected void postExport(FacesContext context, ExportConfiguration exportConfiguration) throws IOException {

        if (exportConfiguration.getPostProcessor() != null) {
            exportConfiguration.getPostProcessor().invoke(context.getELContext(), new Object[]{writer});
        }

        writer.flush();
        writer.close();
        writer = null;

        osw.close();
        osw = null;
    }

    @Override
    protected void preRowExport(DataTable table, Object document) {
        ((PrintWriter) document).append("\t" + table.getVar() + "\n");
    }

    @Override
    protected void postRowExport(DataTable table, Object document) {
        ((PrintWriter) document).append("\t" + table.getVar() + "\n");
    }

    @Override
    protected void exportCells(DataTable table, Object document) {
        PrintWriter writer = (PrintWriter) document;
        for (org.primefaces.component.api.UIColumn col : table.getColumns()) {
            if (col instanceof DynamicColumn) {
                ((DynamicColumn) col).applyStatelessModel();
            }

            if (col.isRendered() && col.isExportable()) {
                String columnTag = getColumnTag(col);
                addColumnValue(writer, col.getChildren(), columnTag, col);
            }
        }
    }

    protected String getColumnTag(org.primefaces.component.api.UIColumn col) {
        String headerText = (col.getExportHeaderValue() != null) ? col.getExportHeaderValue() : col.getHeaderText();
        UIComponent facet = col.getFacet("header");
        String columnTag;

        if (headerText != null) {
            columnTag = headerText.toLowerCase();
        }
        else if (facet != null) {
            columnTag = exportValue(FacesContext.getCurrentInstance(), facet).toLowerCase();
        }
        else {
            throw new FacesException("No suitable xml tag found for " + col);
        }

        return EscapeUtils.forXmlTag(columnTag);
    }
    
    
    
    

    protected void addColumnValue(PrintWriter writer, List<UIComponent> components, String tag, org.primefaces.component.api.UIColumn col) {
        FacesContext context = FacesContext.getCurrentInstance();

        writer.append("\t\t" + tag + "");

        if (col.getExportFunction() != null) {
            writer.append(EscapeUtils.forXml(exportColumnByFunction(context, col)));
        }
        else {
            for (UIComponent component : components) {
                if (component.isRendered()) {
                    String value = exportValue(context, component);
                    if (value != null) {
                        writer.append(EscapeUtils.forXml(value));
                    }
                }
            }
        }

        writer.append("" + tag + "\n");
    }

    @Override
    public String getContentType() {
        return "text/plain";
    }

    @Override
    public String getFileExtension() {
        return ".txt";
    }

}
