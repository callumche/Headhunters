import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Character {
    protected boolean left = false, right = false;
    protected int x = 600;
    protected int y = 300;
    protected double xv = 0;
    protected double yv = 0;
    protected double xa = 0;
    protected double ya = -1.5;
    protected int speedCap = 20;
    protected int jumpCount = 0;

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
            //xa = -5;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
            //xa = 5;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if (jumpCount < 2) {
                jumpCount++;
                yv = 30;
            }
            //ya = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            //ya = -1;
        }
    }
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
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
        //understand that this can be hardcoded directly to +/- to speed, but doing xa for clarity
        if (left && right) {
            xa = 0;
        } else if (left) {
            xa = -5;
        } else if (right) {
            xa = 5;
        } else {
            xa = 0;
        }

        //speedcaps first
        if (xv < speedCap && xv > -speedCap) { //speed cap of 10
            xv += xa;
        } else if (xv >= speedCap) { //if above speed cap, decelerate
            xv -= 1;
        } else if (xv <= -speedCap) { //backwards speed cap
            xv += 1;
        }
        if (yv < speedCap + 10 && yv > -speedCap - 10) {
            //yv += ya; //hardcoded to apply gravity when not on ground
            if (y < Window.resY - 50 - 200) {
                ya = -1.5;
                yv += ya;
            }
        } else if (yv >= speedCap + 10) { //vertical speed cap; has a little extra for fun
            yv -= 1;
        } else if (yv <= -speedCap - 10) {
            yv += 1;
        }

        x += xv;
        //below are bounds checks (see if in bounds, if not, reset position to border and invert velocity
        if (x > Window.resX - 200) { //right border
            x = Window.resX - 200;
            xv = -0.75 * xv; //bounce inelastically when you hit the border
            //System.out.println("right bounce");
        } else if (x < 0) {
            x = 1;
            xv = -0.75 * xv;
            //System.out.println("left bounce");
        }
        y -= yv;
        if (y > Window.resY - 50 - 200) { //height of windows bar ~= 50px
            y = Window.resY - 50 - 200;
            ya = 0;
            yv = -0.4 * yv;
            if (yv <= 4) {
                jumpCount = 0;
                yv = 0;
                //System.out.println("Reset count!");
            }
            //System.out.println("bottom bounce");
        } else if (y < 0) {
            y = 1;
            yv = -0.5 * yv;
            //System.out.println("top bounce");
        }
        if (y >= Window.resY - 55 - 200) {
            friction();
        }
        //friction, constantly decelerating at 0.1
    }

    public void friction(){
        if (xv > 0.5) {
            xv -= 0.50;
        } else if (xv < -0.5) {
            xv += 0.50;
        } else {
            xv = 0;
        }
        if (yv > 0.5) {
            yv -= 0.50;
        } else if (yv < -0.5) {
            yv += 0.50;
        } else {
            yv = 0;
        }
    }

    public void paint (Graphics2D g2d) { //holding out temporarily
        move();
    }


}
