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
    private int pOneSelect = 0, pTwoSelect = 2;
    private boolean oneDone = false, twoDone = false;

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

        g2d.setColor(new Color(139, 0, 0));
        g2d.fillRect(0, 0, Window.resX, Window.resY);//background

        if (startState == 0) { //first phase, splash
            g2d.drawImage(splash, 0, 0, null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(f);
            if (Window.getTick() % 100 > 30) {
                g2d.drawString("Press SPACE to Scrum:", 748 , 590);
            }
        }

        if (startState == 1) { //second phase, character select
            if (pOneSelect == pTwoSelect) {
                g2d.setStroke(new BasicStroke(10));
                if (oneDone || twoDone) {
                    g2d.setStroke(new BasicStroke(20));
                }
                g2d.setColor(Color.MAGENTA);
                g2d.drawRect(370 + pOneSelect * 400, 320, 360, 400); // IF BOTH ARE MOUSING OVER SAME
            } else {
                g2d.setColor(Color.BLUE);
                if (!oneDone) {
                    g2d.setStroke(new BasicStroke(10));
                    g2d.drawRect(370 + pOneSelect * 400, 320, 360, 400);
                } else {
                    g2d.setStroke(new BasicStroke(20));
                    g2d.drawRect(370 + pOneSelect * 400, 320, 360, 400);
                }
                g2d.setColor(Color.RED);
                if (!twoDone) {
                    g2d.setStroke(new BasicStroke(10));
                    g2d.drawRect(370 + pTwoSelect * 400, 320, 360, 400);
                } else {
                    g2d.setStroke(new BasicStroke(20));
                    g2d.drawRect(370 + pTwoSelect * 400, 320, 360, 400);
                }
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

        if (startState == 2) { //arena select

        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){ //P1 Pick
            if (startState == 0){
                startState++;
            } else if (startState == 1 && Window.getPlayerTwoSelect() != pOneSelect) {
                Window.setPlayerOneSelect(pOneSelect);
                oneDone = true;
            } else if (startState == 2) {
                startState++;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){ //P2 Select
            if (startState == 1 && Window.getPlayerOneSelect() != pTwoSelect) {
                Window.setPlayerTwoSelect(pTwoSelect);
                twoDone = true;
                System.out.println("two done!");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (!oneDone) {
                if (pOneSelect!=0) {
                    pOneSelect--;
                    System.out.println("P1 moving left");
                } else {
                    pOneSelect = 2;
                    System.out.println("P1 moving left");
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (!oneDone) {
                if (pOneSelect != 2) {
                    pOneSelect++;
                } else {
                    pOneSelect = 0;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!twoDone) {
                if (pTwoSelect != 0) {
                    pTwoSelect--;
                    System.out.println("P2 moving left");
                } else {
                    pTwoSelect = 2;
                    System.out.println("P2 moving left");
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!twoDone) {
                if (pTwoSelect != 2) {
                    pTwoSelect++;
                } else {
                    pTwoSelect = 0;
                }
            }
        }
    }
}
