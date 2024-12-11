import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MensajeriaApp {
    private JFrame frame;
    private JTextField mensajeField;
    private Informacion info; // Instancia de la clase Informacion

    public MensajeriaApp(JFrame inicioFrame, Informacion info) {
        this.info = info; // Recibir la instancia de Informacion
        frame = new JFrame("Aplicación de Mensajería");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mensajeField = new JTextField();
        frame.add(mensajeField, BorderLayout.CENTER);

        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });
        frame.add(enviarButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void enviarMensaje() {
        String mensaje = mensajeField.getText();
        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un mensaje.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí puedes agregar la lógica para enviar el mensaje
        // Por ejemplo, mostrar un mensaje de confirmación
        JOptionPane.showMessageDialog(frame, "Mensaje enviado: " + mensaje);

        // Reducir el tamaño en la clase Informacion
        info.reducirTamano(1); // Reducir el tamaño en 1
        mensajeField.setText(""); // Limpiar el campo de texto
    }
}