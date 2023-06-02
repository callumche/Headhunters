import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public abstract class Character {
    protected boolean left = false, right = false;
    public int x = 480, y = 300;
    public double xv = 0, yv = 0, xa = 0, ya = 0; //fuck you, im making this public (i don't want a trillion getters/setters) - FIX LATER?
    protected int speedCap = 20, jumpCount = 0;
    protected boolean lookingDirection = true; //false = left, true = right
    protected boolean playerNo; //true = P1, false = P2
    protected int attackState = 0; //0 = not attacking, 1 = biting, 2 = spitting, 3 = special, 4 = hurt
    protected BufferedImage neutral, jump, bite1, bite2, headbutt, hurt, special, spit1, spit2;
    protected BufferedImage current;
    private long markerFrame = 0;
    protected int health = 100;

    public boolean getDirection() {
        return lookingDirection;
    }
    /*public int getX() { unneeded getter/setters
        return x;
    }
    public void setX(int newX){
        x = newX;
    }
    public int getY() {
        return y;
    }
    public void setY(int newY){
        y = newY;
    }*/

    public Character (boolean p) {
        playerNo = p;
        if (!p) {
            x = 1240;
        }
    }

    public void applyDamage(int dam) {
        health -= dam;
    }
    public int getHealth(){
        return health;
    }
    public void updateState() {
        if (attackState == 1) {
            bite();
        }
        if (attackState == 4) {
            hurt();
        }
        if (jumpCount != 0 && attackState == 0) {
            changeImage("jump");
        } else if (attackState == 0) {
            changeImage("neutral");
        }
    }

    public void changeImage(String str) {
        switch (str) {
            case "neutral":
                current = neutral;
                break;
            case "jump":
                current = jump;
                break;
            case "bite1":
                current = bite1;
                break;
            case "bite2":
                current = bite2;
                break;
            case "hurt":
                current = hurt;
                break;
            default:
                current = neutral;
                System.out.println("Image change failed, defaulting to neutral for P" + playerNo);
                break;
        }
    }

    public void bite(){
        if (attackState != 1){ //initiate attack
            attackState = 1;
            markerFrame = Window.getTick();
            changeImage("bite1");
        }
        if (Window.getTick() - markerFrame == 30) { //actual bite 30 frames later
            changeImage("bite2");
            if (playerNo) { //this nest is hurting me send help
                if (Window.isHit(playerNo)) {
                        if (getDirection()) { //knockback
                            Window.p2.xv = 20;
                            Window.p2.yv = 10;
                        } else {
                            Window.p2.xv = -20;
                            Window.p2.yv = 10;
                        }
                    Window.p2.applyDamage(30); //damage
                }
            } else {
                if (Window.isHit(playerNo)) {
                    if (getDirection()) { //knockback
                        Window.p1.xv = 20;
                        Window.p1.yv = 10;
                    } else {
                        Window.p1.xv = -20;
                        Window.p1.yv = 10;
                    }
                    Window.p1.applyDamage(30); //damage
                }
            }
        }
        if (Window.getTick() - markerFrame == 50) {
            attackState = 0;
        }
    }

    public void hurt() {
        if (attackState != 4) {
            attackState = 4;
            markerFrame = Window.getTick();
            changeImage("hurt");
        }
        if (Window.getTick() - markerFrame == 20) {
            attackState = 0;
            changeImage("neutral");
        }
    }

    public void updateDirection() {
        if (xv < 0) {
            lookingDirection = false;
        } else if (xv > 0) { // if not moving, look in prior direction
            lookingDirection = true; //LOOK RIGHT
        }
    }

    public void keyPressed(KeyEvent e){
        if (playerNo) { //P1, WASD
            if (e.getKeyCode() == KeyEvent.VK_A){
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D){
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_W){
                if (jumpCount < 2) {
                    jumpCount++;
                    yv = 30;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_M){
                bite();
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                if (jumpCount < 2) {
                    jumpCount++;
                    yv = 30;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                bite();
            }
        }

    }
    public void keyReleased(KeyEvent e){
        if (playerNo) { //if Player 1 (WASD)
            if (e.getKeyCode() == KeyEvent.VK_A){
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D){
                right = false;
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                right = false;
            }
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
            x = 0;
            xv = -0.75 * xv;
            //System.out.println("left bounce");
        }
        y -= yv;

        if (y > Window.resY - 50 - 200) { //height of windows bar ~= 50px
            y = Window.resY - 50 - 200;
            ya = 0;
            yv = -0.3 * yv;
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
        if (y >= Window.resY - 55 - 200) { //friction, constantly decelerating at 0.1
            if (xv > 0.5) {
                xv -= 1;
            } else if (xv < -0.5) {
                xv += 1;
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

    }

    /*public void collide() {
        if (playerNo) { //if P1
            int dx = Window.p2.getX() - x;
            int dy = Window.p2.getY() - y;
            double distance = Math.sqrt(dx * dx + dy * dy); //dist b/w centers, ALWAYS POSITIVE
            boolean p2IsToRight = dx < 0;
            //IF DX IS NEGATIVE, P2 IS TO THE RIGHT OF P1
            if (distance <= 200 && p2IsToRight) { //P2 is to the right
                xv = xv * -0.25;
                x += 5;
                System.out.println("Moving right!");
            } else if (distance <= 200) { //P2 is to the left
                xv = xv * -0.25;
                x -= 5;
                System.out.println("Moving left!");
            }
        }
        if (!playerNo) { //if P2
            int dx = Window.p1.getX() - x;
            int dy = Window.p1.getY() - y;
            double distance = Math.sqrt(dx * dx + dy * dy); //dist b/w centers, ALWAYS POSITIVE
            boolean p1IsToRight = dx < 0;
            //IF DX IS NEGATIVE, P1 IS TO THE RIGHT OF P2
            if (distance <= 200 && p1IsToRight) { //P2 is to the right
                xv = xv * -0.25;
                x += 5;
                System.out.println("P2 Moving right!");
            } else if (distance <= 200) { //P2 is to the left
                xv = xv * -0.25;
                x -= 5;
                System.out.println("P2 Moving left!");
            }
        }
    }*/

    public void paint (Graphics2D g2d) { //holding out temporarily
        move();
    }
}
