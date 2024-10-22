package Vista;

import Controlador.Controlador;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class VistaPrincipal extends JFrame {
    private LocalDate currentDate = LocalDate.now(); // Fecha actual
    private int workoutIndex = 0; // Índice de rutina actual
    private String[] workouts = {"Espalda | Biceps | Triceps", "Piernas", "Hombros", "Cuello"}; // Rutinas
    private Controlador controlador;
    private Usuario usuario;

    public VistaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        this.usuario = new Usuario("Nombre", 25, 70.0, 1.75, "Sin gustos"); // Usuario temporal para pruebas
        controlador.setUsuario(usuario); // Enlaza el controlador con el usuario

        setTitle("Fitness App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);
        setLayout(new BorderLayout());

        // Panel principal para la vista de contenido
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Panel para cambiar la fecha
        JPanel datePanel = new JPanel();
        JButton prevDateButton = new JButton("<");
        JLabel dateLabel = new JLabel(currentDate.toString());
        JButton nextDateButton = new JButton(">");

        // Acciones para cambiar la fecha
        prevDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.minusDays(1);
                dateLabel.setText(currentDate.toString());
            }
        });
        nextDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.plusDays(1);
                dateLabel.setText(currentDate.toString());
            }
        });
        datePanel.add(prevDateButton);
        datePanel.add(dateLabel);
        datePanel.add(nextDateButton);

        // Panel para los macros
        JPanel macrosPanel = new JPanel();
        macrosPanel.setLayout(new BoxLayout(macrosPanel, BoxLayout.Y_AXIS));
        JLabel macrosLabel = new JLabel("Kcal: 400 / 1800");
        JButton adjustMacrosButton = new JButton("Ajustar macros");
        macrosPanel.add(macrosLabel);
        macrosPanel.add(adjustMacrosButton);

        // Panel para los indicadores de macronutrientes
        JPanel nutrientsPanel = new JPanel(new GridLayout(3, 2));
        nutrientsPanel.add(new JLabel("Proteinas"));
        JProgressBar proteinBar = new JProgressBar(0, 125);
        proteinBar.setValue(87);
        proteinBar.setStringPainted(true);
        nutrientsPanel.add(proteinBar);
        nutrientsPanel.add(new JLabel("Carbohidratos"));
        JProgressBar carbBar = new JProgressBar(0, 125);
        carbBar.setValue(87);
        carbBar.setStringPainted(true);
        nutrientsPanel.add(carbBar);
        nutrientsPanel.add(new JLabel("Grasas"));
        JProgressBar fatBar = new JProgressBar(0, 125);
        fatBar.setValue(87);
        fatBar.setStringPainted(true);
        nutrientsPanel.add(fatBar);

        // Panel de entrenamiento
        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));

        // Flechas para cambiar el grupo muscular
        JButton prevWorkoutButton = new JButton("<");
        JLabel workoutLabel = new JLabel(workouts[workoutIndex]);
        JButton nextWorkoutButton = new JButton(">");

        // Acciones para cambiar rutina
        prevWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutIndex = (workoutIndex - 1 + workouts.length) % workouts.length;
                workoutLabel.setText(workouts[workoutIndex]);
            }
        });
        nextWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workoutIndex = (workoutIndex + 1) % workouts.length;
                workoutLabel.setText(workouts[workoutIndex]);
            }
        });
        JPanel workoutSwitchPanel = new JPanel();
        workoutSwitchPanel.add(prevWorkoutButton);
        workoutSwitchPanel.add(workoutLabel);
        workoutSwitchPanel.add(nextWorkoutButton);

        JButton routineButton = new JButton("Ver rutina");
        JButton exercisesButton = new JButton("Ejercicios");
        workoutPanel.add(workoutSwitchPanel);
        workoutPanel.add(routineButton);
        workoutPanel.add(exercisesButton);

        // Panel de comidas
        JPanel mealsPanel = new JPanel();
        mealsPanel.setLayout(new BoxLayout(mealsPanel, BoxLayout.Y_AXIS));
        mealsPanel.setBorder(BorderFactory.createTitledBorder("Comidas"));
        String[] mealOptions = {"Desayuno", "Almuerzo", "Merienda", "Cena"};
        JComboBox<String> mealsComboBox = new JComboBox<>(mealOptions);
        JButton viewRecipeButton = new JButton("Ver receta");
        mealsPanel.add(mealsComboBox);
        mealsPanel.add(viewRecipeButton);

        // Añadir los paneles al panel principal
        mainPanel.add(datePanel);      // Panel de la fecha
        mainPanel.add(macrosPanel);    // Panel de los macros
        mainPanel.add(nutrientsPanel); // Panel de nutrientes
        mainPanel.add(workoutPanel);   // Panel de entrenamiento
        mainPanel.add(mealsPanel);     // Panel de comidas

        // Menú lateral
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem calendarItem = new JMenuItem("Calendario");
        JMenuItem progressItem = new JMenuItem("Mi Progreso");
        JMenuItem logoutItem = new JMenuItem("Salir de la cuenta");
        menu.add(calendarItem);
        menu.add(progressItem);
        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Añadir el panel principal al frame
        add(mainPanel, BorderLayout.CENTER);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controlador controlador = new Controlador();
                new VistaPrincipal(controlador);
            }
        });
    }
}
