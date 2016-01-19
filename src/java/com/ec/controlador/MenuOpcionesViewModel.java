/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import java.util.HashMap;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;

/**
 *
 * @author gato
 */
public class MenuOpcionesViewModel extends SelectorComposer<Component> {

    @Wire("#btnAdministrar")
    Button btnAdministrar;
    @Wire
    Button btnSeguridad;
    UserCredential credential = new UserCredential();
    private String acceso = "";

    public MenuOpcionesViewModel() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credential = cre;
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

//
        if (credential.getUsuarioSistema().getUsuTipo().equalsIgnoreCase("administrador")) {
            btnSeguridad.setVisible(Boolean.TRUE);

        } else {
            btnSeguridad.setVisible(Boolean.FALSE);
        }
//
//
//            btnAdministrar.setVisible(Boolean.TRUE);
//
//            btnCotizar.setVisible(Boolean.TRUE);
//
//            btnCompaginado.setVisible(Boolean.TRUE);
//
//            btnGiganto.setVisible(Boolean.TRUE);
//
//            btnDigital.setVisible(Boolean.TRUE);
//
//            btnUsuarios.setVisible(Boolean.TRUE);
//
//        } else {
//            btnAdministrar.setVisible(Boolean.FALSE);
//
//            btnCotizar.setVisible(Boolean.FALSE);
//
//            btnCompaginado.setVisible(Boolean.FALSE);
//
//            btnGiganto.setVisible(Boolean.FALSE);
//
//            btnDigital.setVisible(Boolean.FALSE);
//
//            btnUsuarios.setVisible(Boolean.FALSE);
//        }
    }

    @Listen("onClick = #btnAdministrar")
    public void doadministrarDocumentos() {
        Executions.sendRedirect("/declarar/administrarRegistros.zul");
    }

    @Listen("onClick = #btnSeguridad")
    public void doadministrarSeguridad() {
        Executions.sendRedirect("/declarar/administrarSeguridad.zul");
    }

    @Listen("onClick = #btnFactEgresos")
    public void doadministrarEgresos() {
        Executions.sendRedirect("/declarar/administrarEgresos.zul");
    }

    @Listen("onClick = #btnFactReportes")
    public void doadministrarIngresos() {
        Executions.sendRedirect("/declarar/administrarReportes.zul");
    }

    public UserCredential getCredential() {
        return credential;
    }

    public void setCredential(UserCredential credential) {
        this.credential = credential;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
}
