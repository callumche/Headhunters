import java.awt.*;

public class HealthBar {
    private int health;
    private boolean p1; //true is player one health bar and false if player two
    public HealthBar(Boolean b){
        p1 = b;
    }

    public void paint(Graphics2D g2d){
        health = 0;
        g2d.setColor(Color.BLACK);
        if (p1){
            health = Window.p1.getHealth();
            if (health<=0){
                g2d.fillRect(9,10,802,40);
                g2d.setColor(Color.RED);
                g2d.fillRect(10,11,800,38);
            } else {
                g2d.fillRect(9, 10, 802, 40);
                g2d.setColor(Color.GREEN);
                g2d.fillRect(10, 11, health * 8, 38);
                g2d.setColor(Color.RED);
                g2d.fillRect(health * 8 + 10, 11, 800 - health * 8, 38);
            }
        } else {
            health = Window.p2.getHealth();
            if (health<=0){
                g2d.fillRect(1099,10,802,40);
                g2d.setColor(Color.RED);
                g2d.fillRect(1100,11,800,38);
            } else {
                g2d.fillRect(1099, 10, 802, 40);
                g2d.setColor(Color.GREEN);
                g2d.fillRect(1100, 11, health * 8, 38);
                g2d.setColor(Color.RED);
                g2d.fillRect(health * 8 + 1100, 11, 800 + health * 8, 38);
            }
        }

    }

}
