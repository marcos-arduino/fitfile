package Vista;

import Controlador.Controlador;
import Modelo.UsuarioProgreso;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaHistorialPeso extends JFrame {
    public VistaHistorialPeso(Controlador controlador, int usuarioId) {
        setTitle("Historial de Peso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        List<UsuarioProgreso> historial = controlador.obtenerHistorialPeso(usuarioId);

        String[] columnNames = {"Fecha", "Peso"};
        String[][] data = new String[historial.size()][2];

        for (int i = 0; i < historial.size(); i++) {
            UsuarioProgreso progreso = historial.get(i);
            data[i][0] = progreso.getFecha().toString();
            data[i][1] = String.valueOf(progreso.getPeso());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }
}
