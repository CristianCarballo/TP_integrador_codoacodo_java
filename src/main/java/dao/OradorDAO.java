package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Orador;
import utils.ConexionDB;

public class OradorDAO {

   public void agregarOrador(Orador orador) {
      String sql = "INSERT INTO orador (nombre, apellido, tema, fecha_alta) VALUES (?, ?, ?, ?)";
      //bloque try-with-resources
      //asegura que los recursos abiertos en su declaración (dentro de los paréntesis) se cierren automáticamente al final del bloque try 
      try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, orador.getNombre());
         pstmt.setString(2, orador.getApellido());
         pstmt.setString(3, orador.getTema());
         pstmt.setDate(4, orador.getFechaAlta());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public Orador obtenerPorId(int id) {
      String sql = "SELECT * FROM orador WHERE id_orador = ?";
      try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            Orador orador = new Orador();
            orador.setIdOrador(rs.getInt("id_orador"));
            orador.setNombre(rs.getString("nombre"));
            orador.setApellido(rs.getString("apellido"));
            orador.setTema(rs.getString("tema"));
            orador.setFechaAlta(rs.getDate("fecha_alta"));
            return orador;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

   public List<Orador> obtenerTodos() {
      List<Orador> oradores = new ArrayList<>();
      String sql = "SELECT * FROM orador";
      try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

         while (rs.next()) {
            Orador orador = new Orador();
            orador.setIdOrador(rs.getInt("id_orador"));
            orador.setNombre(rs.getString("nombre"));
            orador.setApellido(rs.getString("apellido"));
            orador.setTema(rs.getString("tema"));
            orador.setFechaAlta(rs.getDate("fecha_alta"));
            oradores.add(orador);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return oradores;
   }

   public void actualizarOrador(Orador orador) {
      String sql = "UPDATE orador SET nombre = ?, apellido = ?, tema = ?, fecha_alta = ? WHERE id_orador = ?";
      try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setString(1, orador.getNombre());
         pstmt.setString(2, orador.getApellido());
         pstmt.setString(3, orador.getTema());
         pstmt.setDate(4, orador.getFechaAlta());
         pstmt.setInt(5, orador.getIdOrador());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void eliminarOrador(int id) {
      String sql = "DELETE FROM orador WHERE id_orador = ?";
      try (Connection conn = ConexionDB.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

         pstmt.setInt(1, id);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
