/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.Factura;
import com.ec.entidades.Proveedor;
import com.ec.servicio.ServicioFactura;
import com.ec.servicio.ServicioProveedor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class FacturaViewModel {
    //servicios 

    @Wire
    Window wFactura;
    ServicioProveedor servicioProveedor = new ServicioProveedor();
    ServicioFactura servicioFactura = new ServicioFactura();
    //variable
    private List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
    private Factura factura = new Factura();
    private String acccion = "create";

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("factura") Factura factura, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (factura != null) {
            this.factura = factura;
            acccion = "edit";
        } else {
            this.factura = new Factura();
            acccion = "create";
        }

    }

    @Command
    @NotifyChange({"visita"})
    public void registrarFactura() throws IOException {

        if (factura.getFacNumero() != null) {

            if (acccion.equals("create")) {
                servicioFactura.crear(factura);
                Messagebox.show("Factura registrada exitosamente.");
                wFactura.detach();
            } else {
                servicioFactura.modificar(factura);
                Messagebox.show("Factura registrada exitosamente.");
                wFactura.detach();
            }

        } else {
            Messagebox.show("Verifique la información ingresada", "Atención", Messagebox.OK, Messagebox.ERROR);
        }

    }

    public FacturaViewModel() {
        consultarProveedorAll();
    }

    private void consultarProveedorAll() {
        listaProveedor = servicioProveedor.FindALlProveedor();
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getAcccion() {
        return acccion;
    }

    public void setAcccion(String acccion) {
        this.acccion = acccion;
    }
}
