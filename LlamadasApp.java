import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LlamadasApp {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField nombreField;
    private JTextField numeroField;
    private JFrame inicioFrame; 
    private Map<String, String> indicativos; // Mapa para los indicativos
    private Timer timer; // Temporizador para la duración de la llamada
    private long startTime; // Tiempo de inicio de la llamada
    private JButton colgarButton; // Botón para colgar la llamada
    private JButton llamarButton; // Botón para llamar
    private int llamadasDisponibles; // Contador de llamadas disponibles
    private Informacion info; // Instancia de la clase Informacion

    public LlamadasApp(JFrame inicioFrame, Informacion info) {
        this.inicioFrame = inicioFrame; 
        this.info = info; // Recibir la instancia de Informacion
        frame = new JFrame("Aplicación de Llamadas");
        frame.setSize(400, 400); // Ajustar el tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // Cambiar a 5 filas para los campos y botones

        panel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Número:"));
        numeroField = new JTextField();
        panel.add(numeroField);

        numeroField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // No permitir caracteres no numéricos
                }
            }
        });

        llamarButton = new JButton("Llamar");
        llamarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLlamada();
            }
        });
        panel.add(llamarButton);

        colgarButton = new JButton("Colgar");
        colgarButton.setEnabled(false); // Deshabilitar el botón al inicio
        colgarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colgarLlamada();
            }
        });
        panel.add(colgarButton);

        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                inicioFrame.setVisible(true); 
            }
        });
        panel.add(regresarButton); 

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Inicializar el mapa de indicativos
        inicializarIndicativos();
        
        // Inicializar el contador de llamadas disponibles
        llamadasDisponibles = 5; // Por ejemplo, permitir 5 llamadas
    }

    private void inicializarIndicativos() {
        indicativos = new HashMap<>();
        indicativos.put("+57", "Colombia");
        indicativos.put("57", "Colombia");
        indicativos.put("+1", "Estados Unidos / Canadá");
        indicativos.put("1", "Estados Unidos / Canadá");
        indicativos.put("+34", "España");
        indicativos.put("34", "España");
        // Agregar más indicativos según sea necesario
    }

    private void realizarLlamada() {
        String nombre = nombreField.getText();
        String numero = numeroField.getText();

        if (nombre.isEmpty() || numero.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un nombre y un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si hay llamadas disponibles
        if (llamadasDisponibles > 0) {
            textArea.append("L lamando a " + nombre + " (" + numero + ")\n");
            llamadasDisponibles--;
            info.reducirTamano(1); // Reducir el tamaño de almacenamiento en -1
            colgarButton.setEnabled(true);
            llamarButton.setEnabled(false);

            // Iniciar temporizador para la duración de la llamada
            startTime = System.currentTimeMillis();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    String formattedTime = new SimpleDateFormat("mm:ss").format(new Date(elapsedTime));
                    textArea.append("Duración de la llamada: " + formattedTime + "\n");
                }
            }, 0, 1000); // Actualizar cada segundo
        } else {
            JOptionPane.showMessageDialog(frame, "No hay llamadas disponibles.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void colgarLlamada() {
        textArea.append("Llamada finalizada.\n");
        colgarButton.setEnabled(false);
        llamarButton.setEnabled(true);
        if (timer != null) {
            timer.cancel(); // Detener el temporizador
        }
    }
}