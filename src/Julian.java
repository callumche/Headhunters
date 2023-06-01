import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Julian extends Character{
    private boolean isInitialized = false;
    private BufferedImage neutral = null, jump = null;
    private BufferedImage current = null;

    public Julian (boolean p) {
        super(p);
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads naufil pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res\\Sprites\\JulianNeutral.PNG"));
                jump = ImageIO.read(new File("res\\Sprites\\JulianJump.PNG"));
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
