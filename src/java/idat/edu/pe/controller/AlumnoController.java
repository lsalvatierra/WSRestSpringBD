/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import idat.edu.pe.bean.Alumno;
import idat.edu.pe.dao.AlumnoDAO;
import idat.edu.pe.exception.GeneralException;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LuisAngel
 */
@RestController
@RequestMapping("/Alumno")
public class AlumnoController {
    
    
    @RequestMapping(value="/ListarAlumnos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Alumno>> ListarAlumnosxEspec()  throws GeneralException{
        try {
            AlumnoDAO objAluDAO = new AlumnoDAO();
            List<Alumno> lstAlumno = objAluDAO.ListarAlumnos();        
            return new ResponseEntity<>(lstAlumno, HttpStatus.OK);  
        } catch (Exception e) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED.value()+"/Invalid employee name requested");
        } 
    }
    
        @RequestMapping(value ="/ListarAlumnosxEspec/{IdEspecialidad}", 
            method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Alumno>> ListarAlumnosxEspec(
            @PathVariable String IdEspecialidad){
        AlumnoDAO objAluDAO = new AlumnoDAO();
        List<Alumno> lstAlumno = 
                objAluDAO
                   .ListarAlumnosxEspecialidad(IdEspecialidad);
        return new ResponseEntity<>(lstAlumno, HttpStatus.OK);
        
    }
    
}
