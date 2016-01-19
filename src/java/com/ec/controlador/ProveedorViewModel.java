/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.Factura;
import com.ec.entidades.Proveedor;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioProveedor;
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
public class ProveedorViewModel {

    UserCredential credential = new UserCredential();
    private Proveedor proveedor = new Proveedor();
    ServicioProveedor servicioProveedor = new ServicioProveedor();
    private String accion = "create";
    @Wire
    Window wProveedor;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("proveedor") Proveedor proveedor, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

        if (proveedor != null) {
            this.proveedor = proveedor;
            accion = "edit";
        } else {
            this.proveedor = new Proveedor();
            accion = "create";
        }
    }

    @Command
    @NotifyChange("proveedor")
    public void guardarProveedor() {
        if (servicioProveedor.FindALlProveedorUnico(proveedor).isEmpty()) {
            if (!proveedor.getProvNombre().equals("")) {
                if (accion.equals("create")) {
                    proveedor.setProvEstado("Activo");
                    servicioProveedor.crear(proveedor);
                    Messagebox.show("Guardado con exito");
                    wProveedor.detach();
                } else {
                    proveedor.setProvEstado("Activo");
                    servicioProveedor.modificar(proveedor);
                    Messagebox.show("Guardado con exito");
                    wProveedor.detach();

                }

            } else {
                Messagebox.show("Verifique la informacion requerida", "Atención", Messagebox.OK, Messagebox.ERROR);
            }
        } else {
            Messagebox.show("El proveedor ya se encuentra registrado", "Atención", Messagebox.OK, Messagebox.ERROR);
        }

    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
