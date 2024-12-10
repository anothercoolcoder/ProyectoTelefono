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

public class LlamadasApp {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField nombreField;
    private JTextField numeroField;
    private JFrame inicioFrame; 

    public LlamadasApp(JFrame inicioFrame) {
        this.inicioFrame = inicioFrame; 
        frame = new JFrame("Aplicación de Llamadas");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); 

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
    }

    private void realizarLlamada() {
        String nombre = nombreField.getText();
        String numero = numeroField.getText();

        if (!nombre.isEmpty() && !numero.isEmpty()) {
            if (numero.matches("\\d+")) { 
                Llamada llamada = new Llamada(nombre, numero);
                textArea.append(llamada.toString() + "\n");
                nombreField.setText("");
                numeroField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "El número debe contener solo dígitos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, ingresa el nombre y el número.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reproducirSonidoTecla() {
        try {
            File sonido = new File("C://Users//andre//Downloads//Sonido.wav"); 
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