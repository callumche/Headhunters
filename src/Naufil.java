import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Naufil extends Character {
    private boolean isInitialized = false;

    public Naufil (boolean p) {
        super(p);
        charType = 2;
    }

    public void special() {
        if (attackState == 0 && specialCount == 100) {
            specialCount = 0;
            attackState = 5;
            markerFrame = Window.getTick();
            changeImage("special1");
        }
        if (attackState == 5 && Window.getTick() - markerFrame >= 15) {
            xv = 0;
            yv = 0;
            Window.xOffset = (int) (Math.random() * 5);
            Window.yOffset = (int) (Math.random() * 5);
            changeImage("special2");
            if (playerNo) {
                Window.p2.hurt(300);
            } else {
                Window.p1.hurt(300);
            }
        }
        if (attackState == 5 && Window.getTick() - markerFrame >= 60) {
            Window.xOffset = 0;
            Window.yOffset = 0;
            attackState = 0;
        }
    }

    public void paint(Graphics2D g2d){
        if (!isInitialized) { //loads naufil pics into memory on first frame
            try {
                neutral = ImageIO.read(new File("res//Sprites//NaufilNeutral.PNG"));
                jump = ImageIO.read(new File("res//Sprites//NaufilJump.PNG"));
                bite1 = ImageIO.read(new File("res//Sprites//NaufilBite1.PNG"));
                bite2 = ImageIO.read(new File("res//Sprites//NaufilBite2.PNG"));
                headbutt = ImageIO.read(new File("res//Sprites//NaufilHeadbutt.PNG"));
                hurt = ImageIO.read(new File("res//Sprites//NaufilHurt.PNG"));
                special1 = ImageIO.read(new File("res//Sprites//NaufilSpecial1.PNG"));
                special2 = ImageIO.read(new File("res//Sprites//NaufilSpecial2.PNG"));
                spit1 = ImageIO.read(new File("res//Sprites//NaufilSpit1.PNG"));
                spit2 = ImageIO.read(new File("res//Sprites//NaufilSpit2.PNG"));
                evil = ImageIO.read(new File("res//Sprites//NaufilEvil.PNG"));
            } catch (IOException e) {
                System.out.println("Missing Naufil Image: " + e);
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
