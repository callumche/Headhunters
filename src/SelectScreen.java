import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class SelectScreen {
    private int startState = 0; //converted boolean into start phase (0 = splash, 1 = char 1 select, 2 = char 2 select, 3 = arena select, 4 = scrum
    private boolean isInitialized = false;
    private BufferedImage splash = null, julian = null, callum = null, naufil = null;
    private Font f = null;
    private int pOneSelect = 0;
    private int pTwoSelect = 2;

    private boolean oneDone = false;
    private boolean twoDone = false;

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

        if (startState >= 3) {
            Window.endStart();
        }

        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, Window.resX, Window.resY);//background

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
            g2d.setStroke(new BasicStroke(10));

            if (pOneSelect == 0) {
                g2d.setStroke(new BasicStroke(10));
                if (pTwoSelect == pOneSelect) {
                    g2d.setColor(new Color(255, 0, 255));
                } else {
                    g2d.setColor(Color.BLUE);
                }
                g2d.drawRect(370, 320, 360, 400);

            }

            g2d.setStroke(new BasicStroke(1));

            g2d.setColor(Color.darkGray);
            g2d.fillRect(370, 320, 360, 400);
            g2d.drawImage(julian, 350, 320,400,400,null);
            g2d.fillRect(770, 320, 360, 400);
            g2d.drawImage(callum, 750, 320,400,400, null);
            g2d.fillRect(1170, 320, 360, 400);
            g2d.drawImage(naufil, 1150, 320,400,400, null);

            if (Window.getPlayerOneSelect() < 3 && Window.getPlayerTwoSelect() < 3) {
                startState++;
            }
        }

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if(startState == 0){
                startState++;
            }
            if (startState == 1) {
                Window.setPlayerOneSelect(pOneSelect);
                oneDone = true;
            }
            if (startState == 2 && Window.getPlayerOneSelect() != pOneSelect) {
                Window.setPlayerTwoSelect(pTwoSelect);
                startState++;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){ //P2 Select
            if (startState == 1) {
                Window.setPlayerTwoSelect(pTwoSelect);
                oneDone = true;
            }
            if (startState == 2 && Window.getPlayerTwoSelect() != pOneSelect) {
                Window.setPlayerTwoSelect(pTwoSelect);
                startState++;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (pOneSelect!=0) {
                pOneSelect--;
            } else {
                pOneSelect = 2;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (pOneSelect != 2) {
                pOneSelect++;
            } else {
                pOneSelect = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (pTwoSelect != 0) {
                pTwoSelect--;
            } else {
                pTwoSelect = 2;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (pOneSelect != 2) {
                pTwoSelect++;
            } else {
                pTwoSelect = 0;
            }
        }
    }
}
