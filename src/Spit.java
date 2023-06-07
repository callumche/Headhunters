import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spit {
    private int x, y;
    private double xv, yv = 0;
    private boolean isInitialized = false, owner;
    private BufferedImage img;

    public Spit (int newX, int newY, boolean dir, boolean own) {
        x = newX;
        y = newY;
        if (dir) {
            xv = 35;
        } else {
            xv = -35;
        }
        owner = own;
    }

    public void ballistic() {
        if (x > 0) {
            x += xv;
        }
        y -= yv;
        xv -= 0.5;
        yv -= 0.25;
    }

    public void paint(Graphics2D g2d) {
        if (!isInitialized) {
            try {
                img = ImageIO.read(new File("res\\Sprites\\Spit.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Spit Image: " + e);
            }
            isInitialized = true;
        }
        ballistic();
        g2d.drawImage(img, x, y, null);
    }
}
