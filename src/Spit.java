import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spit {
    public int x, y;
    private double xv, yv = 5;
    private boolean isInitialized = false, owner;
    private BufferedImage img;
    private boolean direct;

    public boolean getOwner() {
        return owner;
    }

    public Spit (int newX, int newY, boolean dir, boolean own) {
        x = newX;
        y = newY + 125;
        direct = dir;
        if (dir) {
            xv = 50;
            x += 100;
        } else {
            xv = -50    ;
        }
        owner = own;
    }

    public void ballistic() {
        x += xv;
        y -= yv;
        if (xv > 0) {
            xv -= 0.5;
        } else {
            xv += 0.5;
        }
        yv -= 0.5;
    }

    public void paint(Graphics2D g2d) {
        if (!isInitialized) {
            try {
                img = ImageIO.read(new File("res//Sprites//Spit.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Spit Image: " + e);
            }
            isInitialized = true;
        }
        ballistic();
        if (!direct) {
            g2d.drawImage(img, x + img.getWidth(), y, -img.getWidth(), img.getHeight(), null); //mirrored, look right
        } else {
            g2d.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
        }
    }
}
