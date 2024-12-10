import javax.swing.*;

public class Verificacion {
    private String modelo;
    private String numeroTelefono;
    private String nombreUsuario; 

    public Verificacion(JFrame parentFrame) {
        nombreUsuario = JOptionPane.showInputDialog(parentFrame, "Por favor, ingresa tu nombre:");
        modelo = JOptionPane.showInputDialog(parentFrame, "Por favor, ingresa el modelo del celular:");
        numeroTelefono = JOptionPane.showInputDialog(parentFrame, "Por favor, ingresa tu número de teléfono:");
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