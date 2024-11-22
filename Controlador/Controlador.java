package Controlador;

import Modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class Controlador {
    private Usuario usuario;
    private Connection connection;

    // Constructor que inicializa la conexión a la base de datos
    public Controlador(String dbUrl, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarUsuario(String nombre, int edad, double peso, double altura, String gustos, String nombreUsuario, String contraseña) {
        usuario = new Usuario(nombre, edad, peso, altura, gustos);
        try {
            String query = "INSERT INTO usuarios (nombre, edad, peso, altura, gustos, nombreUsuario, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nombre);
            statement.setInt(2, edad);
            statement.setDouble(3, peso);
            statement.setDouble(4, altura);
            statement.setString(5, gustos);
            statement.setString(6, nombreUsuario);
            statement.setString(7, contraseña);
            statement.executeUpdate();

            // Obtener el id generado automáticamente
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1)); // Asignar el id generado al objeto usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario iniciarSesion(String nombreUsuario, String contraseña) {
        try {
            String query = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contraseña = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombreUsuario);
            statement.setString(2, contraseña);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int edad = resultSet.getInt("edad");
                double peso = resultSet.getDouble("peso");
                double altura = resultSet.getDouble("altura");
                String gustos = resultSet.getString("gustos");

                usuario = new Usuario(nombre, edad, peso, altura, gustos);
                usuario.setId(resultSet.getInt("id")); // Asignar el id del usuario a la instancia
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para registrar el progreso utilizando el stored procedure
    public void registrarProgreso(int usuarioId, Date fecha, double peso, double porcentajeGrasa, double masaMuscular) {
        if (usuario != null) {
            try {
                String sql = "{CALL RegistrarProgreso(?, ?, ?, ?, ?)}";
                CallableStatement callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, usuarioId);
                callableStatement.setDate(2, new java.sql.Date(fecha.getTime()));
                callableStatement.setDouble(3, peso);
                callableStatement.setDouble(4, porcentajeGrasa);
                callableStatement.setDouble(5, masaMuscular);
                callableStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: Usuario no registrado.");
        }
    }

    // Método para calcular el IMC utilizando la función SQL
    public double calcularIMC(double peso, double altura) {
        try {
            String query = "SELECT CalcularIMC(?, ?) AS imc";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, peso);
            statement.setDouble(2, altura);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("imc");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Método para obtener el historial de peso del usuario
    public List<UsuarioProgreso> obtenerHistorialPeso(int usuarioId) {
        List<UsuarioProgreso> historial = new ArrayList<>();
        if (usuario != null) {
            try {
                String query = "SELECT fecha, peso FROM historialPeso WHERE usuario_id = ? ORDER BY fecha ASC";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, usuarioId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Date fecha = resultSet.getDate("fecha");
                    double peso = resultSet.getDouble("peso");
                    UsuarioProgreso progreso = new UsuarioProgreso(fecha, peso, 0, 0); // Sólo necesitamos fecha y peso
                    historial.add(progreso);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: Usuario no registrado.");
        }
        return historial;
    }

    public void agregarRutina(String nombre, Date fechaInicio, Date fechaFin) {
        if (usuario != null) {
            Rutina rutina = new Rutina(nombre, fechaInicio, fechaFin);
            usuario.agregarRutina(rutina);
        } else {
            System.err.println("Error: Usuario no registrado.");
        }
    }

    public void agregarDieta(String plato) {
        if (usuario != null) {
            Dieta dieta = usuario.getDieta() != null ? usuario.getDieta() : new Dieta();
            String infoNutricional = EdamamAPI.obtenerInformacionNutricional(plato);

            // Parsear la información nutricional de la respuesta JSON
            try {
                JSONObject jsonObject = new JSONObject(infoNutricional);
                int kcal = jsonObject.getJSONObject("totalNutrientsKCal").getJSONObject("ENERC_KCAL").getInt("quantity");
                int proteinas = jsonObject.getJSONObject("totalNutrients").getJSONObject("PROCNT").getInt("quantity");
                int carbohidratos = jsonObject.getJSONObject("totalNutrients").getJSONObject("CHOCDF").getInt("quantity");
                int grasas = jsonObject.getJSONObject("totalNutrients").getJSONObject("FAT").getInt("quantity");

                dieta.agregarPlato(plato, kcal, proteinas, carbohidratos, grasas);
                usuario.setDieta(dieta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: Usuario no registrado.");
        }
    }

    public String obtenerRecetaIA(String gustos) {
        if (usuario != null) {
            String prompt = "Genera una receta saludable basada en estos gustos: " + gustos;
            return IARecomendaciones.obtenerReceta(prompt);
        } else {
            return "Error: Usuario no registrado.";
        }
    }

    public String obtenerInformacionNutricional(String ingrediente) {
        if (usuario != null) {
            return EdamamAPI.obtenerInformacionNutricional(ingrediente);
        } else {
            return "Error: Usuario no registrado.";
        }
    }
}
