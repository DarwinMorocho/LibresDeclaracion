/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidades.Factura;
import com.ec.entidades.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioFactura {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Factura factura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(factura);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar factura");
        } finally {
            em.close();
        }

    }

    public void eliminar(Factura factura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(factura));
            em.getTransaction().commit();



        } catch (Exception e) {
            System.out.println("Error en eliminar  factura" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(Factura factura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(factura);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar factura");
        } finally {
            em.close();
        }

    }

  
    public List<Factura> FindALl(String buscar) {

        List<Factura> listaFactura = new ArrayList<Factura>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Factura.findAll", Factura.class);
//            query.setParameter("buscar", "%" + buscar + "%");
            listaFactura = (List<Factura>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta factura");
        } finally {
            em.close();
        }

        return listaFactura;
    }

   
}
