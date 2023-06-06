import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Callum extends Character{
    private boolean isInitialized = false;

    public Callum (boolean p) {
        super(p);
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res\\Sprites\\CallumNeutral.PNG"));
                jump = ImageIO.read(new File("res\\Sprites\\CallumJump.PNG"));
                bite1 = ImageIO.read(new File("res\\Sprites\\CallumBite1.PNG"));
                bite2 = ImageIO.read(new File("res\\Sprites\\CallumBite2.PNG"));
                headbutt = ImageIO.read(new File("res\\Sprites\\CallumHeadbutt.PNG"));
                hurt = ImageIO.read(new File("res\\Sprites\\CallumHurt.PNG"));
                special = ImageIO.read(new File("res\\Sprites\\CallumSpecial.PNG"));
                spit1 = ImageIO.read(new File("res\\Sprites\\CallumSpit1.PNG"));
                spit2 = ImageIO.read(new File("res\\Sprites\\CallumSpit2.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Callum Image: " + e);
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
