import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Informacion {
    private String modelo;
    private String numeroTelefono;
    private String procesador; // Nueva variable para el procesador
    private int tamano; 
    private JLabel tamanoLabel; // Etiqueta para mostrar el tamaño

    public Informacion(String modelo, String numeroTelefono, String procesador, int tamano) {
        this.modelo = modelo;
        this.numeroTelefono = numeroTelefono;
        this.procesador = procesador; // Inicializar el procesador
        this.tamano = Math.max(tamano, 0); // Asegurarse de que el tamaño no sea negativo
    }

    public void mostrarInformacion() { // Cambiar a public
        JFrame infoFrame = new JFrame("Información del Celular");
        infoFrame.setSize(300, 200);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        infoFrame.setLayout(new GridLayout(5, 1)); // Cambiar a 5 filas para incluir el procesador

        JLabel modeloLabel = new JLabel("Modelo: " + modelo);
        JLabel numeroLabel = new JLabel("Número: " + numeroTelefono);
        tamanoLabel = new JLabel("Tamaño: " + tamano); // Inicializar la etiqueta del tamaño
        JLabel procesadorLabel = new JLabel("Procesador: " + procesador); // Etiqueta para el procesador

        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.dispose(); 
            }
        });

        infoFrame.add(modeloLabel);
        infoFrame.add(numeroLabel);
        infoFrame.add(tamanoLabel);
        infoFrame.add(procesadorLabel); // Agregar la etiqueta del procesador
        infoFrame.add(botonRegresar);

        infoFrame.setLocationRelativeTo(null); 
        infoFrame.setVisible(true);
    }

    public void reducirTamano(int cantidad) {
        if (tamano - cantidad < 0) {
            JOptionPane.showMessageDialog(null, "El tamaño no puede ser negativo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            this.tamano -= cantidad; // Reducir el tamaño
            actualizarTamanoLabel(); // Actualizar la etiqueta del tamaño
        }
    }

    private void actualizarTamanoLabel() {
        tamanoLabel.setText("Tamaño: " + tamano); // Actualizar el texto de la etiqueta
    }

    public int getTamano() {
        return tamano; // Método para obtener el tamaño actual
    }
}