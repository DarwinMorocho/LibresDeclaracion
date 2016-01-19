/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidades.RubroFactura;
import com.ec.entidades.Usuario;
import com.ec.utilitario.RubroReportModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioRubroFactura {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(RubroFactura rubroFactura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(rubroFactura);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar rubroFactura");
        } finally {
            em.close();
        }

    }

    public void eliminar(RubroFactura rubroFactura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(rubroFactura));
            em.getTransaction().commit();



        } catch (Exception e) {
            System.out.println("Error en eliminar  rubroFactura" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(RubroFactura rubroFactura) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(rubroFactura);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar rubroFactura");
        } finally {
            em.close();
        }

    }

    public List<RubroFactura> FindALlRubro() {

        List<RubroFactura> listaRubro = new ArrayList<RubroFactura>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("RubroFactura.findAll", RubroFactura.class);
//           query.setParameter("codigoUsuario", rubroFactura);
            listaRubro = (List<RubroFactura>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta rubroFactura");
        } finally {
            em.close();
        }

        return listaRubro;
    }

    public List<RubroFactura> FindALlRubroForUser(Usuario usuario) {

        List<RubroFactura> listaRubro = new ArrayList<RubroFactura>();
        try {
            System.out.println("id de usuario para consultar rubroFactura");
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("RubroFactura.findAll", RubroFactura.class);
            query.setParameter("idUsuario", usuario);
            listaRubro = (List<RubroFactura>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta rubroFactura" + e);
        } finally {
            em.close();
        }

        return listaRubro;
    }

    public List<RubroFactura> FindALlRubroForUserBetween(Usuario usuario, Date inicio, Date Fin) {

        List<RubroFactura> listaRubro = new ArrayList<RubroFactura>();
        try {
            System.out.println("id de usuario para consultar rubroFactura");
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("RubroFactura.findAllBetween", RubroFactura.class);
            query.setParameter("idUsuario", usuario);
            query.setParameter("fechaInicio", inicio);
            query.setParameter("fechaFin", Fin);
            listaRubro = (List<RubroFactura>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta rubroFactura" + e);
        } finally {
            em.close();
        }

        return listaRubro;
    }

    //consulta para armar el pastel por genero
    public List<RubroReportModel> FindByReportRubro(Usuario usuario) {

        List<RubroReportModel> listaAreasChart = new ArrayList<RubroReportModel>();

        try {

            System.out.println("idUsuario " + usuario.getIdUsuario());
            System.out.println("inicio " + usuario.getIdUsuario());
            System.out.println("fin " + usuario.getIdUsuario());
            //Connection connection = em.unwrap(Connection.class);
            listaAreasChart.clear();
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT NEW com.ec.utilitario.RubroReportModel(r.rubros.rubDescripcion,SUM(r.rfTotal)) FROM RubroFactura r WHERE r.factura.idUsuario= :idUsuario  GROUP BY r.rubroFacturaPK.idRubro");
//            query.setParameter("fechaInicio", inicio);
//            query.setParameter("fechaFin", fin);
            query.setParameter("idUsuario", usuario);

            listaAreasChart = (List<RubroReportModel>) query.getResultList();
            System.out.println("longitud de la consulta de la FindByReportRubro " + listaAreasChart.size() + "  " + query.toString());
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta FindByReportRubro " + e);
        } finally {
            em.close();
        }

        return listaAreasChart;
    }
}
