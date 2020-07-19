/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cursosonline.dao;

import com.cursosonline.entidades.Estudiantes;
import com.cursosonline.util.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ROSADO RONALD
 */
public class EstudiantesImplDAO implements EstudiantesDAO {
    
     @Override
    public List<Estudiantes> getEstudiantes() {
         List<Estudiantes> estudiantes = new ArrayList<>();
        
        Connection conn;    
        try {
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
                     
            String sql="SELECT id, nombres, apellidos, email FROM estudiantes;";
            
            PreparedStatement stm= conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Estudiantes estudiante = new Estudiantes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                estudiantes.add(estudiante);
            }
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        return  estudiantes;
    }
    
    @Override
    public void ingresar(Estudiantes estudiante) {
        String query = "INSERT INTO estudiantes( nombres, apellidos, email)VALUES(?, ?, ?);";
        Connection conn;
        try{
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
            
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, estudiante.getNombres());
            stm.setString(2, estudiante.getApellidos());
            stm.setString(3, estudiante.getEmail());
            stm.execute();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void actualizar(Estudiantes estudiante) {
        String query = "UPDATE estudiantes SET nombres=?, apellidos=?, email=? WHERE id=?;";
        Connection conn;
        try{
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
            
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, estudiante.getNombres());
            stm.setString(2, estudiante.getApellidos());
            stm.setString(3, estudiante.getEmail());
            stm.setInt(4, estudiante.getId());
            stm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM estudiantes WHERE id=?;";
       
       Connection conn;
        try{
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
            
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   
    
    
}
