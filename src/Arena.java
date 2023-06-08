import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Arena {

    private int arena;
    private boolean isInitialized = false;
    private BufferedImage phys;
    private int time;

    public Arena(){
    }

    public int getTime() {
        return time;
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized){
            Timer timer = new Timer(0,1000);
            isInitialized = true;
            if (Window.getPlayerOneSelect() == 2 || Window.getPlayerTwoSelect() == 2) {
                try {
                    phys = ImageIO.read(new File("res//PhysicsNaufilDead.PNG"));
                } catch (IOException e) {
                    System.out.println("Missing Arena Image: " + e);
                }
            } else {
                try {
                    phys = ImageIO.read(new File("res//PhysicsNaufilAlive.PNG"));
                } catch (IOException e) {
                    System.out.println("Missing Arena Image: " + e);
                }
            }
        }

        arena = Window.getArenaSelect();
        if (arena == 0) {
            g2d.setColor(Color.cyan);
            g2d.fillRect(0,0,Window.resX,Window.resY);
            g2d.setColor(Color.yellow);
            g2d.fillOval(100, 200, 300, 300);
            g2d.setColor(Color.green);
            for (int i = 0; i <= Window.resX; i += 15) {
                g2d.fillRect(i, 950, 5, 150);
            }
        } else if (arena == 1) {
            g2d.setColor(Color.darkGray);
            g2d.fillRect(0,0,Window.resX,Window.resY);
            g2d.setColor(Color.white);
            g2d.fillOval(100, 200, 300, 300);
            g2d.fillOval(1800, 100, 10, 10);
            g2d.fillOval(1650, 150, 10, 10);
            g2d.fillOval(1200, 75, 5, 5);
        } else if (arena == 2) {
            g2d.drawImage(phys, 0, 0, null);
        }

        time = 120 - Helper.checkTime(0,0);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        g2d.drawString(""+time,1000,30);

    }
}
