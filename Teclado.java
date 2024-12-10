import javax.swing.*;
import javax.swing.border.EmptyBorder; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teclado extends JPanel {
    public Teclado() {
        setBorder(new EmptyBorder(5, 5, 5, 5));

        setLayout(new GridLayout(5, 3)); 

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(new ButtonClickListener());
            button.setPreferredSize(new Dimension(60, 60));
            add(button);
        }

        JButton button0 = new JButton("0");
        button0.addActionListener(new ButtonClickListener());
        button0.setPreferredSize(new Dimension(60, 60)); 
        add(button0);

        JButton buttonClear = new JButton("Borrar");
        buttonClear.addActionListener(new ButtonClickListener());
        buttonClear.setPreferredSize(new Dimension(60, 60)); 
        add(buttonClear);

        JButton buttonOff = new JButton("Apagar");
        buttonOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        buttonOff.setPreferredSize(new Dimension(60, 60)); 
        add(buttonOff); 

        for (Component component : getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setPreferredSize(new Dimension(60, 60)); 
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            System.out.println("Botón presionado: " + buttonText);
        }
    }
}