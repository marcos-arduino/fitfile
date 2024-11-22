package Vista;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame {
    private Controlador controlador;
    private JTextField nombreField;
    private JTextField edadField;
    private JTextField pesoField;
    private JTextField alturaField;
    private JTextField gustosField;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;

    public VistaRegistro(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Nombre de Usuario:"));
        usuarioField = new JTextField();
        add(usuarioField);

        add(new JLabel("Contraseña:"));
        contraseñaField = new JPasswordField();
        add(contraseñaField);

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Edad:"));
        edadField = new JTextField();
        add(edadField);

        add(new JLabel("Peso (kg):"));
        pesoField = new JTextField();
        add(pesoField);

        add(new JLabel("Altura (m):"));
        alturaField = new JTextField();
        add(alturaField);

        add(new JLabel("Gustos:"));
        gustosField = new JTextField();
        add(gustosField);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contraseña = new String(contraseñaField.getPassword());
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                double peso = Double.parseDouble(pesoField.getText());
                double altura = Double.parseDouble(alturaField.getText());
                String gustos = gustosField.getText();

                controlador.registrarUsuario(nombre, edad, peso, altura, gustos, usuario, contraseña);

                new VistaPrincipal(controlador);
                dispose();
            }
        });
        add(registrarButton);

        setVisible(true);
    }
}
