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

public class MensajeriaApp {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField nombreField;
    private JTextField numeroField;
    private JTextField mensajeField;
    private JFrame inicioFrame; 

    public MensajeriaApp(JFrame inicioFrame) {
        this.inicioFrame = inicioFrame; 
        frame = new JFrame("Aplicación de Mensajería");
        frame.setSize(400, 300);
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

        panel.add(new JLabel("Mensaje:"));
        mensajeField = new JTextField();
        panel.add(mensajeField);

        // Reproducir sonido al escribir en el campo de mensaje
        mensajeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                reproducirSonidoTecla(); 
            }
        });

        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        panel.add(enviarButton);

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

    private void enviarMensaje() {
        String nombre = nombreField.getText();
        String numero = numeroField.getText();
        String mensaje = mensajeField.getText();

        if (!nombre.isEmpty() && !numero.isEmpty() && !mensaje.isEmpty()) {
            if (numero.matches("\\d+")) { 
                String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
                textArea.append("[" + hora + "] Mensaje a " + nombre + " (Número: " + numero + "): " + mensaje + "\n");
                nombreField.setText("");
                numeroField.setText("");
                mensajeField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "El número debe contener solo dígitos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, ingresa el nombre, el número y el mensaje.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reproducirSonidoTecla() {
        try {
            File sonido = new File("C://Users//andre//Downloads//Hola.wav"); 
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sonido);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            
            // Esperar a que el clip termine de reproducirse
            while (clip.isRunning()) {
                Thread.sleep(100); // Espera 100 ms
            }
            
            clip.close(); // Cierra el clip después de que se haya reproducido
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace(); // Asegúrate de que esto esté aquí para manejar excepciones
        }
    }

    public static void main(String[] args) {
        JFrame inicioFrame = new JFrame("Pantalla de Inicio");
        inicioFrame.setSize(400, 300);
        inicioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioFrame.setVisible(true);
        
        new MensajeriaApp(inicioFrame); 
    }
}