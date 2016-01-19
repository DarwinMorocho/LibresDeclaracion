/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.controlador;

import com.ec.entidades.Factura;
import com.ec.entidades.Proveedor;
import com.ec.entidades.Rubros;
import com.ec.entidades.Usuario;
import com.ec.seguridad.EnumSesion;
import com.ec.seguridad.UserCredential;
import com.ec.servicio.ServicioFactura;
import com.ec.servicio.ServicioProveedor;
import com.ec.servicio.ServicioRubro;
import com.ec.servicio.ServicioUsuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.zkoss.zul.Messagebox;

/**
 *
 * @author gato
 */
public class AdministrarViewModel {

    //servicios 
    ServicioProveedor servicioProveedor = new ServicioProveedor();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    ServicioFactura servicioFactura = new ServicioFactura();
    ServicioRubro servicioRubro = new ServicioRubro();
    //variable
    private List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
    private List<Factura> listaFacturas = new ArrayList<Factura>();
    private List<Rubros> listaRubros = new ArrayList<Rubros>();
    private String nombreLogin = "";
    Usuario credentialLog = new Usuario();

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);



    }

    public AdministrarViewModel() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute(EnumSesion.userCredential.getNombre());
        credentialLog = cre.getUsuarioSistema();
        System.out.println("idUsuario recuperado " + cre.getUsuarioSistema().getIdUsuario());
        consultarProveedorAll();
        consultarUsuario();
        consultarFactura();
        consultarRubros();
    }
//metodos de consultas

    private void consultarUsuario() {
        listaUsuario = servicioUsuario.FindUsuarioAllLike(nombreLogin);
    }

    private void consultarProveedorAll() {
        listaProveedor = servicioProveedor.FindALlProveedor();
    }

    private void consultarRubros() {
        listaRubros = servicioRubro.FindALlRubroForUser(credentialLog);
    }

    public Usuario getCredentialLog() {
        return credentialLog;
    }

    public void setCredentialLog(Usuario credentialLog) {
        this.credentialLog = credentialLog;
    }

    @NotifyChange("listaFacturas")
    private void consultarFactura() {
        listaFacturas = servicioFactura.FindALl(nombreLogin);
    }

    @Command
    @NotifyChange("listaProveedor")
    public void nuevoProveedor() {

        final HashMap<String, Proveedor> map = new HashMap<String, Proveedor>();
        map.put("proveedor", new Proveedor());
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiProvedor.zul", null, null);
        window.doModal();
        consultarProveedorAll();
    }

    @Command
    @NotifyChange("listaUsuario")
    public void nuevoUsuario() {

        final HashMap<String, Usuario> map = new HashMap<String, Usuario>();
        map.put("cliente", new Usuario());
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiUsuario.zul", null, map);
        window.doModal();
        consultarUsuario();
    }

    @Command
    @NotifyChange("listaFacturas")
    public void nuevaFactura() {

        final HashMap<String, Factura> map = new HashMap<String, Factura>();
        map.put("cliente", new Factura());
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiFactura.zul", null, null);
        window.doModal();
        consultarFactura();
    }

    //metodos para modificar
    @Command
    @NotifyChange("listaFacturas")
    public void modificarFactura(@BindingParam("factura") Factura factura) {

        final HashMap<String, Factura> map = new HashMap<String, Factura>();
        map.put("factura", factura);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiFactura.zul", null, map);
        window.doModal();
        consultarFactura();
    }

    @Command
    @NotifyChange("listaProveedor")
    public void modificarProveedor(@BindingParam("proveedor") Proveedor proveedor) {

        final HashMap<String, Proveedor> map = new HashMap<String, Proveedor>();
        map.put("proveedor", proveedor);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiProvedor.zul", null, map);
        window.doModal();
        consultarProveedorAll();
    }

    @Command
    @NotifyChange("listaUsuario")
    public void modificarUsuario(@BindingParam("usuario") Usuario usuario) {

        final HashMap<String, Usuario> map = new HashMap<String, Usuario>();
        map.put("usuario", usuario);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiUsuario.zul", null, map);
        window.doModal();
        consultarUsuario();
    }

    @Command
    @NotifyChange("listaRubros")
    public void nuevoRubro() {

        final HashMap<String, Proveedor> map = new HashMap<String, Proveedor>();
        map.put("proveedor", null);
        org.zkoss.zul.Window window = (org.zkoss.zul.Window) Executions.createComponents(
                "/declarar/guiRubro.zul", null, null);
        window.doModal();
        consultarRubros();
    }

    @Command
    @NotifyChange("listaRubros")
    public void modificarRubro(@BindingParam("rubro") Rubros rubros) {

        servicioRubro.modificar(rubros);
        Messagebox.show("Registrado exitosamente.");
    }

    //metodos get and set
    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getNombreLogin() {
        return nombreLogin;
    }

    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
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
}
