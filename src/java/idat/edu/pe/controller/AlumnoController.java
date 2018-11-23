/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import idat.edu.pe.bean.Alumno;
import idat.edu.pe.bean.Respuesta;
import idat.edu.pe.dao.AlumnoDAO;
import idat.edu.pe.exception.GeneralException;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LuisAngel
 */
@RestController
@RequestMapping("/Alumno")
public class AlumnoController {
    
    
    @RequestMapping(value="/ListarAlumnos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Alumno>> ListarAlumnos()  throws GeneralException{
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
            @PathVariable String IdEspecialidad) throws GeneralException{
        String ErrorControlado ="";
        try {
            AlumnoDAO objAluDAO = new AlumnoDAO();
            List<Alumno> lstAlumno = 
                    objAluDAO.ListarAlumnosxEspecialidad(IdEspecialidad);
            if(lstAlumno.size() > 0){
                return new ResponseEntity<>(lstAlumno, HttpStatus.OK);
            }else{
                ErrorControlado = HttpStatus.BAD_REQUEST.value()+
                            "/No existe información para la consulta.";
                throw new GeneralException(ErrorControlado);
            }
        } catch (Exception e) {
            if(ErrorControlado.equals("")){
                ErrorControlado = HttpStatus.BAD_REQUEST.value()+
                            "/Error al conectarse a la base de datos";
            }           
            throw new GeneralException(ErrorControlado);
        }      
    }
    
    
    @RequestMapping(value ="/ListarAlumnosxEspecPost", 
            method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Alumno>> ListarAlumnosxEspecPost(@RequestBody String objJson) throws GeneralException{
        String ErrorControlado ="";
        try {
            AlumnoDAO objAluDAO = new AlumnoDAO();
            Alumno objAlumno = new Gson()
                .fromJson(objJson, Alumno.class);
            List<Alumno> lstAlumno = 
                    objAluDAO.ListarAlumnosxEspecialidad(objAlumno.getIdEspecialidad());
            if(lstAlumno.size() > 0){
                return new ResponseEntity<>(lstAlumno, HttpStatus.OK);
            }else{
                ErrorControlado = HttpStatus.BAD_REQUEST.value()+
                            "/No existe información para la consulta.";
                throw new GeneralException(ErrorControlado);
            }
        } catch (Exception e) {
            if(ErrorControlado.equals("")){
                ErrorControlado = HttpStatus.BAD_REQUEST.value()+
                            "/Error al conectarse a la base de datos";
            }           
            throw new GeneralException(ErrorControlado);
        }      
    }
    
         @RequestMapping(value = "/EliminarAlumno", method = 
            RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String EliminarAlumno(
            @RequestBody String objJson){        
        AlumnoDAO objAlumnoDAO = 
                new AlumnoDAO();
        Alumno objAlumno = new Gson()
                .fromJson(objJson, Alumno.class);        
        Boolean rpta = 
                objAlumnoDAO
                        .EliminarAlumno(objAlumno.getIdAlumno());
              Respuesta objRpta = new Respuesta();
        String mensaje = rpta ? "Correcto" : "Error";
        objRpta.setMensaje(mensaje);
        objRpta.setRpta(rpta);        
        return new Gson().toJson(objRpta);
    }
    
          @RequestMapping(value = "/RegistrarAlumno", method = 
            RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String RegistrarAlumno(
            @RequestBody String objJson){        
        AlumnoDAO objAlumnoDAO = 
                new AlumnoDAO();
        Alumno objAlumno = new Gson()
                .fromJson(objJson, Alumno.class);        
        Boolean rpta = 
                objAlumnoDAO
                        .RegistrarAlumno(objAlumno);
              Respuesta objRpta = new Respuesta();
        String mensaje = rpta ? "Correcto" : "Error";
        objRpta.setMensaje(mensaje);
        objRpta.setRpta(rpta);        
        return new Gson().toJson(objRpta);
    }
    
          @RequestMapping(value = "/ModificarAlumno", method = 
            RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String ModificarAlumno(
            @RequestBody String objJson){        
        AlumnoDAO objAlumnoDAO = 
                new AlumnoDAO();
        Alumno objAlumno = new Gson()
                .fromJson(objJson, Alumno.class);        
        Boolean rpta = 
                objAlumnoDAO.ModificarAlumno(objAlumno);
        Respuesta objRpta = new Respuesta();
        String mensaje = rpta ? "Correcto" : "Error";
        objRpta.setMensaje(mensaje);
        objRpta.setRpta(rpta);        
        return new Gson().toJson(objRpta);
    }
    //INFORMACIÓN EN FORMATO JSON
    //{"ApeAlumno":"bbbb","NomAlumno":"ccc","IdEspecialidad":"E01","Procedencia":"E", "IdAlumno":"A0042"}
}
