import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JPanel {
    Entity e1 = new Entity();

    public Window(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                e1.keyReleased(e);

            }
            @Override
            public void keyPressed(KeyEvent e) {
                e1.keyPressed(e);

            }
        });
        setFocusable(true);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        e1.paint(g2d);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("HeadHunters");
        Window win = new Window();
        frame.add(win);
        frame.setSize(1020, 640);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            Thread.sleep(16);
            frame.repaint();
        }
    }
}