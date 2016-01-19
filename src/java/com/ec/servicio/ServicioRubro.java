/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidades.Rubros;
import com.ec.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioRubro {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Rubros rubros) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(rubros);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar rubros");
        } finally {
            em.close();
        }

    }

    public void eliminar(Rubros rubros) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(rubros));
            em.getTransaction().commit();



        } catch (Exception e) {
            System.out.println("Error en eliminar  rubros" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(Rubros rubros) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(rubros);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar rubros");
        } finally {
            em.close();
        }

    }

    public List<Rubros> FindALlRubro() {

        List<Rubros> listaRubro = new ArrayList<Rubros>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Rubros.findAll", Rubros.class);
//           query.setParameter("codigoUsuario", rubros);
            listaRubro = (List<Rubros>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta rubros");
        } finally {
            em.close();
        }

        return listaRubro;
    }

    public List<Rubros> FindALlRubroForUser(Usuario usuario) {

        List<Rubros> listaRubro = new ArrayList<Rubros>();
        try {
            System.out.println("id de usuario para consultar rubros");
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Rubros.findAllUsuario", Rubros.class);
            query.setParameter("idUsuario", usuario);
            listaRubro = (List<Rubros>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta rubros"+e);
        } finally {
            em.close();
        }

        return listaRubro;
    }
}
