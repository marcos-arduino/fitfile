package Vista;

import Controlador.Controlador;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String dbUrl = "jdbc:mysql://localhost:3306/seguimientofitness";
            String user = "root";
            String password = "";

            Controlador controlador = new Controlador(dbUrl, user, password);
            new VistaLogin(controlador);
        });
    }
}
