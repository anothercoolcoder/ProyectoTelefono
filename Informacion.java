import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Informacion {
    private String modelo;
    private String numeroTelefono;
    private JFrame inicioFrame;
    private int tamano; 

    public Informacion(String modelo, String numeroTelefono, JFrame inicioFrame, int tamano) {
        this.modelo = modelo;
        this.numeroTelefono = numeroTelefono;
        this.inicioFrame = inicioFrame; 
        this.tamano = tamano;

        JFrame infoFrame = new JFrame("Información del Celular");
        infoFrame.setSize(300, 200);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        infoFrame.setLayout(new GridLayout(4, 1)); 

        JLabel modeloLabel = new JLabel("Modelo: " + modelo);
        JLabel numeroLabel = new JLabel("Número: " + numeroTelefono);
        JLabel tamanoLabel = new JLabel("Tamaño: " + tamano);

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
        infoFrame.add(botonRegresar);

        infoFrame.setLocationRelativeTo(inicioFrame); 
        infoFrame.setVisible(true);
    }
}