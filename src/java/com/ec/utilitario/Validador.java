/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.utilitario;

import java.util.Map;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

/**
 *
 * @author gato
 */
public class Validador  {

   
    public boolean validateEmail( String email) {
        if (email == null || !email.matches(".+@.+\\.[a-z]+")) {
          return true;
        }else{
          return false;
        }
    }

    public boolean validateCedula( String cedula) {
        if (cedula == null || !cedula.matches("/^[0-9]+$/")) {
           return true;
        }else{
          return false;
        }
    }
}
