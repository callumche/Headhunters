import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public abstract class Character {
    protected boolean left = false, right = false;
    public int x = 480, y = 300;
    public double xv = 0, yv = 0, xa = 0, ya = 0; //fuck you, im making this public (i don't want a trillion getters/setters) - FIX LATER?
    protected int speedCap = 20, jumpCount = 0;
    protected boolean lookingDirection = true, altDirection = true; //false = left, true = right
    protected boolean playerNo; //true = P1, false = P2
    protected int attackState = 0; //0 = not attacking, 1 = biting, 2 = spitting, 3 = headbutt, 4 = hurt, 5 = special, 6 = starting/dark
    protected BufferedImage neutral, jump, bite1, bite2, headbutt, hurt, special1, special2, spit1, spit2, evil;
    protected BufferedImage current;
    protected long markerFrame = 0;
    public int health = 100, charType;
    protected int hurtDur = 0;
    protected int specialCount = 0;

    public void start() {
        lookingDirection = playerNo;
        if (attackState != 6) {
            attackState = 6;
            markerFrame = Window.getTick();
            if (playerNo) {
                xv = -5;
            } else {
                xv = 5;
            }
            changeImage("evil");
        }

        if (Window.getTick() - markerFrame >= 180) {
            attackState = 0;
        }
    }

    public void increaseSpecial(int amount) {
        if (specialCount + amount >= 100) {
            specialCount = 100;
        } else {
            specialCount += amount;
        }
    }

    public int getState() {
        return attackState;
    }

//    IDEA: Make separate combo tick that decreases damage, to discourage move spamming
//    not even if it lands, just spamming decreases your damage output (by multiplier added to applyDamage();

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
        if (attackState == 2) {
            spit();
        }
        if (attackState == 3) {
            headbutt();
        }
        if (attackState == 4) {
            hurt(hurtDur);
        }
        if (attackState == 5) {
            special();
        }
        if (attackState == 6) {
            start();
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
            case "headbutt":
                current = headbutt;
                break;
            case "spit1":
                current = spit1;
                break;
            case "spit2":
                current = spit2;
                break;
            case "special1":
                current = special1;
                break;
            case "special2":
                current = special2;
                break;
            case "evil":
                current = evil;
                break;
            default:
                current = neutral;
                System.out.println("Image change failed, defaulting to neutral for P" + playerNo);
                break;
        }
    }

    public void special(){
        if (specialCount == 100) {
            specialCount = 0;
        }
    }

    public void headbutt(){
        if (attackState == 0) {
            attackState = 3;
            markerFrame = Window.getTick();
        }
        if (attackState == 3 && Window.getTick() - markerFrame == 10) {
            changeImage("headbutt");
            if (playerNo) { //this nest is hurting me send help
                if (Window.isHit(playerNo)) {
                    if (getDirection()) { //knockback
                        Window.p2.xv = 10;
                        Window.p2.yv = 5;
                    } else {
                        Window.p2.xv = -10;
                        Window.p2.yv = 5;
                    }
                    Window.p2.applyDamage(5); //damage
                    Window.p2.hurt(10);
                    increaseSpecial(10);
                }
            } else {
                if (Window.isHit(playerNo)) {
                    if (getDirection()) { //knockback
                        Window.p1.xv = 10;
                        Window.p1.yv = 5;
                    } else {
                        Window.p1.xv = -10;
                        Window.p1.yv = 5;
                    }
                    Window.p1.applyDamage(5); //damage
                    Window.p1.hurt(10);
                    increaseSpecial(10);
                }
            }
        }
        if (attackState == 3 && Window.getTick() - markerFrame >= 30) {
            attackState = 0;
        }
    }

    public void spit(){
        if (attackState == 0) {
            attackState = 2;
            markerFrame = Window.getTick();
            changeImage("spit1");
        }
        if (Window.getTick() - markerFrame == 10) {
            changeImage("spit2");
            Window.spits.add(new Spit(x, y, lookingDirection, playerNo));
        }
        if (Window.getTick() - markerFrame >= 30) {
            attackState = 0;
        }
    }

    public void bite(){
        if (attackState == 0){ //initiate attack
            attackState = 1;
            markerFrame = Window.getTick();
            changeImage("bite1");
        }
        if (attackState == 1 && Window.getTick() - markerFrame == 30) { //actual bite 30 frames later
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
                    Window.p2.applyDamage(20); //damage
                    Window.p2.hurt(40);
                    increaseSpecial(40);
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
                    Window.p1.applyDamage(20); //damage
                    Window.p1.hurt(40);
                    increaseSpecial(40);
                }
            }
        }
        if (attackState == 1 && Window.getTick() - markerFrame >= 50) {
            attackState = 0;
        }
    }

    public void hurt(int duration) {
        hurtDur = duration;
        if (attackState != 4) {
            attackState = 4;
            markerFrame = Window.getTick();
            changeImage("hurt");
            altDirection = lookingDirection;
        }
        lookingDirection = altDirection;
        if (Window.getTick() - markerFrame >= hurtDur) {
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
        if (playerNo && attackState != 4) { //P1, WASD
            if (e.getKeyCode() == KeyEvent.VK_A) {
                left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (jumpCount < 2) {
                    jumpCount++;
                    yv = 30;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_M){
                bite();
            }
            if (e.getKeyCode() == KeyEvent.VK_B) {
                headbutt();
            }
            if (e.getKeyCode() == KeyEvent.VK_N) {
                spit();
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                special();
            }
        } else if (attackState != 4) {
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
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 || e.getKeyCode() == KeyEvent.VK_SHIFT) {
                bite();
            }
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                headbutt();
            }
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                spit();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                special();
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

    public void paint (Graphics2D g2d) { //holding out temporarily
        move();
    }
}
