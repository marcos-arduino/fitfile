package Vista;

import Controlador.Controlador;
import Modelo.Usuario;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipal extends JFrame {
    private Controlador controlador;
    private Usuario usuario;
    private JLabel macrosLabel;
    private JLabel proteinLabel;
    private JLabel carbsLabel;
    private JLabel fatsLabel;

    public VistaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        this.usuario = controlador.getUsuario(); // Asegúrate de tener el método getUsuario() en Controlador

        setTitle("Consulta Nutricional");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(createMacrosPanel());

        JTextField ingredienteField = new JTextField();
        add(new JLabel("Ingrediente:"));
        add(ingredienteField);

        JButton consultarButton = new JButton("Consultar Información Nutricional");
        add(consultarButton);

        JTextArea resultadoArea = new JTextArea();
        add(new JScrollPane(resultadoArea));

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ingrediente = ingredienteField.getText();
                String resultado = controlador.obtenerInformacionNutricional(ingrediente);
                resultadoArea.setText(resultado);
                actualizarMacros();
            }
        });
        
        // Botón para ver el historial de peso
        JButton btnHistorialPeso = new JButton("Ver Historial de Peso");
        btnHistorialPeso.addActionListener(e -> {
            int usuarioId = controlador.getUsuario().getId();
            VistaHistorialPeso historialPeso = new VistaHistorialPeso(controlador, usuarioId);
            historialPeso.setVisible(true);
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnHistorialPeso);

        add(panelBotones, BorderLayout.SOUTH);

        // Mostrar la ventana principal
        setVisible(true);
    }



    private JPanel createMacrosPanel() {
        JPanel macrosPanel = new JPanel();
        macrosPanel.setLayout(new BoxLayout(macrosPanel, BoxLayout.Y_AXIS));

        macrosLabel = new JLabel("Kcal: " + usuario.getDieta().getTotalKcal() + " / 1800");
        proteinLabel = new JLabel("Proteínas: " + usuario.getDieta().getTotalProteinas() + "g");
        carbsLabel = new JLabel("Carbohidratos: " + usuario.getDieta().getTotalCarbohidratos() + "g");
        fatsLabel = new JLabel("Grasas: " + usuario.getDieta().getTotalGrasas() + "g");

        JButton adjustMacrosButton = new JButton("Ajustar macros");

        macrosPanel.add(macrosLabel);
        macrosPanel.add(proteinLabel);
        macrosPanel.add(carbsLabel);
        macrosPanel.add(fatsLabel);
        macrosPanel.add(adjustMacrosButton);

        return macrosPanel;
    }

    private void actualizarMacros() {
        macrosLabel.setText("Kcal: " + usuario.getDieta().getTotalKcal() + " / 1800");
        proteinLabel.setText("Proteínas: " + usuario.getDieta().getTotalProteinas() + "g");
        carbsLabel.setText("Carbohidratos: " + usuario.getDieta().getTotalCarbohidratos() + "g");
        fatsLabel.setText("Grasas: " + usuario.getDieta().getTotalGrasas() + "g");
    }
    

}
