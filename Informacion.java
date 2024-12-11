import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Informacion {
    private String modelo;
    private String numeroTelefono;
    private String procesador; // Nueva variable para el procesador
    private JFrame inicioFrame;
    private int tamano; 

    public Informacion(String modelo, String numeroTelefono, String procesador, JFrame inicioFrame, int tamano) {
        this.modelo = modelo;
        this.numeroTelefono = numeroTelefono;
        this.procesador = procesador; // Inicializar el procesador
        this.inicioFrame = inicioFrame; 
        this.tamano = tamano;

        JFrame infoFrame = new JFrame("Información del Celular");
        infoFrame.setSize(300, 200);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        infoFrame.setLayout(new GridLayout(5, 1)); // Cambiar a 5 filas para incluir el procesador

        JLabel modeloLabel = new JLabel("Modelo: " + modelo);
        JLabel numeroLabel = new JLabel("Número: " + numeroTelefono);
        JLabel tamanoLabel = new JLabel("Tamaño: " + tamano);
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

        infoFrame.setLocationRelativeTo(inicioFrame); 
        infoFrame.setVisible(true);
    }
}