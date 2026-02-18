package com.gestioncitas.modelo;

import com.gestioncitas.dao.PacienteDAO;

public class PruebaConexion {

    public static void main(String[] args) {

        PacienteDAO dao = new PacienteDAO();

        int idEliminar = 1; // ⚠️ Pon un ID que exista en tu BD

        boolean resultado = dao.eliminar(idEliminar);

        if (resultado) {
            System.out.println("Paciente eliminado correctamente");
        } else {
            System.out.println("Error al eliminar paciente");
        }
    }
}