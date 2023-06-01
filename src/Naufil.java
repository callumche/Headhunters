import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Naufil extends Character {
    private boolean isInitialized = false;
    private BufferedImage neutral = null, jump = null;
    private BufferedImage current = null;

    public Naufil (boolean p) {
        super(p);
    }

    public void changeImage(String str) {
        switch (str) {
            case "jump":
                
        }
    }

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

//        collide();
        updateDirection();


    }
}
