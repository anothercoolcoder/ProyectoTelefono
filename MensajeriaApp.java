import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MensajeriaApp {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
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

        textField = new JTextField();
        frame.add(textField, BorderLayout.SOUTH);

        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        frame.add(enviarButton, BorderLayout.EAST);

        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
                inicioFrame.setVisible(true); 
            }
        });
        frame.add(regresarButton, BorderLayout.WEST); 

        frame.setVisible(true);
    }

    private void enviarMensaje() {
        String mensaje = textField.getText();
        if (!mensaje.isEmpty()) {
            String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
            textArea.append("[" + hora + "] " + mensaje + "\n");
            textField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Por favor, escribe un mensaje.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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