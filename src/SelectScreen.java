import java.awt.*;
import java.awt.event.KeyEvent;

public class SelectScreen {
    private boolean startMenu = true;
    public void paint (Graphics2D g2d){
        if (startMenu) {
            g2d.setColor(Color.red);
            g2d.fillRect(5, 5, Window.resX - 5, Window.resY - 5); //image of title screen just using red rectangle for now
        } else {

        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            startMenu = false;
        }
    }
}
