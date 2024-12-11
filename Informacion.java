import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Informacion {
    private String modelo;
    private String numeroTelefono;
    private String procesador; 
    private int tamano; 
    private JLabel tamanoLabel; 
    public Informacion(String modelo, String numeroTelefono, String procesador, int tamano) {
        this.modelo = modelo;
        this.numeroTelefono = numeroTelefono;
        this.procesador = procesador; 
        this.tamano = Math.max(tamano, 0); 
    }

    public void mostrarInformacion() { 
        JFrame infoFrame = new JFrame("Información del Celular");
        infoFrame.setSize(300, 200);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        infoFrame.setLayout(new GridLayout(5, 1)); 

        JLabel modeloLabel = new JLabel("Modelo: " + modelo);
        JLabel numeroLabel = new JLabel("Número: " + numeroTelefono);
        tamanoLabel = new JLabel("Tamaño: " + tamano); 
        JLabel procesadorLabel = new JLabel("Procesador: " + procesador); 
        
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
        infoFrame.add(procesadorLabel); 
        infoFrame.add(botonRegresar);

        infoFrame.setLocationRelativeTo(null); 
        infoFrame.setVisible(true);
    }

    public void reducirTamano(int cantidad) {
        if (tamano - cantidad < 0) {
            JOptionPane.showMessageDialog(null, "El tamaño no puede ser negativo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            this.tamano -= cantidad; 
            actualizarTamanoLabel(); 
        }
    }

    private void actualizarTamanoLabel() {
        tamanoLabel.setText("Tamaño: " + tamano); 
    }

    public int getTamano() {
        return tamano; 
    }
}