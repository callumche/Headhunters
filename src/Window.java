import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Window extends JPanel {
    Naufil n1 = new Naufil();
    CharacterSelect charMenu = new CharacterSelect();
    public static final int resX = 1920, resY = 1080;
    private int stage = 0;

    public Window(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                n1.keyReleased(e);
            } //problem: have to know what classes to send keypresses to
            @Override
            public void keyPressed(KeyEvent e) {
                charMenu.keyPressed(e);
                n1.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        charMenu.paint(g2d);
        n1.paint(g2d);

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("HeadHunters");
        Window win = new Window();
        frame.add(win);
        frame.setSize(resX, resY);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            Thread.sleep(16);
            //Thread.sleep(60);
            frame.repaint();
        }
    }
}