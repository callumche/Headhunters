import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class SelectScreen {
    private int startState = 0; //converted boolean into start phase (0 = splash, 1 = char select, 2 = arena)
    private boolean isInitialized = false;
    private BufferedImage splash = null, julian = null, callum = null, naufil = null;

    public void init() {
        try {
            splash = ImageIO.read(new File("res\\TitleSplash.png"));
            julian = ImageIO.read(new File("res\\Sprites\\JulianSelect.PNG"));
            callum = ImageIO.read(new File("res\\Sprites\\CallumSelect.PNG"));
            naufil = ImageIO.read(new File("res\\Sprites\\NaufilSelect.PNG"));
        } catch (IOException e) {
            System.out.println("Missing Loading Screen Image: " + e);
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
        if (startState == 0) { //first phase, splash
            g2d.drawImage(splash, 0, 0, null);
            if (Window.getTick() % 100 > 30) {
                g2d.drawString("Press SPACE to Scrum:", 600 , 500);
            }
        }
        if (startState == 1) { //second phase, character select
            g2d.setColor(Color.darkGray);
            g2d.fillRect(540, 440, 200, 200);
            g2d.drawImage(julian, 540, 440, null);
            g2d.fillRect(860, 440, 200, 200);
            g2d.drawImage(callum, 860, 440, null);
            g2d.fillRect(1180, 440, 200, 200);
            g2d.drawImage(naufil, 1180, 440, null);
        }

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            startState++;
        }
    }
}
