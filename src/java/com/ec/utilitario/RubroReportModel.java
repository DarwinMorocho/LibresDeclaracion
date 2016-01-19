/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitario;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author gato
 */
public class RubroReportModel {

    private String nombre;
    private BigDecimal cantidad;
    private BigDecimal porcentajePaste;

    public RubroReportModel(String nombre, BigDecimal cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPorcentajePaste() {
        return porcentajePaste;
    }

    public void setPorcentajePaste(BigDecimal porcentajePaste) {
        this.porcentajePaste = porcentajePaste;
    }
}
