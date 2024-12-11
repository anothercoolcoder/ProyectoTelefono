    import javax.swing.*;
    import java.awt.*;

    public class Main {
        public static void main(String[] args) {
            Hora hora = new Hora();
            String fechaActual = hora.obtenerFechaActual();
            String horaActual = hora.obtenerHoraActual();

            Verificacion verificacion = new Verificacion(null);
            
            String modelo = verificacion.getModelo();
            String numeroTelefono = verificacion.getNumeroTelefono();
            String nombreUsuario = verificacion.getNombreUsuario();
            int tamano = verificacion.getTamano();
            String procesador = verificacion.getProcesador(); 

            if (nombreUsuario == null || modelo == null || numeroTelefono == null || procesador == null) {
                JOptionPane.showMessageDialog(null, "Error: Información del usuario incompleta.", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

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
            panelBotones.setLayout(new GridLayout(5, 2)); 

            Informacion info = new Informacion(modelo, numeroTelefono, procesador, tamano);

            JButton botonCalculadora = new JButton("Abrir Calculadora");
            botonCalculadora.addActionListener(e -> new CalculadoraApp(inicioFrame));
            panelBotones.add(botonCalculadora);

            JButton botonMensajeria = new JButton("Abrir Mensajes");
            botonMensajeria.addActionListener(e -> new MensajeriaApp(inicioFrame, info)); 
            panelBotones.add(botonMensajeria);

            JButton abrirLlamadasButton = new JButton("Abrir Llamadas");
            abrirLlamadasButton.addActionListener(e -> {
                new LlamadasApp(inicioFrame, info); 
                inicioFrame.setVisible(false); 
            });
            panelBotones.add(abrirLlamadasButton); 

            JButton botonInfo = new JButton("Información");
            botonInfo.addActionListener(e -> {
                String mensaje = String.format(" Modelo: %s\nNúmero: %s\nTamaño: %d\nProcesador: %s", modelo, numeroTelefono, tamano, procesador);
                JOptionPane.showMessageDialog(inicioFrame, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
            });
            panelBotones.add(botonInfo);

            JButton botonColor = new JButton("Cambiar Color de Interfaz");
            botonColor.addActionListener(e -> {
                String[] colores = {"Rojo", "Verde", "Azul", "Amarillo", "Morado"};
                String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un color", "Color", JOptionPane.QUESTION_MESSAGE, null, colores, colores[0]);
            
                if (seleccion != null) {
                    Color nuevoColor;
                    switch (seleccion) {
                        case "Rojo":
                            nuevoColor = Color.RED;
                            break;
                        case "Verde":
    nuevoColor = Color.GREEN;
                            break;
                        case "Azul":
                            nuevoColor = Color.BLUE;
                            break;
                        case "Amarillo":
                            nuevoColor = Color.YELLOW;
                            break;
                        case "Morado":
                            nuevoColor = Color.MAGENTA;
                            break;
                        default:
                            nuevoColor = Color.WHITE; 
                    }
            
                    inicioFrame.getContentPane().setBackground(nuevoColor);
            
                    actualizarColorComponentes(inicioFrame.getContentPane(), nuevoColor);
                }
            });
            panelBotones.add(botonColor); 

            JButton botonSnake = new JButton("Abrir Snake");
            botonSnake.addActionListener(e -> {
                inicioFrame.setVisible(false); 
                new Snake(inicioFrame); 
            });
            panelBotones.add(botonSnake); 

            JPanel panelContenedor = new JPanel();
            panelContenedor.setLayout(new BorderLayout());
            panelContenedor.add(panelBotones, BorderLayout.NORTH);

            Teclado teclado = new Teclado();
            panelContenedor.add(teclado, BorderLayout.SOUTH); 

            inicioFrame.add(panelContenedor, BorderLayout.SOUTH);

            inicioFrame.setVisible(true);
        }

        private static void actualizarColorComponentes(Container contenedor, Color nuevoColor) {
            for (Component componente : contenedor.getComponents()) {
                if (componente instanceof JPanel || componente instanceof JComponent) {
                    componente.setBackground(nuevoColor);
            
                    Color textoColor = (nuevoColor == Color.YELLOW || nuevoColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
                    if (componente instanceof JLabel) {
                        ((JLabel) componente).setForeground(textoColor);
                    } else if (componente instanceof JButton) {
                        ((JButton) componente).setForeground(textoColor);
                    }
            
                    if (componente instanceof Container) {
                        actualizarColorComponentes((Container) componente, nuevoColor);
                    }
                }
            }
        }
    }