/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import idat.edu.pe.bean.Especialidad;
import idat.edu.pe.dao.EspecialidadDAO;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LuisAngel
 */
@RestController
@RequestMapping("/pagos")
public class EspecialidadController {
    
        @RequestMapping(value="/pago", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public String pago(){
       return "Mi primer servicio SPRING";     
    } 
    @RequestMapping(value="/listPersonas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String getPersona(){
        EspecialidadDAO objEspeDAO = new EspecialidadDAO();
        List<Especialidad> lstEspecialidad = objEspeDAO.ListarEspecialidad();        
       return new Gson().toJson(lstEspecialidad);     
    }    
}
