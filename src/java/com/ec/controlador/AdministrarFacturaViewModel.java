/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.Factura;
import com.ec.entidades.Proveedor;
import com.ec.entidades.RubroFactura;
import com.ec.entidades.RubroFacturaPK;
import com.ec.entidades.Rubros;
import com.ec.entidades.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioFactura;
import com.ec.servicio.ServicioProveedor;
import com.ec.servicio.ServicioRubro;
import com.ec.servicio.ServicioRubroFactura;
import com.ec.servicio.ServicioUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarFacturaViewModel {

    @Wire
    Button btnFactura;
    private boolean validar = false;
    //servicios 
    ServicioProveedor servicioProveedor = new ServicioProveedor();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioFactura servicioFactura = new ServicioFactura();
    ServicioRubro servicioRubro = new ServicioRubro();
    ServicioRubroFactura servicioRubroFactura = new ServicioRubroFactura();
    //variable
    Usuario credentialLog = new Usuario();
    private Factura factura = new Factura();
    //listas de las tablas
    private List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
    private List<Factura> listaFacturas = new ArrayList<Factura>();
    private List<Rubros> listaRubros = new ArrayList<Rubros>();
    //registrar rubros-facturas
    //Armar el modelo de seleccion de permisos
    private ListModelList<RubroFactura> lstRubroFactura;
    private List<RubroFactura> listaRubroFactura = new ArrayList<RubroFactura>();
    private Set<RubroFactura> RubroFacturaSeleccionados;
    private RubroFactura rubroFactura = new RubroFactura();
    private Rubros rubro = null;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        ((ListModelList<RubroFactura>) lstRubroFactura).clear();



    }

    public AdministrarFacturaViewModel() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credentialLog = cre.getUsuarioSistema();
        System.out.println("usuario nombre usuario " + cre.getNombreUsuario());
        System.out.println("usuario idUsuario " + cre.getUsuarioSistema().getUsuNombre());
        System.out.println("idUsuario " + cre.getUsuarioSistema().getIdUsuario());
        consultarProveedorAll();
        consultarRubrosAll();
        factura = new Factura();
        factura.setFacFecha(new Date());
        getRubros();
    }
    //datos del modelo de perfil

    private void getRubros() {
        setLstRubroFactura(new ListModelList<RubroFactura>(getListaRubroFactura()));
        ((ListModelList<RubroFactura>) lstRubroFactura).setMultiple(true);
    }

    @Command
    public void rubrosSeleccionados() {
        RubroFacturaSeleccionados = ((ListModelList<RubroFactura>) getLstRubroFactura()).getSelection();

    }
//metodos de consultas

    private void consultarProveedorAll() {
        listaProveedor = servicioProveedor.FindALlProveedor();
    }

    private void consultarRubrosAll() {
        listaRubros = servicioRubro.FindALlRubroForUser(credentialLog);
    }

    public Usuario getCredentialLog() {
        return credentialLog;
    }

    public void setCredentialLog(Usuario credentialLog) {
        this.credentialLog = credentialLog;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public List<Rubros> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<Rubros> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public ListModelList<RubroFactura> getLstRubroFactura() {
        return lstRubroFactura;
    }

    public void setLstRubroFactura(ListModelList<RubroFactura> lstRubroFactura) {
        this.lstRubroFactura = lstRubroFactura;
    }

    public List<RubroFactura> getListaRubroFactura() {
        return listaRubroFactura;
    }

    public void setListaRubroFactura(List<RubroFactura> listaRubroFactura) {
        this.listaRubroFactura = listaRubroFactura;
    }

    public Set<RubroFactura> getRubroFacturaSeleccionados() {
        return RubroFacturaSeleccionados;
    }

    public void setRubroFacturaSeleccionados(Set<RubroFactura> RubroFacturaSeleccionados) {
        this.RubroFacturaSeleccionados = RubroFacturaSeleccionados;
    }

    public RubroFactura getRubroFactura() {
        return rubroFactura;
    }

    public void setRubroFactura(RubroFactura rubroFactura) {
        this.rubroFactura = rubroFactura;
    }

    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }

    public Rubros getRubro() {
        return rubro;
    }

    public void setRubro(Rubros rubro) {
        this.rubro = rubro;
    }

    ///
    @Command
    @NotifyChange("factura")
    public void calcularValores(@BindingParam("valor") BigDecimal valor) {
        if (valor != null) {
//            System.out.println("SUBTOTAL " + valor);
            factura.setFacSubtotal(valor);
            BigDecimal iva = valor.multiply(BigDecimal.valueOf(0.12));
//            System.out.println("iva " + iva);
            BigDecimal total = valor.add(iva);
//            System.out.println("total " + total);
            factura.setFacIva(iva);
            factura.setFacTotal(total);
        } else {
            factura.setFacSubtotal(BigDecimal.ZERO);
            factura.setFacIva(BigDecimal.ZERO);
            factura.setFacTotal(BigDecimal.ZERO);
        }
    }

    @Command
    @NotifyChange("factura")
    public void guardarFactura() {
        factura.setIdUsuario(credentialLog);
        servicioFactura.crear(factura);
        Messagebox.show("Registrado exitosamente.");
        btnFactura.setVisible(Boolean.FALSE);
        validar = true;
    }

    //materiales varios
    @Command
    @NotifyChange({"lstRubroFactura"})
    public void nuevoRubroFactura() {
        if (validar) {
            if (rubro != null) {
                List<Rubros> nuevalistaRubros = servicioRubro.FindALlRubroForUser(credentialLog);
                RubroFactura nuevoRegistro = new RubroFactura();
                RubroFacturaPK facturaPK = new RubroFacturaPK(rubro.getIdRubro(), factura.getIdCompras());
                nuevoRegistro.setRubroFacturaPK(facturaPK);
                nuevoRegistro.setRubros(rubro);
                nuevoRegistro.setFechaRegistro(new Date());
                if (!lstRubroFactura.contains(nuevoRegistro)) {
                    ((ListModelList<RubroFactura>) lstRubroFactura).add(nuevoRegistro);
                } else {
                    Messagebox.show("El rubro ya se encuentra registrado", "Atención", Messagebox.OK, Messagebox.ERROR);
                }

            } else {
                Messagebox.show("Seleccione un rubro", "Atención", Messagebox.OK, Messagebox.ERROR);
            }
//          
        } else {
            Messagebox.show("Primero debe registrar el valor total de la factura para ingresar el detalle", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }

    @Command
    @NotifyChange({"lstRubroFactura"})
    public void eliminarRubro() {
        ((ListModelList<RubroFactura>) lstRubroFactura).removeAll(RubroFacturaSeleccionados);

    }

    @Command
    @NotifyChange({"factura", "lstRubroFactura"})
    public void guardarRubroFactura() {
        for (RubroFactura item : lstRubroFactura) {
            servicioRubroFactura.crear(item);
        }

        Messagebox.show("Registrado exitosamente.");
        lstRubroFactura.clear();
        factura = new Factura();
        validar = false;
        btnFactura.setVisible(Boolean.TRUE);
    }

}
