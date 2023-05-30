import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class SelectScreen {
    private int startState = 0; //converted boolean into start phase (0 = splash, 1 = char select, 2 = arena)
    private boolean isInitialized = false;
    private BufferedImage splash = null;

    public void init() {
        try {
            splash = ImageIO.read(new File("res\\TitleSplash.png"));
        } catch (IOException e) {
            System.out.println("Missing Naufil Image: " + e);
        }
        isInitialized = true;
    }
    public void paint (Graphics2D g2d){
        if (!isInitialized) {
            init();
        }
        if (startState > 2) {
            Window.endStart();
        }
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, Window.resX, Window.resY);
        if (startState == 0) {
            g2d.drawImage(splash, 0, 0, null);
        }

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            startState++;
        }
    }
}
