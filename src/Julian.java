import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Julian extends Character{
    private boolean isInitialized = false;

    public Julian (boolean p) {
        super(p);
        charType = 0;
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res//Sprites//JulianNeutral.PNG"));
                jump = ImageIO.read(new File("res//Sprites//JulianJump.PNG"));
                bite1 = ImageIO.read(new File("res//Sprites//JulianBite1.PNG"));
                bite2 = ImageIO.read(new File("res//Sprites//JulianBite2.PNG"));
                headbutt = ImageIO.read(new File("res//Sprites//JulianHeadbutt.PNG"));
                hurt = ImageIO.read(new File("res//Sprites//JulianHurt.PNG"));
                special1 = ImageIO.read(new File("res//Sprites//JulianSpecial1.PNG"));
                special2 = ImageIO.read(new File("res//Sprites//JulianSpecial2.PNG"));
                spit1 = ImageIO.read(new File("res//Sprites//JulianSpit1.PNG"));
                spit2 = ImageIO.read(new File("res//Sprites//JulianSpit2.PNG"));
                evil = ImageIO.read(new File("res//Sprites//JulianEvil.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Julian Image: " + e);
            }
            isInitialized = true;
        }

        updateDirection();
        updateState();

        if (lookingDirection) {
            g2d.drawImage(current, x + current.getWidth() + Window.xOffset, y + Window.yOffset, -current.getWidth(), current.getHeight(), null); //mirrored, look right
        } else {
            g2d.drawImage(current, x + Window.xOffset, y + Window.yOffset, current.getWidth(), current.getHeight(), null);
        }
        move();
    }
}
