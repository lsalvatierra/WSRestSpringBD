/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.bean;

/**
 *
 * @author LuisAngel
 */
public class Respuesta {

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isRpta() {
        return rpta;
    }

    public void setRpta(boolean rpta) {
        this.rpta = rpta;
    }
    
    private String mensaje;
    private boolean rpta;
}
