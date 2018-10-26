/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.dao;

import idat.edu.pe.bd.BDConnection;
import idat.edu.pe.bean.Alumno;
import idat.edu.pe.idao.IAlumnoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LuisAngel
 */
public class AlumnoDAO implements IAlumnoDAO {

    @Override
    public List<Alumno> ListarAlumnosxEspecialidad(String IdEspecialidad) {
            List<Alumno> lista = new ArrayList<>();
            BDConnection objCon = BDConnection.getInstance();               
            Connection con = objCon.EstablecerConexion();
            try{
                PreparedStatement pstmt = con.prepareStatement("{call dbo.sp_AlumnosxEspecialidad(?)}");
                pstmt.setString(1,IdEspecialidad);
                ResultSet rs =pstmt.executeQuery();
                while (rs.next()) {
                   lista.add(new Alumno( rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)));
                }
                rs.close();
                pstmt.close();
                objCon.Desconectar();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            
         return lista;         
        
    }

    @Override
    public List<Alumno> ListarAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        BDConnection objCon = BDConnection.getInstance();             
        Connection con = objCon.EstablecerConexion();
        try{
            Statement cstmt = con.createStatement();
            ResultSet rs =cstmt.executeQuery("{call dbo.sp_ListarAlumno}");
            while (rs.next()) {
               lista.add(new Alumno(rs.getString(1),rs.getString(3),rs.getString(2),rs.getString(4), rs.getString(5)));
            }
            rs.close();
            cstmt.close();
            objCon.Desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return lista;        
    }

    @Override
    public Boolean RegistrarAlumno(Alumno objAlumno) {
        BDConnection objCon = BDConnection.getInstance();               
        Connection con = objCon.EstablecerConexion();
        Boolean rpta = null;
        try{
            PreparedStatement pstmt = con.prepareStatement("{call dbo.sp_MantRegistrarAlumno(?,?,?,?)}");
            pstmt.setString(1,objAlumno.getNomAlumno());
            pstmt.setString(2,objAlumno.getApeAlumno());
            pstmt.setString(3,objAlumno.getIdEspecialidad());
            pstmt.setString(4,objAlumno.getProcedencia());
            pstmt.execute();
            rpta = pstmt.getUpdateCount() > 0 ? true : false;
            pstmt.close();
            objCon.Desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
        return rpta;         
    }

    @Override
    public Boolean ModificarAlumno(Alumno objAlumno) {
        BDConnection objCon = BDConnection.getInstance();              
        Connection con = objCon.EstablecerConexion();
        Boolean rpta = null;
        try{
            PreparedStatement pstmt = con.prepareStatement("{call dbo.sp_MantActualizarAlumno(?,?,?,?,?)}");
            pstmt.setString(1,objAlumno.getIdAlumno());
            pstmt.setString(2,objAlumno.getNomAlumno());
            pstmt.setString(3,objAlumno.getApeAlumno());
            pstmt.setString(4,objAlumno.getIdEspecialidad());
            pstmt.setString(5,objAlumno.getProcedencia());
            pstmt.execute();
            rpta = pstmt.getUpdateCount() > 0 ? true : false;
            pstmt.close();
            objCon.Desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
        return rpta;         
    }

    @Override
    public Boolean EliminarAlumno(String IdAlumno) {
        BDConnection objCon = BDConnection.getInstance();            
        Connection con = objCon.EstablecerConexion();
        Boolean rpta = null;
        try{
            PreparedStatement pstmt = con.prepareStatement("{call dbo.sp_MantEliminarAlumno(?)}");
            pstmt.setString(1,IdAlumno);
            pstmt.execute();
            rpta = pstmt.getUpdateCount() > 0 ? true : false;
            pstmt.close();
            objCon.Desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }  
        return rpta;          
    }
    
}
