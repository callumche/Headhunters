import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Naufil extends Character {
    private boolean isInitialized = false;

    public Naufil (boolean p) {
        super(p);
        charType = 2;
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads naufil pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res\\Sprites\\NaufilNeutral.PNG"));
                jump = ImageIO.read(new File("res\\Sprites\\NaufilJump.PNG"));
                bite1 = ImageIO.read(new File("res\\Sprites\\NaufilBite1.PNG"));
                bite2 = ImageIO.read(new File("res\\Sprites\\NaufilBite2.PNG"));
                headbutt = ImageIO.read(new File("res\\Sprites\\NaufilHeadbutt.PNG"));
                hurt = ImageIO.read(new File("res\\Sprites\\NaufilHurt.PNG"));
                special = ImageIO.read(new File("res\\Sprites\\NaufilSpecial.PNG"));
                spit1 = ImageIO.read(new File("res\\Sprites\\NaufilSpit1.PNG"));
                spit2 = ImageIO.read(new File("res\\Sprites\\NaufilSpit2.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
            }
            isInitialized = true;
        }

        updateDirection();
        updateState();

        if (lookingDirection) {
            g2d.drawImage(current, x + current.getWidth(), y, -current.getWidth(), current.getHeight(), null); //mirrored, look right
        } else {
            g2d.drawImage(current, x, y, current.getWidth(), current.getHeight(), null);
        }
        move();
    }
}
