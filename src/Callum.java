import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Callum extends Character{
    private boolean isInitialized = false;
    private BufferedImage neutral = null, jump = null;
    private BufferedImage current = null;

    public Callum (boolean p) {
        super(p);
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads naufil pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res\\Sprites\\CallumNeutral.PNG"));
                jump = ImageIO.read(new File("res\\Sprites\\CallumJump.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
            }
            isInitialized = true;
        }

        if (jumpCount != 0) {
            current = jump;
        } else {
            current = neutral;
        }

        if (lookingDirection) {
            g2d.drawImage(current, x + current.getWidth(), y, -current.getWidth(), current.getHeight(), null); //mirrored, look right
        } else {
            g2d.drawImage(current, x, y, current.getWidth(), current.getHeight(), null);
        }
        move();
        updateDirection();
    }
}
