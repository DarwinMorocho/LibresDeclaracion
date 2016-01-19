/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.Factura;
import com.ec.entidades.Usuario;
import com.ec.servicio.ServicioUsuario;
import java.io.IOException;
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
public class UsuarioViewModel {

    ServicioUsuario servicioUsuario= new ServicioUsuario();
    private Usuario usuario = new Usuario();
    private String acccion = "create";
    @Wire
    Window wUsuarioE;

    @AfterCompose
    public void afterCompose(@ExecutionArgParam("usuario") Usuario usuario, @ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        if (usuario != null) {
            this.usuario = usuario;
            acccion = "edit";
        } else {
            this.usuario = new Usuario();
            acccion = "create";
        }

    }

    @Command
    @NotifyChange({"visita"})
    public void registrarUsuario() throws IOException {

        if (usuario.getUsuNombre() != null) {

            if (acccion.equals("create")) {
                usuario.setUsuNivel(1);
                servicioUsuario.crear(usuario);
                Messagebox.show("Usuario registrado exitosamente.");
                wUsuarioE.detach();
            } else {
                servicioUsuario.modificar(usuario);
                Messagebox.show("Usuario registrado exitosamente.");
                wUsuarioE.detach();
            }

        } else {
            Messagebox.show("Verifique la información ingresada", "Atención", Messagebox.OK, Messagebox.ERROR);
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAcccion() {
        return acccion;
    }

    public void setAcccion(String acccion) {
        this.acccion = acccion;
    }
}
