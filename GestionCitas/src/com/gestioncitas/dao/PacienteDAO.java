package com.gestioncitas.dao;

import com.gestioncitas.conexion.ConexionBD;
import com.gestioncitas.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // INSERTAR PACIENTE
    public boolean insertar(Paciente paciente) {

        String sql = "INSERT INTO paciente (nombre, apellido, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getTelefono());
            ps.setString(4, paciente.getCorreo());
            ps.setString(5, paciente.getDireccion());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    // LISTAR PACIENTES
    public List<Paciente> listar() {

        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR PACIENTE
    public boolean actualizar(Paciente paciente) {

        String sql = "UPDATE paciente SET nombre=?, apellido=?, telefono=?, correo=?, direccion=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getTelefono());
            ps.setString(4, paciente.getCorreo());
            ps.setString(5, paciente.getDireccion());
            ps.setInt(6, paciente.getId());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR PACIENTE
    public boolean eliminar(int id) {

        String sql = "DELETE FROM paciente WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}