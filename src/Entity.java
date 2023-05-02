import java.awt.*;
import java.awt.event.KeyEvent;

public class Entity {
    private int x = 300;
    private int y = 300;
    private double xv = 0;
    private double yv = 0;
    private double xa = 0;
    private double ya = 0;
    private int speedCap = 10;
    private int jumpCount = 0; //int not boolean for possible future double+ jump support

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            xa = -2;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            xa = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if (jumpCount < 2) {
                jumpCount++;
                yv = 15;
            }
            //ya = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            //ya = -1;
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
            //ya = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            //ya = 0;
        }
    }


    public void move() {
        //speedcaps first
        if (xv < speedCap && xv > -speedCap) { //speed cap of 10
            xv += xa;
        } else if (xv >= speedCap) { //if above speed cap, decelerate
            xv -= 1;
        } else if (xv <= -speedCap) { //backwards speed cap
            xv += 1;
        }
        if (yv < speedCap && yv > -speedCap) {
            yv += ya; //hardcoded to apply gravity when not on ground
            if (y < 580) {
                yv -= 0.5;
            }
        } else if (yv >= speedCap) { //if above speed cap, decelerate
            yv -= 1;
        } else if (yv <= -speedCap) { //backwards speed cap
            yv += 1;
        }

        x += xv;
        //below are bounds checks (see if in bounds, if not, reset position to border and invert velocity
        if (x > 990) { //right border
            x = 990;
            xv = -0.75 * xv; //bounce inelastically when you hit the border
            System.out.println("right bounce");
        } else if (x < 0) {
            x = 1;
            xv = -0.75 * xv;
            System.out.println("left bounce");
        }
        y -= yv;
        if (y > 580) { //height of windows bar ~= 50px
            y = 580;
            ya = 0;
            yv = -0.5 * yv;
            if (yv <= 4) {
                jumpCount = 0;
                yv = 0;
                System.out.println("Reset count!");
            }
            System.out.println("bottom bounce");
        } else if (y < 0) {
            y = 1;
            yv = -0.5 * yv;
            System.out.println("top bounce");
        }
        if (y == 580) {
            friction();
        }
        //friction, constantly decelerating at 0.1
    }

    public void friction(){
        if (xv > 0.1) {
            xv -= 0.2;
        } else if (xv < -0.1) {
            xv += 0.2;
        } else {
            xv = 0;
        }
        if (yv > 0.1) {
            yv -= 0.2;
        } else if (yv < -0.1) {
            yv += 0.2;
        } else {
            yv = 0;
        }
    }

    public void paint (Graphics2D g2d) {
        g2d.fillRect(x, y, 20, 20);
        move();
    }
}
