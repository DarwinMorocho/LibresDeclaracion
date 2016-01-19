/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitario;

import com.ec.entidades.RubroFactura;
import com.ec.entidades.Rubros;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gato
 */
public class RubroFacturaModel {

    private RubroFactura rubroFactura = new RubroFactura();
    private List<Rubros> listaRubro = new ArrayList<Rubros>();

    public RubroFacturaModel() {
    }

    public RubroFacturaModel(List<Rubros> listaRubro) {
        this.listaRubro = listaRubro;
    }

    public RubroFactura getRubroFactura() {
        return rubroFactura;
    }

    public void setRubroFactura(RubroFactura rubroFactura) {
        this.rubroFactura = rubroFactura;
    }

    public List<Rubros> getListaRubro() {
        return listaRubro;
    }

    public void setListaRubro(List<Rubros> listaRubro) {
        this.listaRubro = listaRubro;
    }
}
