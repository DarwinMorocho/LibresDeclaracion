/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gato
 */
@Entity
@Table(name = "rubros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubros.findAll", query = "SELECT r FROM Rubros r"),
    @NamedQuery(name = "Rubros.findAllUsuario", query = "SELECT r FROM Rubros r WHERE r.idUsuario = :idUsuario"),
    @NamedQuery(name = "Rubros.findByIdRubro", query = "SELECT r FROM Rubros r WHERE r.idRubro = :idRubro"),
    @NamedQuery(name = "Rubros.findByRubDescripcion", query = "SELECT r FROM Rubros r WHERE r.rubDescripcion = :rubDescripcion"),
    @NamedQuery(name = "Rubros.findByRubCosto", query = "SELECT r FROM Rubros r WHERE r.rubCosto = :rubCosto"),
    @NamedQuery(name = "Rubros.findByRubPeriodo", query = "SELECT r FROM Rubros r WHERE r.rubPeriodo = :rubPeriodo")})
public class Rubros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rubro")
    private Integer idRubro;
    @Size(max = 200)
    @Column(name = "rub_descripcion")
    private String rubDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rub_costo")
    private Float rubCosto;
    @Column(name = "rub_periodo")
    @Temporal(TemporalType.DATE)
    private Date rubPeriodo;
    @Column(name = "rub_anio")
    private Integer rubAnio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rubros")
    private Collection<RubroFactura> rubroFacturaCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Rubros() {
    }

    public Rubros(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public String getRubDescripcion() {
        return rubDescripcion;
    }

    public void setRubDescripcion(String rubDescripcion) {
        this.rubDescripcion = rubDescripcion;
    }

    public Float getRubCosto() {
        return rubCosto;
    }

    public void setRubCosto(Float rubCosto) {
        this.rubCosto = rubCosto;
    }

    public Date getRubPeriodo() {
        return rubPeriodo;
    }

    public void setRubPeriodo(Date rubPeriodo) {
        this.rubPeriodo = rubPeriodo;
    }

    @XmlTransient
    public Collection<RubroFactura> getRubroFacturaCollection() {
        return rubroFacturaCollection;
    }

    public void setRubroFacturaCollection(Collection<RubroFactura> rubroFacturaCollection) {
        this.rubroFacturaCollection = rubroFacturaCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getRubAnio() {
        return rubAnio;
    }

    public void setRubAnio(Integer rubAnio) {
        this.rubAnio = rubAnio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRubro != null ? idRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubros)) {
            return false;
        }
        Rubros other = (Rubros) object;
        if ((this.idRubro == null && other.idRubro != null) || (this.idRubro != null && !this.idRubro.equals(other.idRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Rubros[ idRubro=" + idRubro + " ]";
    }
}
