import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Map<String, String> indicativos; 
    private Timer timer; 
    private long startTime; 
    private JButton colgarButton; 
    private JButton llamarButton; 
    private int llamadasDisponibles; 
    private Informacion info; 

    public LlamadasApp(JFrame inicioFrame,Informacion info) {
        this.inicioFrame = inicioFrame; 
        this.info = new Informacion("Modelo X", "123456789", "Snapdragon 888", 100); 
        frame = new JFrame("Aplicación de Llamadas");
        frame.setSize(400, 400); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
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

        llamarButton = new JButton("Llamar");
        llamarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLlamada();
            }
        });
        panel.add(llamarButton);

        colgarButton = new JButton("Colgar");
        colgarButton.setEnabled(false); 
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

        inicializarIndicativos();
        
        llamadasDisponibles = 5; 
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
        String numero = numeroField.getText().replaceAll("\\s+", ""); 
    
        if (!nombre.isEmpty() && !numero.isEmpty()) {
            String indicativo = null;
    
            if (numero.startsWith("+")) {
                indicativo = numero.substring(0, 3); 
            } else if (numero.startsWith("57") || numero.startsWith("1") || numero.startsWith("34") || numero.startsWith("52")) {
                indicativo = numero.substring(0, 2); 
            } else {
                JOptionPane.showMessageDialog(frame, "Número no válido. Asegúrate de incluir el indicativo correcto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            if (indicativos.containsKey(indicativo)) {
                textArea.append("Llamando a " + nombre + " (" + numero + ")\n");
                colgarButton.setEnabled(true);
                llamarButton.setEnabled(false);
                startTime = System.currentTimeMillis();
                iniciarTemporizador();
            } else {
                JOptionPane.showMessageDialog(frame, "Número no válido. Asegúrate de incluir el indicativo correcto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarTemporizador() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - startTime;
                String tiempo = new SimpleDateFormat("mm:ss").format(new Date(elapsedTime));
                textArea.append("Duración de la llamada: " + tiempo + "\n");
            }
        }, 0, 1000);
    }

    private void colgarLlamada() {
        textArea.append("Llamada finalizada.\n");
        colgarButton.setEnabled(false);
        llamarButton.setEnabled(true);
        if (timer != null) {
            timer.cancel();
        }
        llamadasDisponibles--;
        if (llamadasDisponibles <= 0) {
            JOptionPane.showMessageDialog(frame, "No hay llamadas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            llamarButton.setEnabled(false);
        }
    }
}