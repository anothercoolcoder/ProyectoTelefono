import javax.swing.*;
import java.awt.*;

public class Verificacion {
    private String modelo;
    private String numeroTelefono;
    private String nombreUsuario; 

    public Verificacion(JFrame parentFrame) {
        JPanel panel = new JPanel(new GridLayout(3, 2)); // 3 filas, 2 columnas

        JTextField nombreField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField numeroField = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Modelo del celular:"));
        panel.add(modeloField);
        panel.add(new JLabel("Número de teléfono:"));
        panel.add(numeroField);

        int option = JOptionPane.showConfirmDialog(parentFrame, panel, "Ingrese su información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nombreUsuario = nombreField.getText();
            modelo = modeloField.getText();
            numeroTelefono = numeroField.getText();
        } else {
            nombreUsuario = null;
            modelo = null;
            numeroTelefono = null;
        }
    }

    public String getModelo() {
        return modelo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
}