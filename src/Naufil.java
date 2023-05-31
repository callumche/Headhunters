import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Naufil extends Character {
    private boolean isInitialized = false;
    private BufferedImage neutral = null;


    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads naufil pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res\\Sprites\\NaufilNeutral.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
            }
            isInitialized = true;
        }
        g2d.drawImage(neutral, x, y, null);
        move();

        if (jumpCount != 0) {

            try {
                neutral = ImageIO.read(new File("res\\Sprites\\NaufilJump.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
            }

        } else {

            try {
                neutral = ImageIO.read(new File("res\\Sprites\\NaufilNeutral.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
            }

        }

    }
}
