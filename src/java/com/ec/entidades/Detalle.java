/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gato
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByIdDetalle", query = "SELECT d FROM Detalle d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detalle.findByDetCantidad", query = "SELECT d FROM Detalle d WHERE d.detCantidad = :detCantidad"),
    @NamedQuery(name = "Detalle.findByDetDescripcion", query = "SELECT d FROM Detalle d WHERE d.detDescripcion = :detDescripcion"),
    @NamedQuery(name = "Detalle.findByDetUnitario", query = "SELECT d FROM Detalle d WHERE d.detUnitario = :detUnitario"),
    @NamedQuery(name = "Detalle.findByDetIva", query = "SELECT d FROM Detalle d WHERE d.detIva = :detIva"),
    @NamedQuery(name = "Detalle.findByDetTotal", query = "SELECT d FROM Detalle d WHERE d.detTotal = :detTotal")})
public class Detalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "det_cantidad")
    private BigDecimal detCantidad;
    @Size(max = 100)
    @Column(name = "det_descripcion")
    private String detDescripcion;
    @Column(name = "det_unitario")
    private Float detUnitario;
    @Column(name = "det_iva")
    private Float detIva;
    @Column(name = "det_total")
    private Float detTotal;
    @JoinColumns({
        @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro"),
        @JoinColumn(name = "id_compras", referencedColumnName = "id_compras")})
    @ManyToOne(optional = false)
    private RubroFactura rubroFactura;

    public Detalle() {
    }

    public Detalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigDecimal getDetCantidad() {
        return detCantidad;
    }

    public void setDetCantidad(BigDecimal detCantidad) {
        this.detCantidad = detCantidad;
    }

    public String getDetDescripcion() {
        return detDescripcion;
    }

    public void setDetDescripcion(String detDescripcion) {
        this.detDescripcion = detDescripcion;
    }

    public Float getDetUnitario() {
        return detUnitario;
    }

    public void setDetUnitario(Float detUnitario) {
        this.detUnitario = detUnitario;
    }

    public Float getDetIva() {
        return detIva;
    }

    public void setDetIva(Float detIva) {
        this.detIva = detIva;
    }

    public Float getDetTotal() {
        return detTotal;
    }

    public void setDetTotal(Float detTotal) {
        this.detTotal = detTotal;
    }

    public RubroFactura getRubroFactura() {
        return rubroFactura;
    }

    public void setRubroFactura(RubroFactura rubroFactura) {
        this.rubroFactura = rubroFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detalle[ idDetalle=" + idDetalle + " ]";
    }
    
}
