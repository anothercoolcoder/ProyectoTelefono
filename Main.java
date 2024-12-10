import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Hora hora = new Hora();
        String fechaActual = hora.obtenerFechaActual();
        String horaActual = hora.obtenerHoraActual();

        JFrame parentFrame = new JFrame();
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setVisible(false); 

        Verificacion verificacion = new Verificacion(parentFrame);
        
        String modelo = verificacion.getModelo();
        String numeroTelefono = verificacion.getNumeroTelefono();
        String nombreUsuario = verificacion.getNombreUsuario();

        JFrame inicioFrame = new JFrame("Pantalla de Inicio");
        inicioFrame.setSize(500, 700);
        inicioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioFrame.setLayout(new BorderLayout());

        JLabel saludoLabel = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        inicioFrame.add(saludoLabel, BorderLayout.NORTH);

        JLabel fechaLabel = new JLabel("Fecha: " + fechaActual, SwingConstants.CENTER);
        JLabel horaLabel = new JLabel("Hora: " + horaActual, SwingConstants.CENTER);
        JPanel panelFechaHora = new JPanel(new GridLayout(2, 1));
        panelFechaHora.add(fechaLabel);
        panelFechaHora.add(horaLabel);
        inicioFrame.add(panelFechaHora, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 2));

        JButton botonCalculadora = new JButton("Abrir Calculadora");
        botonCalculadora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalculadoraApp(inicioFrame);
            }
        });
        panelBotones.add(botonCalculadora);

        JButton botonMensajeria = new JButton("Abrir Mensajes");
        botonMensajeria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MensajeriaApp(inicioFrame);
            }
        });
        panelBotones.add(botonMensajeria);

        JButton botonLlamada = new JButton("Abrir Llamadas");
        botonLlamada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LlamadasApp(inicioFrame);
            }
        });
        panelBotones.add(botonLlamada);

        JButton botonInfo = new JButton("Información");
        botonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = String.format("Modelo: %s\nNúmero: %s", modelo, numeroTelefono);
                JOptionPane.showMessageDialog(inicioFrame, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelBotones.add(botonInfo);

        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(panelBotones, BorderLayout.NORTH);

        Teclado teclado = new Teclado();
        panelContenedor.add(teclado, BorderLayout.SOUTH); 

        inicioFrame.add(panelContenedor, BorderLayout.SOUTH);

        inicioFrame.setVisible(true);
    }
}