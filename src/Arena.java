import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Arena {

    private int arena;
    private boolean isInitialized = false, countdown = true, suddenDeath = false, starting = false;
    private BufferedImage phys;
    private int time, count;

    public Arena(){
    }

    public void setSuddenDeath(boolean set){
        suddenDeath = set;
    }

    public void setTime(int newTime){
        time = newTime;
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) {
            time = 120;
            count = 4;
            isInitialized = true;
                try {
                    if (Window.getPlayerOneSelect() == 2 || Window.getPlayerTwoSelect() == 2) {
                        phys = ImageIO.read(new File("res//PhysicsNaufilDead.PNG"));
                    } else {
                        phys = ImageIO.read(new File("res//PhysicsNaufilAlive.PNG"));
                    }
                } catch (IOException e) {
                    System.out.println("Missing Arena Image: " + e);
                }
        }
        if (Window.getTick() == 32) { //magic number :(
            count = 3;
        }
        if (Window.getTick() % 60 == 0) {

            if (countdown) {
                count -= 1;
            } else {
                time -= 1;
            }

        }
        arena = Window.getArenaSelect();
        if (arena == 0) {
            g2d.setColor(new Color(28, 167, 231));
            g2d.fillRect(0,0,Window.resX,Window.resY);
            g2d.setColor(Color.yellow);
            g2d.fillOval(Window.xOffset + 100, Window.yOffset + 200, 300, 300);
            g2d.setColor(Color.green);
            for (int i = 0; i <= Window.resX; i += 15) {
                g2d.fillRect(Window.xOffset + i, Window.yOffset + 950, 5, 150);
            }
        } else if (arena == 1) {
            g2d.setColor(Color.darkGray);
            g2d.fillRect(0,0,Window.resX,Window.resY);
            g2d.setColor(Color.white);
            g2d.fillOval(Window.xOffset + 100, Window.yOffset + 200, 300, 300);
            g2d.fillOval(Window.xOffset + 1800, Window.yOffset + 100, 10, 10);
            g2d.fillOval(Window.xOffset + 1650, Window.yOffset + 150, 10, 10);
            g2d.fillOval(Window.xOffset + 1200, Window.yOffset + 75, 5, 5);
        } else if (arena == 2) {
            g2d.drawImage(phys, Window.xOffset, Window.yOffset, null);
        }
        if (countdown && count <= 3){
            if (!starting) {
                Window.p1.start(180);
                Window.p2.start(180);
                starting = true;
            }
            if (suddenDeath) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.yellow);
            }
            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
            if (count >= 1) {
                g2d.drawString(String.valueOf(count), 925, 200);
            } else if (count==0){
                g2d.drawString("GO",900,200);
            } else {
                countdown = false;
            }
        } else if (suddenDeath) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            g2d.drawString("SUDDEN DEATH", 775, 100);
        } else {
            g2d.setColor(Color.yellow);
            g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
            g2d.drawString(String.valueOf(time), 925, 50);
        }
        if (time <= 0 && !suddenDeath) {
            Window.p1.start(300);
            Window.p2.start(300);
            suddenDeath = true;
            countdown = true;
            count = 5;
            Window.p1.x = 480;
            Window.p1.y = 300;
            Window.p2.x = 1240;
            Window.p2.y = 300;
            Window.p1.xv = 0;
            Window.p2.xv = 0;
            Window.p1.yv = 0;
            Window.p2.yv = 0;
            Window.p1.health = 1;
            Window.p2.health = 1;
        }
    }

}
