import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class SelectScreen {
    private int startState = 0; //converted boolean into start phase (0 = splash, 1 = char and arena select, 2 = arena)
    private boolean isInitialized = false;
    private BufferedImage splash = null, julian = null, callum = null, naufil = null;
    private Font f = null;
    private int selector = 0;
    public void init() {
        f = new Font("Comic Sans MS", Font.BOLD, 36);
        try {
            splash = ImageIO.read(new File("res\\TitleSplash.png"));
            julian = ImageIO.read(new File("res\\Processed Shots\\JulianSelect.PNG"));
            callum = ImageIO.read(new File("res\\Processed Shots\\CallumSelect.PNG"));
            naufil = ImageIO.read(new File("res\\Processed Shots\\NaufilSelect.PNG"));
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
            g2d.setColor(Color.WHITE);
            g2d.setFont(f);
            //System.out.println(g2d.getFontMetrics());
            if (Window.getTick() % 100 > 30) {
                g2d.drawString("Press SPACE to Scrum:", 748 , 590);
            }
        }
        if (startState == 1) { //second phase, character select
            g2d.setColor(Color.yellow);
            if (selector==0){
                g2d.fillOval(500,200,50,50);
            } else if (selector == 1){
                g2d.fillOval(900,200,50,50);
            } else {
                g2d.fillOval(1300,200,50,50);
            }
            g2d.setColor(Color.darkGray);
            g2d.fillRect(370, 320, 360, 400);
            g2d.drawImage(julian, 350, 320,400,400,null);
            g2d.fillRect(770, 320, 360, 400);
            g2d.drawImage(callum, 750, 320,400,400, null);
            g2d.fillRect(1170, 320, 360, 400);
            g2d.drawImage(naufil, 1150, 320,400,400, null);
        }

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            //playerOneSelect = selector;
            startState++;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (selector!=0) {
                selector--;
            } else {
                selector = 2;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (selector!=2) {
                selector++;
            } else {
                selector = 0;
            }
        }
    }
}
