/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidades.Proveedor;
import com.ec.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioProveedor {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Proveedor proveedor) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar proveedor");
        } finally {
            em.close();
        }

    }

    public void eliminar(Proveedor proveedor) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(proveedor));
            em.getTransaction().commit();



        } catch (Exception e) {
            System.out.println("Error en eliminar  proveedor" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(Proveedor proveedor) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar proveedor");
        } finally {
            em.close();
        }

    }

    public List<Proveedor> FindALlProveedor() {

        List<Proveedor> listaClientes = new ArrayList<Proveedor>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Proveedor.findAll", Proveedor.class);
//           query.setParameter("codigoUsuario", proveedor);
            listaClientes = (List<Proveedor>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta proveedor");
        } finally {
            em.close();
        }

        return listaClientes;
    }

    public List<Proveedor> FindALlProveedorUnico(Proveedor proveedor) {

        List<Proveedor> listaClientes = new ArrayList<Proveedor>();
        try {
            //Connection connection = em.unwrap(Connection.class);
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Proveedor.findByCedulaNombre", Proveedor.class);
            query.setParameter("provIdentificacion", proveedor.getProvIdentificacion());
            query.setParameter("provNombre", proveedor.getProvNombre());
            listaClientes = (List<Proveedor>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta proveedor");
        } finally {
            em.close();
        }

        return listaClientes;
    }
}
