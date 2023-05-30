import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
public class CharacterSelect {
    private boolean startMenu = true;
    private boolean isInitialized = false;
    private BufferedImage neutral = null;
    public void paint (Graphics2D g2d){
        if (startMenu) {
            g2d.setColor(Color.red);
            g2d.fillRect(5, 5, Window.resX - 5, Window.resY - 5); //image of title screen just using red rectangle for now
            if (!isInitialized) { //loads naufil pics into memory on first frame
                try {
                    neutral = ImageIO.read(new File("res\\TitleSplash.png"));
                } catch (IOException e) {
                    System.out.println("Missing head huntsa Image: " + e);
                }
                isInitialized = true;
            }
            g2d.drawImage(neutral, 0, 0, null);
        } else {

        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            startMenu = false;
        }
    }
}
