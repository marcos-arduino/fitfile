package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarUsuario(String nombreUsuario, String contraseña, String nombre, int edad, double peso, double altura, String gustos) throws SQLException {
        String sql = "INSERT INTO usuarios (nombreUsuario, contraseña, nombre, edad, peso, altura, gustos) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombreUsuario);
        statement.setString(2, contraseña);
        statement.setString(3, nombre);
        statement.setInt(4, edad);
        statement.setDouble(5, peso);
        statement.setDouble(6, altura);
        statement.setString(7, gustos);
        statement.executeUpdate();
    }

    public Usuario autenticar(String nombreUsuario, String contraseña) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contraseña = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nombreUsuario);
        statement.setString(2, contraseña);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Usuario(
                resultSet.getString("nombre"),
                resultSet.getInt("edad"),
                resultSet.getDouble("peso"),
                resultSet.getDouble("altura"),
                resultSet.getString("gustos")
            );
        } else {
            return null;
        }
    }
}
