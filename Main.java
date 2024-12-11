import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Obtener la fecha y hora actual
        Hora hora = new Hora();
        String fechaActual = hora.obtenerFechaActual();
        String horaActual = hora.obtenerHoraActual();

        // Verificar la información del usuario
        Verificacion verificacion = new Verificacion(null);
        
        String modelo = verificacion.getModelo();
        String numeroTelefono = verificacion.getNumeroTelefono();
        String nombreUsuario = verificacion.getNombreUsuario();
        int tamano = verificacion.getTamano();
        String procesador = verificacion.getProcesador(); // Obtener el procesador

        // Validar la información del usuario
        if (nombreUsuario == null || modelo == null || numeroTelefono == null || procesador == null) {
            JOptionPane.showMessageDialog(null, "Error: Información del usuario incompleta.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir si hay un error
        }

        // Crear la ventana principal
        JFrame inicioFrame = new JFrame("Pantalla de Inicio");
        inicioFrame.setSize(500, 700);
        inicioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioFrame.setLayout(new BorderLayout());

        // Agregar etiquetas de saludo y fecha/hora
        JLabel saludoLabel = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        inicioFrame.add(saludoLabel, BorderLayout.NORTH);

        JLabel fechaLabel = new JLabel("Fecha: " + fechaActual, SwingConstants.CENTER);
        JLabel horaLabel = new JLabel("Hora: " + horaActual, SwingConstants.CENTER);
        JPanel panelFechaHora = new JPanel(new GridLayout(2, 1));
        panelFechaHora.add(fechaLabel);
        panelFechaHora.add(horaLabel);
        inicioFrame.add(panelFechaHora, BorderLayout.CENTER);

        // Crear panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 2));

        // Botón para abrir la calculadora
        JButton botonCalculadora = new JButton("Abrir Calculadora");
        botonCalculadora.addActionListener(e -> new CalculadoraApp(inicioFrame));
        panelBotones.add(botonCalculadora);

        // Botón para abrir la mensajería
        JButton botonMensajeria = new JButton("Abrir Mensajes");
        botonMensajeria.addActionListener(e -> new MensajeriaApp(inicioFrame));
        panelBotones.add(botonMensajeria);

        // Crear una instancia de Informacion
        Informacion info = new Informacion(modelo, numeroTelefono, procesador, tamano);

        // Botón para abrir la aplicación de llamadas
        JButton abrirLlamadasButton = new JButton("Abrir Llamadas");
        abrirLlamadasButton.addActionListener(e -> {
            new LlamadasApp(inicioFrame, info); // Pasar ambos parámetros
            inicioFrame.setVisible(false); // Ocultar el JFrame principal
        });
        panelBotones.add(abrirLlamadasButton); // Agregar el botón al panel

        // Botón para mostrar información
        JButton botonInfo = new JButton("Información");
        botonInfo.addActionListener(e -> {
            // Incluir el procesador en el mensaje
            String mensaje = String.format(" Modelo: %s\nNúmero: %s\nTamaño: %d\nProcesador: %s", modelo, numeroTelefono, tamano, procesador);
            JOptionPane.showMessageDialog(inicioFrame, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
        });
        panelBotones.add(botonInfo);

        // Crear contenedor para los botones y el teclado
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(panelBotones, BorderLayout.NORTH);

        // Agregar teclado (asegúrate de que la clase Teclado esté implementada)
        Teclado teclado = new Teclado();
        panelContenedor.add(teclado, BorderLayout.SOUTH); 

        // Agregar el contenedor al marco principal
        inicioFrame.add(panelContenedor, BorderLayout.SOUTH);

        // Hacer visible la ventana
        inicioFrame.setVisible(true);
    }
}