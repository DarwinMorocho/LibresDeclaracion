/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.Rubros;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioRubro;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author gato
 */
public class RubroViewModel {
    
    @Wire
    Window wRubroE;
    ServicioRubro servicioRubro = new ServicioRubro();
    Rubros rubros = new Rubros();
    UserCredential credentialLog = new UserCredential();
    
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credentialLog = cre;
        
    }
    
    public RubroViewModel() {
    }
    
    public Rubros getRubros() {
        return rubros;
    }
    
    public void setRubros(Rubros rubros) {
        this.rubros = rubros;
    }
    
    public UserCredential getCredentialLog() {
        return credentialLog;
    }
    
    public void setCredentialLog(UserCredential credentialLog) {
        this.credentialLog = credentialLog;
    }
    
    @Command
    public void guardarRubro() {
        
        if (!rubros.getRubDescripcion().equals("") && !rubros.getRubCosto().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy");
            String fechaFormateada = formato.format(new Date());
            rubros.setRubAnio(Integer.parseInt(fechaFormateada));
            System.out.println("anio " + fechaFormateada);
            rubros.setIdUsuario(credentialLog.getUsuarioSistema());
            servicioRubro.crear(rubros);
            Messagebox.show("Registrado exitosamente.");
            wRubroE.detach();
        } else {
            
            Messagebox.show("Verifique la información ingresada", "Atención", Messagebox.OK, Messagebox.ERROR);
        }
    }
}
