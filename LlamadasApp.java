import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
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

    public LlamadasApp(JFrame inicioFrame) {
        this.inicioFrame = inicioFrame; 
        frame = new JFrame("Aplicación de Llamadas");
        frame.setSize(400, 400); // Ajustar el tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); 

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
                if (Character.isDigit(c)) {
                    reproducirSonidoTecla(); 
                }
            }
        });

        JButton llamarButton = new JButton("Llamar");
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
        
        // Mostrar los indicativos disponibles
        mostrarIndicativos();
    }

    private void mostrarIndicativos() {
        StringBuilder sb = new StringBuilder("Indicativos disponibles:\n");
        for (String indicativo : indicativos.keySet()) {
            sb.append(indicativo).append(" - ").append(indicativos.get(indicativo)).append("\n");
        }
        JOptionPane.showMessageDialog(frame, sb.toString(), "Indicativos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void inicializarIndicativos() {
        indicativos = new HashMap<>();
        indicativos.put("+57", "Colombia");
        indicativos.put("57", "Colombia");
        indicativos.put("+1", "Estados Unidos / Canadá");
        indicativos.put("1", "Estados Unidos / Canadá");
        indicativos.put("+34", "España");
        indicativos.put("34", "España");
        indicativos.put("+52", "México");
        indicativos.put("52", "México");
    }

    private void realizarLlamada() {
        String nombre = nombreField.getText();
        String numero = numeroField.getText().replaceAll("\\s+", ""); // Eliminar espacios

        if (!nombre.isEmpty() && ! numero.isEmpty()) {
            // Verifica el formato del número, permitiendo indicativo con o sin '+'
            String indicativo = numero.startsWith("+") ? numero.substring(0, 3) : numero.substring(0, 2);
            String pais = indicativos.get(indicativo); // Busca el país por el indicativo

            if (pais != null) {
                Llamada llamada = new Llamada(nombre, numero);
                textArea.append(llamada.toString() + " (Número de " + pais + ")\n");
                nombreField.setText("");
                numeroField.setText("");

                // Reproducir sonido al iniciar la llamada
                reproducirSonidoLlamada();

                // Iniciar el temporizador para la duración de la llamada
                startTime = System.currentTimeMillis();
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        long seconds = (elapsedTime / 1000) % 60;
                        long minutes = (elapsedTime / (1000 * 60)) % 60;
                        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
                        textArea.append("Duración de la llamada: " + hours + " horas, " + minutes + " minutos, " + seconds + " segundos.\n");
                    }
                }, 0, 1000); // Actualizar cada segundo

                colgarButton.setEnabled(true); // Habilitar el botón de colgar
                frame.setSize(400, 400); // Ajustar el tamaño de la ventana
            } else {
                JOptionPane.showMessageDialog(frame, "Indicativo no reconocido. Por favor, ingresa un indicativo válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, ingresa el nombre y el número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void colgarLlamada() {
        if (timer != null) {
            timer.cancel(); // Detener el temporizador
        }
        colgarButton.setEnabled(false); // Deshabilitar el botón de colgar
        textArea.append("La llamada ha sido colgada.\n");
        frame.setSize(400, 300); // Restablecer el tamaño de la ventana
    }

    private void reproducirSonidoLlamada() {
        try {
            File sonido = new File("C://Users//andre//Downloads//SonidoLlamada.wav"); // Cambia la ruta al sonido de llamada
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void reproducirSonidoTecla() {
        try {
            File sonido = new File("C://Users//andre//Downloads//SonidoTecla.wav"); 
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame inicioFrame = new JFrame("Pantalla de Inicio");
        inicioFrame.setSize(400, 300);
        inicioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioFrame.setVisible(true);
        
        new LlamadasApp(inicioFrame); 
    }

    class Llamada {
        private String nombre;
        private String numero;
        private String hora;

        public Llamada(String nombre, String numero) {
            this.nombre = nombre;
            this.numero = numero;
            this.hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
        }

        @Override
        public String toString() {
            return "[" + hora + "] Llamada a " + nombre + " (Número: " + numero + ")";
        }
    }
}