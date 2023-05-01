import java.awt.*;
import java.awt.event.KeyEvent;

public class Entity {
    private int x = 300;
    private int y = 300;
    private double xv = 0;
    private double yv = 0;
    private double xa = 0;
    private double ya = 0;

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            xa = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            xa = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            ya = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            ya = -1;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            xa = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            xa = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            ya = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            ya = 0;
        }
    }

    public void updateMovement() {
        x += xv;
        y -= yv;

        xv += xa;
        yv += ya;
    }

    public void paint (Graphics2D g2d) {
        g2d.fillRect(x, y, 20, 20);
        updateMovement();
    }
}
