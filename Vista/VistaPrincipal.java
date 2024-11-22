package Vista;

import Controlador.Controlador;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
    private Controlador controlador;
    private Usuario usuario;
    private JProgressBar kcalProgressBar;
    private JProgressBar proteinProgressBar;
    private JProgressBar carbsProgressBar;
    private JProgressBar fatsProgressBar;

    public VistaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        this.usuario = controlador.getUsuario();

        setTitle("Consulta Nutricional");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.BLACK); // Fondo de la ventana negro para estilo

        add(createMacrosPanel());

        JTextField ingredienteField = new JTextField();
        add(new JLabel("Ingrediente:"));
        add(ingredienteField);

        JButton consultarButton = new JButton("Consultar Información Nutricional");
        add(consultarButton);

        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        add(new JScrollPane(resultadoArea));

        consultarButton.addActionListener(e -> {
            String ingrediente = ingredienteField.getText();
            String resultado = controlador.obtenerInformacionNutricional(ingrediente);
            if (resultado == null || resultado.isEmpty()) {
                resultadoArea.setText("No se encontró información para el ingrediente: " + ingrediente);
            } else {
                resultadoArea.setText(resultado);
            }
            actualizarMacros();
        });

        // Botón para ver el historial de peso
        JButton btnHistorialPeso = new JButton("Ver Historial de Peso");
        btnHistorialPeso.addActionListener(e -> {
            int usuarioId = controlador.getUsuario().getId();
            VistaHistorialPeso historialPeso = new VistaHistorialPeso(controlador, usuarioId);
            historialPeso.setVisible(true);
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.BLACK);
        panelBotones.add(btnHistorialPeso);
        add(panelBotones);

        pack();
        setVisible(true);
    }

    private JPanel createMacrosPanel() {
        JPanel macrosPanel = new JPanel();
        macrosPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Diseño de cuadrícula con espacio entre filas y columnas
        macrosPanel.setBackground(Color.BLACK);

        // Colores personalizados para las barras
        Color progressBarColor = Color.LIGHT_GRAY;
        Color borderColor = Color.DARK_GRAY;

        // Configuración para las barras
        kcalProgressBar = crearBarraProgreso(usuario.getDieta().getTotalKcal(), 1800, progressBarColor, borderColor);
        proteinProgressBar = crearBarraProgreso(usuario.getDieta().getTotalProteinas(), 150, progressBarColor, borderColor);
        carbsProgressBar = crearBarraProgreso(usuario.getDieta().getTotalCarbohidratos(), 300, progressBarColor, borderColor);
        fatsProgressBar = crearBarraProgreso(usuario.getDieta().getTotalGrasas(), 70, progressBarColor, borderColor);

        // Agregar las etiquetas y las barras al panel
        macrosPanel.add(crearEtiqueta("Kcal", usuario.getDieta().getTotalKcal(), 1800));
        macrosPanel.add(kcalProgressBar);
        macrosPanel.add(crearEtiqueta("Proteínas", usuario.getDieta().getTotalProteinas(), 150));
        macrosPanel.add(proteinProgressBar);
        macrosPanel.add(crearEtiqueta("Carbohidratos", usuario.getDieta().getTotalCarbohidratos(), 300));
        macrosPanel.add(carbsProgressBar);
        macrosPanel.add(crearEtiqueta("Grasas", usuario.getDieta().getTotalGrasas(), 70));
        macrosPanel.add(fatsProgressBar);

        return macrosPanel;
    }

    // Método para crear una barra de progreso personalizada
    private JProgressBar crearBarraProgreso(int valor, int maximo, Color colorBarra, Color colorBorde) {
        JProgressBar progressBar = new JProgressBar(0, maximo);
        progressBar.setValue(valor);
        progressBar.setForeground(colorBarra);
        progressBar.setBackground(Color.BLACK);
        progressBar.setBorder(BorderFactory.createLineBorder(colorBorde, 2)); // Borde oscuro
        progressBar.setStringPainted(false); // No mostrar texto dentro de la barra
        return progressBar;
    }

    // Método para crear etiquetas personalizadas
    private JLabel crearEtiqueta(String nombre, int valor, int maximo) {
        JLabel label = new JLabel(nombre + ": " + valor + " / " + maximo + "g");
        label.setForeground(Color.LIGHT_GRAY); // Color del texto
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente del texto
        return label;
    }

    private void actualizarMacros() {
        kcalProgressBar.setValue(usuario.getDieta().getTotalKcal());
        proteinProgressBar.setValue(usuario.getDieta().getTotalProteinas());
        carbsProgressBar.setValue(usuario.getDieta().getTotalCarbohidratos());
        fatsProgressBar.setValue(usuario.getDieta().getTotalGrasas());
    }
}
