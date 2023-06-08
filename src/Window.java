import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Window extends JPanel {
    SelectScreen startMenu = new SelectScreen();
    Arena arena = new Arena();
    public static final int resX = 1920, resY = 1080;
    private static long globalTick = 0;
    private static boolean isStarting = true;
    private static int playerOneSelect = 4, playerTwoSelect = 4, arenaSelect = 3;
    static Character p1, p2;
    static HealthBar hb1 , hb2;
    private static double playerDistance;
    private static boolean position = true; //true = P2 to right of P1, false = P2 to left
    private static boolean finished = false, p1Winner = true;
    public static ArrayList<Spit> spits = new ArrayList<Spit>();

    WinScreen winS = new WinScreen();

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
        } else if (!finished){
            playerDistance = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
            position = p2.x > p1.x;
            arena.paint(g2d);
            p1.paint(g2d);
            p2.paint(g2d);
            hb1.paint(g2d);
            hb2.paint(g2d);
            collide();
            if (!spits.isEmpty()) {
                for (int i = 0; i < spits.size(); i++){
                    spits.get(i).paint(g2d);
                    if (isProjectileHit(spits.get(i))) {
                        if (spits.get(i).getOwner()) {
                            p2.hurt();
                            p2.applyDamage(5);
                        } else {
                            p1.hurt();
                            p1.applyDamage(5);
                        }
                        spits.remove(i);
                        break;
                    }
                    int x = spits.get(i).x;
                    int y = spits.get(i).y;
                    if (x <= 0 || x >= resX || y <= 0 || y >= resX) {
                        spits.remove(i);
                        break;
                    }
                }


            }

        } else {
            winS.paint(g2d);
        }
    }

    public double getDistance() {
        return playerDistance;
    }
    public boolean getPosition() {
        return position;
    }

    public boolean isProjectileHit(Spit i) {
        if (i.getOwner()) { //P1
            int xDist = Math.abs(p2.x + 100 - (i.x + 25));
            int yDist = Math.abs(p2.y + 100 - (i.y + 25));
            double distance = Math.sqrt(xDist * xDist + yDist * yDist);
            return distance <= 100;
        } else {
            int xDist = Math.abs(p1.x + 100 - (i.x + 25));
            int yDist = Math.abs(p1.y + 100 - (i.y + 25));
            double distance = Math.sqrt(xDist * xDist + yDist * yDist);
            return distance <= 100;
        }
    }

    public static boolean isHit(boolean caller) { //true = P1, false = P2
        if (caller) { //P1 is attacking
            if (p1.getDirection() && p2.getState() != 4) {//if P1 is facing right
                return (playerDistance <= 250 && Math.abs(p2.y - p1.y) < 50 && position);
            } else if (p1.getState() != 4) {
                return (playerDistance <= 250 && Math.abs(p2.y - p1.y) < 50 && !position);
            }
        } else {
            if (p2.getDirection() && p1.getState() != 4) {//if right
                return (playerDistance <= 250 && Math.abs(p2.y - p1.y) < 50 && !position);
            } else if (p1.getState() != 4) {
                return (playerDistance <= 250 && Math.abs(p2.y - p1.y) < 50 && position);
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

    public void collide() {
        double distance = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
        if (distance < 200) {
            double overlap = 200 - distance;
            double dx = p2.x - p1.x;
            double dy = p2.y - p1.y;
            double angle = Math.atan2(dy, dx);
            double shiftX = overlap * Math.cos(angle);
            double shiftY = overlap * Math.sin(angle);

            p1.x -= shiftX / 2;
            p1.y -= shiftY / 2;
            p2.x += shiftX / 2;
            p2.y += shiftY / 2;
            p1.xv *= 0.75;
            p2.xv *= 0.75;
        }
    }

    public static boolean getWinner(){return p1Winner;}
    public static void setWinner(Boolean bool){p1Winner = bool;}
    public static void setFinished(){ finished = true;}
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
    public static int getArenaSelect(){return arenaSelect;}
    public static void endStart() {
        isStarting = false;
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
//        double longest = 0;
        while(true) {
            Thread.sleep(16);
            globalTick++;
            frame.repaint();
        }
    }
}