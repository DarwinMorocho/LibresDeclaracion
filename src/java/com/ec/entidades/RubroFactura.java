/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gato
 */
@Entity
@Table(name = "rubro_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RubroFactura.findAll", query = "SELECT r FROM RubroFactura r where r.factura.idUsuario=:idUsuario ORDER BY r.rubros.rubDescripcion"),
        @NamedQuery(name = "RubroFactura.findAllBetween", query = "SELECT r FROM RubroFactura r where r.factura.idUsuario=:idUsuario and r.fechaRegistro BETWEEN :fechaInicio AND :fechaFin ORDER BY r.rubros.rubDescripcion"),
//    @NamedQuery(name = "RubroFactura.findAllReport", query = "SELECT r.rubros.rubDescripcion,COUNT(r.rubros) FROM RubroFactura r WHERE r.factura.idUsuario:=idUsuario AND r.fechaRegistro BETWEEN :fechaInicio AND :fechaFin GROUP BY r.rubros"),
    @NamedQuery(name = "RubroFactura.findByIdRubro", query = "SELECT r FROM RubroFactura r WHERE r.rubroFacturaPK.idRubro = :idRubro"),
    @NamedQuery(name = "RubroFactura.findByIdCompras", query = "SELECT r FROM RubroFactura r WHERE r.rubroFacturaPK.idCompras = :idCompras"),
    @NamedQuery(name = "RubroFactura.findByFechaRegistro", query = "SELECT r FROM RubroFactura r WHERE r.fechaRegistro = :fechaRegistro")})
public class RubroFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RubroFacturaPK rubroFacturaPK;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @JoinColumn(name = "id_compras", referencedColumnName = "id_compras", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rubros rubros;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rubroFactura")
    private Collection<Detalle> detalleCollection;
    @Transient
    private List<Rubros> listaRubros = new ArrayList<Rubros>();
    @Column(name = "rf_cantidad")
    private BigDecimal rfCantidad;
    @Column(name = "rf_subtotal")
    private BigDecimal rfSubtotal;
    @Column(name = "rf_iva")
    private BigDecimal rfIva;
    @Column(name = "rf_total")
    private BigDecimal rfTotal;
    @Size(max = 100)
    @Column(name = "rf_descripcion")
    private String rfDescripcion;

    public RubroFactura() {
    }

    public RubroFactura(RubroFacturaPK rubroFacturaPK) {
        this.rubroFacturaPK = rubroFacturaPK;
    }

    public RubroFactura(int idRubro, int idCompras) {
        this.rubroFacturaPK = new RubroFacturaPK(idRubro, idCompras);
    }

    public RubroFacturaPK getRubroFacturaPK() {
        return rubroFacturaPK;
    }

    public void setRubroFacturaPK(RubroFacturaPK rubroFacturaPK) {
        this.rubroFacturaPK = rubroFacturaPK;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Rubros getRubros() {
        return rubros;
    }

    public void setRubros(Rubros rubros) {
        this.rubros = rubros;
    }

    public List<Rubros> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<Rubros> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public BigDecimal getRfCantidad() {
        return rfCantidad;
    }

    public void setRfCantidad(BigDecimal rfCantidad) {
        this.rfCantidad = rfCantidad;
    }

    public BigDecimal getRfSubtotal() {
        return rfSubtotal;
    }

    public void setRfSubtotal(BigDecimal rfSubtotal) {
        this.rfSubtotal = rfSubtotal;
    }

    public BigDecimal getRfIva() {
        return rfIva;
    }

    public void setRfIva(BigDecimal rfIva) {
        this.rfIva = rfIva;
    }

    public BigDecimal getRfTotal() {
        return rfTotal;
    }

    public void setRfTotal(BigDecimal rfTotal) {
        this.rfTotal = rfTotal;
    }

    public String getRfDescripcion() {
        return rfDescripcion;
    }

    public void setRfDescripcion(String rfDescripcion) {
        this.rfDescripcion = rfDescripcion;
    }

    @XmlTransient
    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubroFacturaPK != null ? rubroFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroFactura)) {
            return false;
        }
        RubroFactura other = (RubroFactura) object;
        if ((this.rubroFacturaPK == null && other.rubroFacturaPK != null) || (this.rubroFacturaPK != null && !this.rubroFacturaPK.equals(other.rubroFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RubroFactura[ rubroFacturaPK=" + rubroFacturaPK + " ]";
    }
}
