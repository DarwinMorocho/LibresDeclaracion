/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gato
 */
@Embeddable
public class RubroFacturaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rubro")
    private int idRubro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_compras")
    private int idCompras;

    public RubroFacturaPK() {
    }

    public RubroFacturaPK(int idRubro, int idCompras) {
        this.idRubro = idRubro;
        this.idCompras = idCompras;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public int getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(int idCompras) {
        this.idCompras = idCompras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRubro;
        hash += (int) idCompras;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroFacturaPK)) {
            return false;
        }
        RubroFacturaPK other = (RubroFacturaPK) object;
        if (this.idRubro != other.idRubro) {
            return false;
        }
        if (this.idCompras != other.idCompras) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RubroFacturaPK[ idRubro=" + idRubro + ", idCompras=" + idCompras + " ]";
    }
    
}
