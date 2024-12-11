import javax.swing.*;
import java.awt.*;

public class Verificacion {
    private String modelo;
    private String numeroTelefono;
    private String nombreUsuario;
    private int tamano; 

    public Verificacion(JFrame parentFrame) {
        JPanel panel = new JPanel(new GridLayout(4, 2)); 

        JTextField nombreField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField numeroField = new JTextField();
        JTextField tamanoField = new JTextField();

        panel.add(new JLabel("Nombre: "));
        panel.add(nombreField);
        panel.add(new JLabel("Modelo del celular: "));
        panel.add(modeloField);
        panel.add(new JLabel("Número de teléfono: "));
        panel.add(numeroField);
        panel.add(new JLabel("Tamaño: "));
        panel.add(tamanoField);

        int option = JOptionPane.showConfirmDialog(parentFrame, panel, "Ingrese su información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            nombreUsuario = nombreField.getText().trim();
            modelo = modeloField.getText().trim();

            // Validación del número de teléfono
            while (true) {
                numeroTelefono = numeroField.getText().trim();
                if (numeroTelefono.matches("\\d{10}")) { 
                    break; 
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Por favor, ingrese un número de teléfono válido (10 dígitos).", "Error", JOptionPane.ERROR_MESSAGE);
                    numeroTelefono = null; 
                    option = JOptionPane.showConfirmDialog(parentFrame, panel, "Ingrese su información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option != JOptionPane.OK_OPTION) {
                        nombreUsuario = null;
                        modelo = null;
                        numeroTelefono = null;
                        tamano = 0;
                        return; 
                    }
                }
            }

            // Validación del tamaño
            while (true) {
                try {
                    tamano = Integer.parseInt(tamanoField.getText().trim());
                    break; // Salir del bucle si se ingresa un tamaño válido
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(parentFrame, "Por favor, ingrese un tamaño válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
                    tamanoField.setText(""); // Limpiar el campo para que el usuario vuelva a intentarlo
                    option = JOptionPane.showConfirmDialog(parentFrame, panel, "Ingrese su información", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option != JOptionPane.OK_OPTION) {
                        nombreUsuario = null;
                        modelo = null;
                        numeroTelefono = null;
                        tamano = 0;
                        return; 
                    }
                }
            }
        } else {
            nombreUsuario = null;
            modelo = null;
            numeroTelefono = null;
            tamano = 0;
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

    public int getTamano() {
        return tamano;
    }
}