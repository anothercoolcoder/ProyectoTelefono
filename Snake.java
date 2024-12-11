import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private final int DOT_SIZE = 10;
    private final int DELAY = 100;

    private ArrayList<Point> snake;
    private Point food;
    private char direction;
    private boolean running;
    private JButton backButton; 

    public Snake(JFrame parentFrame) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 'S') direction = 'W';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'W') direction = 'S';
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 'D') direction = 'A';
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'A') direction = 'D';
                        break;
                }
            }
        });

        snake = new ArrayList<>();
        direction = 'D';
        running = false;

        Timer timer = new Timer(DELAY, this);
        timer.start();
        startGame();

        backButton = new JButton("Regresar");
        backButton.setBounds(WIDTH / 2 - 50, HEIGHT - 50, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true); 
                ((JFrame) SwingUtilities.getWindowAncestor(Snake.this)).dispose(); 
            }
        });
        this.setLayout(null); 
        this.add(backButton);
    }

    private void startGame() {
        snake.clear();
        snake.add(new Point(5, 5));
        spawnFood();
        running = true;
    }

    private void spawnFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / DOT_SIZE);
        int y = rand.nextInt(HEIGHT / DOT_SIZE);
        food = new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            g.setColor(Color.RED);
            g.fillRect(food.x * DOT_SIZE, food.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);

            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x * DOT_SIZE, p.y * DOT_SIZE, DOT_SIZE, DOT_SIZE);
            }
        } else {
            showGameOver(g);
        }
    }

    private void showGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Game Over", WIDTH / 2 - 50, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
            checkFood();
            repaint();
        }
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case 'W':
                newHead.y--;
                break;
            case 'S':
                newHead.y++;
                break;
            case 'A':
 newHead.x--;
                break;
            case 'D':
                newHead.x++;
                break;
        }

        snake.add(0, newHead);
        if (running) {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH / DOT_SIZE || head.y < 0 || head.y >= HEIGHT / DOT_SIZE || snake.subList(1, snake.size()).contains(head)) {
            running = false;
        }
    }

    private void checkFood() {
        if (snake.get(0).equals(food)) {
            snake.add(new Point(food)); 
            spawnFood(); 
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        Snake snakeGame = new Snake(frame);
        frame.add(snakeGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}