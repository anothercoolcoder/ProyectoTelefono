import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Informacion {
    private String modelo;
    private String numeroTelefono;
    private JFrame inicioFrame; 

    public Informacion(String modelo, String numeroTelefono, JFrame inicioFrame) {
        this.modelo = modelo;
        this.numeroTelefono = numeroTelefono;
        this.inicioFrame = inicioFrame; 

        JFrame infoFrame = new JFrame("Información del Celular");
        infoFrame.setSize(300, 200);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        infoFrame.setLayout(null);

        JLabel modeloLabel = new JLabel("Modelo: " + modelo);
        modeloLabel.setBounds(10, 10, 250, 25);
        infoFrame.add(modeloLabel);

        JLabel numeroLabel = new JLabel("Número: " + numeroTelefono);
        numeroLabel.setBounds(10, 40, 250, 25);
        infoFrame.add(numeroLabel);

        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.setBounds(10, 80, 100, 25);
        infoFrame.add(botonRegresar);

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.dispose();
                inicioFrame.setVisible(true); 
            }
        });

        infoFrame.setVisible(true);
    }
}