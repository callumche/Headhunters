import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Naufil extends Character {
    private boolean isInitialized = false;
    private BufferedImage neutral = null, jump = null;
    private BufferedImage current = null;

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads naufil pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res\\Sprites\\NaufilNeutral.PNG"));
                jump = ImageIO.read(new File("res\\Sprites\\NaufilJump.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
            }
            isInitialized = true;
        }
        g2d.drawImage(current, x, y, null);
        move();

        if (jumpCount != 0) {
            current = jump;
        } else {
            current = neutral;
        }

    }
}
