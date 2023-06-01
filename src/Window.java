import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Window extends JPanel {
    SelectScreen startMenu = new SelectScreen();
    public static final int resX = 1920, resY = 1080;
    private int stage = 0;
    private static long globalTick = 0;
    private static boolean isStarting = true;
    private static int playerOneSelect = 4, playerTwoSelect = 4, arenaSelect = 3;
    static Character p1 = null;
    static Character p2 = null;

    public Window(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (!isStarting) {
                    p1.keyReleased(e);
                    p2.keyReleased(e);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                startMenu.keyPressed(e);
                if (!isStarting) {
                    p1.keyPressed(e);
                    p2.keyPressed(e);
                }
            }
        });
        setFocusable(true);
    }

    public static void init() {
        switch (playerOneSelect) {
            case 0:
                p1 = new Julian(true);
                break;
            case 1:
                p1 = new Callum(true);
                break;
            case 2:
                p1 = new Naufil(true);
                break;
        }
        switch (playerTwoSelect) {
            case 0:
                p2 = new Julian(false);
                break;
            case 1:
                p2 = new Callum(false);
                break;
            case 2:
                p2 = new Naufil(false);
                break;
        }
    }

    public static void setPlayerOneSelect(int n){
        playerOneSelect = n;
    }
    public static void setPlayerTwoSelect(int n){
        playerTwoSelect = n;
    }
    public static int getPlayerOneSelect(){
        return playerOneSelect;
    }
    public static int getPlayerTwoSelect(){
        return playerTwoSelect;
    }
    public static void setArenaSelect(int s){
        arenaSelect = s;
    }
    public static void endStart() {
        isStarting = false;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int n) {
        stage = n;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (isStarting) {
            startMenu.paint(g2d);
        } else {
            p1.paint(g2d);
            p2.paint(g2d);
        }
    }

    public static long getTick() {
        return globalTick;
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
            Thread.sleep(16); //roughly 60FPS
            //Thread.sleep(60); //debug
            globalTick++;
            frame.repaint();
        }
    }
}