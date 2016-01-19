/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.RubroFactura;
import com.ec.entidades.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioFactura;
import com.ec.servicio.ServicioProveedor;
import com.ec.servicio.ServicioRubro;
import com.ec.servicio.ServicioRubroFactura;
import com.ec.servicio.ServicioUsuario;
import com.ec.utilitario.RubroReportModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Legend;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.YAxis;
import org.zkoss.chart.model.DefaultCategoryModel;
import org.zkoss.chart.plotOptions.PiePlotOptions;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarReportesViewModel {

    private boolean validarCheckPie = false;
    //servicios 
    ServicioProveedor servicioProveedor = new ServicioProveedor();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioFactura servicioFactura = new ServicioFactura();
    ServicioRubro servicioRubro = new ServicioRubro();
    ServicioRubroFactura servicioRubroFactura = new ServicioRubroFactura();
    Usuario credentialLog = new Usuario();
    List<RubroFactura> lstRubroFacturas = new ArrayList<RubroFactura>();

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        consultarRubrosFactura();



    }

    public AdministrarReportesViewModel() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credentialLog = cre.getUsuarioSistema();
    }

    private void consultarRubrosFactura() {
        lstRubroFacturas = servicioRubroFactura.FindALlRubroForUser(credentialLog);
    }
    @Wire
    Charts chartRubro;
    @Wire
    Charts chartFacturaGastos;
    private byte[] graficoBarrasEdad;
    private Date fechaInicioRubro = new Date();
    private Date fechaFinRubro = new Date();
    private Date fechaInicioRubroGastos = new Date();
    private Date fechaFinRubroGastos = new Date();

    @Command
    @NotifyChange({"chartRubro"})
    public void graficarForRubro() throws IOException {
//        Chart chartOptional = chartEdad.getChart();
////        chartOptional.clearOptonDataListener();
//        chartOptional.setPlotBorderWidth(0);
//        chartOptional.setPlotShadow(false);

        chartRubro.getTooltip().setPointFormat(
                "{series.name}: <b>{point.percentage:.1f}%</b>");

        PiePlotOptions plotOptions = chartRubro.getPlotOptions().getPie();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor("pointer");
        plotOptions.getDataLabels().setEnabled(false);
        plotOptions.setShowInLegend(true);



        chartRubro.getSeries().remove();
        Series series = chartRubro.getSeries();
        series.setType("pie");
        series.setName("Rubro: ");

        validarCheckPie = true;
        List<RubroReportModel> lstrubroFacturas = servicioRubroFactura.FindByReportRubro(credentialLog);
        for (RubroReportModel item : lstrubroFacturas) {
            if (validarCheckPie) {
                Point point = new Point(item.getNombre(), item.getCantidad());
                point.setSliced(true);
                point.setSelected(true);
                series.addPoint(point);
                validarCheckPie = false;
            } else {
                series.addPoint(new Point(item.getNombre(), item.getCantidad()));
            }
        }

    }

    public Date getFechaInicioRubroGastos() {
        return fechaInicioRubroGastos;
    }

    public void setFechaInicioRubroGastos(Date fechaInicioRubroGastos) {
        this.fechaInicioRubroGastos = fechaInicioRubroGastos;
    }

    public Date getFechaFinRubroGastos() {
        return fechaFinRubroGastos;
    }

    public void setFechaFinRubroGastos(Date fechaFinRubroGastos) {
        this.fechaFinRubroGastos = fechaFinRubroGastos;
    }

    
    
    @Command
    @NotifyChange({"chartRubro"})
    public void graficarGastos() throws IOException {
        List<RubroFactura> lstRubros = servicioRubroFactura.FindALlRubroForUserBetween(credentialLog, fechaInicioRubroGastos, fechaFinRubroGastos);
        DefaultCategoryModel model = new DefaultCategoryModel();
        for (RubroFactura item : lstRubros) {
            model.setValue("Gasto: ", item.getRubros().getRubDescripcion(), item.getFactura().getFacTotal());
        }
//usando ZKCHARTS
        chartFacturaGastos.setModel(model);


        chartFacturaGastos.getXAxis().setTitle("Rubros");
        chartFacturaGastos.getYAxis().setAllowDecimals(false);

        YAxis yAxis = chartFacturaGastos.getYAxis();
        yAxis.setMin(0);
//        yAxis.setMax(1000);
//        yAxis.setLineWidth(1000);
        yAxis.setTitle("Dolares");
        yAxis.getTitle().setAlign("high");
        yAxis.getLabels().setOverflow("justfy");

        chartFacturaGastos.getTooltip().setValueSuffix("Dolares");

        chartFacturaGastos.getPlotOptions().getBar().getDataLabels().setEnabled(true);

        Legend legend = chartFacturaGastos.getLegend();
        legend.setLayout("vertical");
        legend.setAlign("right");
        legend.setVerticalAlign("top");
        legend.setX(-40);
        legend.setY(100);
        legend.setFloating(true);
        legend.setBorderWidth(1);
        legend.setShadow(true);

        chartFacturaGastos.getCredits().setEnabled(false);
    }

    public Date getFechaInicioRubro() {
        return fechaInicioRubro;
    }

    public void setFechaInicioRubro(Date fechaInicioRubro) {
        this.fechaInicioRubro = fechaInicioRubro;
    }

    public Date getFechaFinRubro() {
        return fechaFinRubro;
    }

    public void setFechaFinRubro(Date fechaFinRubro) {
        this.fechaFinRubro = fechaFinRubro;
    }

    public boolean isValidarCheckPie() {
        return validarCheckPie;
    }

    public void setValidarCheckPie(boolean validarCheckPie) {
        this.validarCheckPie = validarCheckPie;
    }

    public Usuario getCredentialLog() {
        return credentialLog;
    }

    public void setCredentialLog(Usuario credentialLog) {
        this.credentialLog = credentialLog;
    }

    public List<RubroFactura> getLstRubroFacturas() {
        return lstRubroFacturas;
    }

    public void setLstRubroFacturas(List<RubroFactura> lstRubroFacturas) {
        this.lstRubroFacturas = lstRubroFacturas;
    }
    private Date fechaInicio = new Date();
    private Date fechaFin = new Date();

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Command
    @NotifyChange({"lstRubroFacturas"})
    public void buscarRubroFacturas() throws IOException {

        lstRubroFacturas = servicioRubroFactura.FindALlRubroForUserBetween(credentialLog, fechaInicio, fechaFin);
    }

    // GENERAL XML
    @Command
    public void generateXml() throws Exception {

        if (lstRubroFacturas.size() > 0) {
            String name = "archivo";


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "ATS", null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value

            //Item Node
            Element itemNode = document.createElement("datos_personales");
            //Key Node
            Element ruc = document.createElement("Ruc");
            Text nodeKeyValue = document.createTextNode(credentialLog.getUsuCedula());
            ruc.appendChild(nodeKeyValue);

            //Value Node
            Element razon_social = document.createElement("Razon_social");
            Text nodeRazonSocial = document.createTextNode(credentialLog.getUsuNombre());
            razon_social.appendChild(nodeRazonSocial);


            //append keyNode and valueNode to itemNode
            itemNode.appendChild(ruc);
            itemNode.appendChild(razon_social);
            //Item Node
            Element itemfactura = document.createElement("detalle_desgloce");
            //Key Node




//        List<DetalleCotizacion> detalleCotizacions = servicioDetalleCotizacion.FindDetalleCotizacionPorCotizacionXml(valor);
//        Element itemdetalleFactura = document.createElement("detalle_factura");
            int aux = 0;
//        Element num_cot = document.createElement("detalle_desgloce");
//        Text nodeNumCot = document.createTextNode("");
//        num_cot.appendChild(nodeNumCot);

            for (RubroFactura item : lstRubroFacturas) {
//            System.out.println("cotizacion " + valor.getCotNumero() + "  producto" + item.getDetProductoSolicitado());

                //Value Node
//            Element cot_val_total = document.createElement("valor_total");
//            Text nodeValTot = document.createTextNode(valor.getCotTotal().toString());
//            cot_val_total.appendChild(nodeValTot);

                //Key Node
                aux++;
                Element rubro = document.createElement("rubro");
                Text node_rubro = document.createTextNode(item.getRubros().getRubDescripcion());
                rubro.appendChild(node_rubro);

                Element detcantidad = document.createElement("cantidad");
                Text nodedetcant = document.createTextNode(item.getRfCantidad().toString());
                detcantidad.appendChild(nodedetcant);

                //Key Node
                Element descripcion = document.createElement("descripcion");
                Text nodedetsol = document.createTextNode(item.getRfDescripcion());
                descripcion.appendChild(nodedetsol);
                //Key Node
                Element det_sub_total = document.createElement("det_sub_total");
                Text nodeSub_total = document.createTextNode(item.getRfSubtotal().toString());
                det_sub_total.appendChild(nodeSub_total);

                Element iva = document.createElement("iva");
                Text node_iva = document.createTextNode(item.getRfIva().toString());
                iva.appendChild(node_iva);

                Element det_total = document.createElement("det_sub_total");
                Text node_total = document.createTextNode(item.getRfTotal().toString());
                det_total.appendChild(node_total);
//                //agrega al detalle de cotizacion
//                itemdetalleFactura.appendChild(detcantidad);
//                itemdetalleFactura.appendChild(det_solitado);
//                itemdetalleFactura.appendChild(det_sub_total);

                //agrega la cotizacion
                itemfactura.appendChild(rubro);
                itemfactura.appendChild(detcantidad);
                itemfactura.appendChild(descripcion);
                itemfactura.appendChild(det_sub_total);
                itemfactura.appendChild(iva);
                itemfactura.appendChild(det_total);


            }
            //Value Node
            //append itemNode to raiz
            raiz.appendChild(itemNode);
            raiz.appendChild(itemfactura);
//        raiz.appendChild(itemdetalleFactura);

            String directorioReportes = Executions.getCurrent().getDesktop().getWebApp()
                    .getRealPath("/reportes");
            String pathSalida = directorioReportes + File.separator + "ATS" + new Date().getMonth() + new Date().getSeconds() + ".xml";
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            System.out.println("Direccion del reporte  " + pathSalida);
            Result result = new StreamResult(pathSalida); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            FileInputStream inputStream;

            File dosfile = new File(pathSalida);
            if (dosfile.exists()) {
                inputStream = new FileInputStream(dosfile);
                Filedownload.save(inputStream, new MimetypesFileTypeMap().getContentType(dosfile), dosfile.getName());
            } else {
            }
        } else {
            Messagebox.show("No existe información a para generar el XML", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }


}
