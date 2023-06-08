import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Callum extends Character{
    private boolean isInitialized = false;
    private boolean firingLaser = false;

    public Callum (boolean p) {
        super(p);
        charType = 1;
    }

    public void special() {
        if (attackState == 0) {
            attackState = 5;
            markerFrame = Window.getTick();
            altDirection = lookingDirection;
            changeImage("special1");
        }

        lookingDirection = altDirection;

        if (Window.getTick() - markerFrame >= 60) {
            changeImage("special2");
            firingLaser = true;
        }

        if (Window.getTick() - markerFrame >= 180) {
            attackState = 0;
        }

    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res//Sprites//CallumNeutral.PNG"));
                jump = ImageIO.read(new File("res//Sprites//CallumJump.PNG"));
                bite1 = ImageIO.read(new File("res//Sprites//CallumBite1.PNG"));
                bite2 = ImageIO.read(new File("res//Sprites//CallumBite2.PNG"));
                headbutt = ImageIO.read(new File("res//Sprites//CallumHeadbutt.PNG"));
                hurt = ImageIO.read(new File("res//Sprites//CallumHurt.PNG"));
                special1 = ImageIO.read(new File("res//Sprites//CallumSpecial1.PNG"));
                special2 = ImageIO.read(new File("res//Sprites//CallumSpecial2.PNG"));
                spit1 = ImageIO.read(new File("res//Sprites//CallumSpit1.PNG"));
                spit2 = ImageIO.read(new File("res//Sprites//CallumSpit2.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Callum Image: " + e);
            }
            isInitialized = true;
        }
        if (attackState != 5) {
            firingLaser = false;
        }

        if (firingLaser) {
            if (Math.random() <= 0.5) {
                x += 5 * Math.random();
                y += 5 * Math.random();
            } else {
                x -= 5 * Math.random();
                y -= 5 * Math.random();
            }
            g2d.setColor(new Color (84, 0, 255));
            if (lookingDirection) {
                g2d.fillRect(x + 190, y + 75, 1920, 50);
                g2d.setColor(Color.WHITE);
                if (Window.getTick() % 16 <= 8) {
                    for (int i = x; i <= 1920; i += 50){
                        g2d.fillRect(i, y + 90, 50, 20);
                    }
                } else {
                    for (int i = x + 25; i <= 1920; i += 50){
                        g2d.fillRect(i, y + 85, 50, 30);
                    }
                }
            } else {
                g2d.fillRect(x - 1910, y + 75, 1920, 50);
            }

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
