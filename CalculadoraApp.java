import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CalculadoraApp {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2, resultado;
    private String operador;
    private JFrame inicioFrame; 
    private DecimalFormat decimalFormat; // Para formatear los números

    public CalculadoraApp(JFrame inicioFrame) {
        this.inicioFrame = inicioFrame; 
        frame = new JFrame("Calculadora");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4)); // Cambiado a 5 filas para incluir los nuevos botones

        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            "%", "^", "Regresar" // Añadido el botón de porcentaje y el botón de elevar
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.addActionListener(new BotonClickListener());
            panel.add(boton);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Inicializar el formato decimal
        decimalFormat = new DecimalFormat("#,##0.##"); // Formato para mostrar con separador de miles y hasta dos decimales
    }

    private class BotonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
                textField.setText(textField.getText() + comando);
            } else if (comando.equals("C")) {
                textField.setText("");
            } else if (comando.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error: División por cero", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    case "^": // Manejar la operación de elevar
                        resultado = Math.pow(num1, num2);
                        break;
                    case "%": // Manejar la operación de porcentaje
                        resultado = (num1 * num2) / 100; // num1 es el número y num2 es el porcentaje
                        break;
                }
                // Formatear el resultado antes de mostrarlo
                textField.setText(decimalFormat.format(resultado));
            } else if (comando.equals("Regresar")) {
                frame.dispose(); 
                inicioFrame.setVisible(true); 
            } else {
                num1 = Double.parseDouble(textField.getText());
                operador = comando;
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        JFrame inicioFrame = new JFrame("Pantalla de Inicio");
        inicioFrame.setSize(400, 300);
        inicioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioFrame.setVisible(true);
        
        new CalculadoraApp(inicioFrame); 
    }
}