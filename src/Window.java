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
    static HealthBar hb1;
    static HealthBar hb2;
    private static double distance;
    private static boolean position = true; //true = P2 to right of P1, false = P2 to left

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
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (isStarting) {
            startMenu.paint(g2d);
        } else {
            distance = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
            position = p2.x > p1.x;
            p1.paint(g2d);
            p2.paint(g2d);
            hb1.paint(g2d);
            hb2.paint(g2d);
        }
    }

    public double getDistance() {
        return distance;
    }
    public boolean getPosition() {
        return position;
    }

    public static boolean isHit(boolean caller) { //true = P1, false = P2
        if (caller) { //P1 is attacking
            if (p1.getDirection() && p2.getState() != 4) {//if P1 is facing right
                return (distance <= 210 && Math.abs(p2.y - p1.y) < 50 && position);
            } else if (p1.getState() != 4) {
                return (distance <= 210 && Math.abs(p2.y - p1.y) < 50 && !position);
            }
        } else {
            if (p2.getDirection() && p1.getState() != 4) {//if right
                return (distance <= 210 && Math.abs(p2.y - p1.y) < 50 && !position);
            } else if (p1.getState() != 4) {
                return (distance <= 210 && Math.abs(p2.y - p1.y) < 50 && position);
            }
        }
        return false;
    }

    public static void init() {
        hb1 = new HealthBar(true);
        hb2 = new HealthBar(false);
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