package com.cursosonline.dao;

import com.cursosonline.entidades.Curso;
import com.cursosonline.util.Util;
import static com.cursosonline.util.Util.CLAVE;
import static com.cursosonline.util.Util.USUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROSADO RONALD
 */
public class CursosDaoImpl implements CursoDao{
    
    
    
    @Override
    public List<Curso> getCurso() {
        List<Curso> cursos = new ArrayList<>();
        
        Connection conn;    
        try {
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
                     
            String sql="SELECT id, nombre FROM cursos;";
            
            PreparedStatement stm= conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Curso curso = new Curso(rs.getInt(1),rs.getString(2));
                cursos.add(curso);
            }
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        return  cursos;
        
    }

    @Override
    public void ingresar(Curso curso) {
        String query = "INSERT INTO cursos( nombre)VALUES(?);";
        Connection conn;
        try{
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
            
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, curso.getNombre());
            stm.execute();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void actualizar(Curso curso) {
        String query = "UPDATE cursos SET nombre=? WHERE id=?;";
        Connection conn;
        try{
            conn = DriverManager.getConnection(Util.URL, Util.USUARIO, Util.CLAVE);
            
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, curso.getNombre());
            stm.setInt(2, curso.getId());
            stm.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
       String query = "DELETE FROM cursos WHERE id=?;";
       
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
