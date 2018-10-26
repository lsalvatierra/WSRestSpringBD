/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.idao;

import idat.edu.pe.bean.Alumno;
import java.util.List;

/**
 *
 * @author LuisAngel
 */
public interface IAlumnoDAO {
    
    public List<Alumno> ListarAlumnosxEspecialidad(String IdEspecialidad);
    public List<Alumno> ListarAlumnos();
    public Boolean RegistrarAlumno(Alumno objAlumno);
    public Boolean ModificarAlumno(Alumno objAlumno);    
    public Boolean EliminarAlumno(String IdAlumno);     
    
}
