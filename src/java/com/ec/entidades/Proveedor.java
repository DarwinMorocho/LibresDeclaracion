/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gato
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedor.findByProvIdentificacion", query = "SELECT p FROM Proveedor p WHERE p.provIdentificacion = :provIdentificacion"),
    @NamedQuery(name = "Proveedor.findByCedulaNombre", query = "SELECT p FROM Proveedor p WHERE p.provIdentificacion = :provIdentificacion OR p.provNombre=:provNombre"),
    @NamedQuery(name = "Proveedor.findByProvNombre", query = "SELECT p FROM Proveedor p WHERE p.provNombre = :provNombre"),
    @NamedQuery(name = "Proveedor.findByProvDireccion", query = "SELECT p FROM Proveedor p WHERE p.provDireccion = :provDireccion"),
    @NamedQuery(name = "Proveedor.findByProvTelefono", query = "SELECT p FROM Proveedor p WHERE p.provTelefono = :provTelefono"),
    @NamedQuery(name = "Proveedor.findByProvEstado", query = "SELECT p FROM Proveedor p WHERE p.provEstado = :provEstado")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Size(max = 13)
    @Column(name = "prov_identificacion")
    private String provIdentificacion;
    @Size(max = 100)
    @Column(name = "prov_nombre")
    private String provNombre;
    @Size(max = 100)
    @Column(name = "prov_direccion")
    private String provDireccion;
    @Size(max = 100)
    @Column(name = "prov_telefono")
    private String provTelefono;
    @Size(max = 10)
    @Column(name = "prov_estado")
    private String provEstado;
    @OneToMany(mappedBy = "idProveedor")
    private Collection<Factura> facturaCollection;

    public Proveedor() {
    }
   

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getProvIdentificacion() {
        return provIdentificacion;
    }

    public void setProvIdentificacion(String provIdentificacion) {
        this.provIdentificacion = provIdentificacion;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getProvDireccion() {
        return provDireccion;
    }

    public void setProvDireccion(String provDireccion) {
        this.provDireccion = provDireccion;
    }

    public String getProvTelefono() {
        return provTelefono;
    }

    public void setProvTelefono(String provTelefono) {
        this.provTelefono = provTelefono;
    }

    public String getProvEstado() {
        return provEstado;
    }

    public void setProvEstado(String provEstado) {
        this.provEstado = provEstado;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Proveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
