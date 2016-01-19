/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.servicio;

import com.ec.entidades.Usuario;
import com.ec.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gato
 */
public class ServicioUsuario {

    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void crear(Usuario usuario) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar usuario");
        } finally {
            em.close();
        }

    }

    public void eliminar(Usuario usuario) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.remove(em.merge(usuario));
            em.getTransaction().commit();



        } catch (Exception e) {
            System.out.println("Error en eliminar  usuario" + e);
        } finally {
            em.close();
        }

    }

    public void modificar(Usuario usuario) {

        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en insertar usuario");
        } finally {
            em.close();
        }

    }

    public Usuario FindUsuarioPorNombre(String nombre) {

        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        Usuario usuarioObtenido = new Usuario();
        try {
            //Connection connection = em.unwrap(Connection.class);

            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Usuario.findByUsuLogin", Usuario.class);
            query.setParameter("usuLogin", nombre);
            listaUsuario = (List<Usuario>) query.getResultList();
            if (listaUsuario.size() > 0) {
                for (Usuario usuario : listaUsuario) {
                    usuarioObtenido = usuario;
                }
            } else {
                usuarioObtenido = null;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en lsa consulta usuario");
        } finally {
            em.close();
        }

        return usuarioObtenido;
    }

    public List<Usuario> FindUsuarioAllLike(String nombreLogin) {

        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        try {
            em = HelperPersistencia.getEMF();
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Usuario.findAllLike", Usuario.class);
            query.setParameter("usuLogin", "%" + nombreLogin + "%");
            listaUsuario = (List<Usuario>) query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en la consulta usuario");
        } finally {
            em.close();
        }
        return listaUsuario;
    }

    public int suma() {
        return 3 + 2;
    }
}
