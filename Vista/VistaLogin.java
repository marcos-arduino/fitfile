package Vista;

import Controlador.Controlador;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {
    private Controlador controlador;
    private JTextField usuarioField;
    private JPasswordField contraseñaField;

    public VistaLogin(Controlador controlador) {
        this.controlador = controlador;

        setTitle("Inicio de Sesión");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nombre de Usuario:"));
        usuarioField = new JTextField();
        add(usuarioField);

        add(new JLabel("Contraseña:"));
        contraseñaField = new JPasswordField();
        add(contraseñaField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = usuarioField.getText();
                String contraseña = new String(contraseñaField.getPassword());
                Usuario usuario = controlador.iniciarSesion(nombreUsuario, contraseña);

                if (usuario != null) {
                    controlador.setUsuario(usuario);
                    new VistaPrincipal(controlador);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(VistaLogin.this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loginButton);

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VistaRegistro(controlador);
                dispose();
            }
        });
        add(registerButton);

        setVisible(true);
    }
}
