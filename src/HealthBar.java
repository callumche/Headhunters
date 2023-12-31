import java.awt.*;

public class HealthBar {
    private int health = 0;
    private boolean p1; //true is player one health bar and false if player two
    public HealthBar(Boolean b){
        p1 = b;
    }

    public void paint(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        if (p1){
            health = Window.p1.getHealth();
            if (health<=0){
                g2d.fillRect(9,10,802,40);
                g2d.setColor(Color.RED);
                g2d.fillRect(10,11,800,38);
                Window.setWinner(false);
                Window.setFinished();
            } else {
                g2d.fillRect(9, 10, 802, 40);
                g2d.setColor(Color.GREEN);
                g2d.fillRect(10, 11, health * 8, 38);
                g2d.setColor(Color.RED);
                g2d.fillRect(health * 8 + 10, 11, 800 - health * 8, 38);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(9, 49, 400, 26);
                g2d.setColor(Color.YELLOW);
                if (Window.getTick() % 20 >= 10 && Window.p1.specialCount == 100) {
                    g2d.setColor(Color.MAGENTA);
                }
                if (Window.p1.specialCount > 0) {
                    g2d.fillRect(10, 50, Window.p1.specialCount * 4 - 1, 25);
                }
            }
        } else {
            health = Window.p2.getHealth();
            if (health<=0){
                g2d.fillRect(1099,10,802,40);
                g2d.setColor(Color.RED);
                g2d.fillRect(1100,11,800,38);
                Window.setWinner(true);
                Window.setFinished();
            } else {
                g2d.fillRect(1099, 10, 802, 40);
                g2d.setColor(Color.GREEN);
                g2d.fillRect(1100, 11, health * 8, 38);
                g2d.setColor(Color.RED);
                g2d.fillRect(health * 8 + 1100, 11, 800 - health * 8, 38);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(1501, 49, 400, 26);
                g2d.setColor(Color.YELLOW);
                if (Window.getTick() % 20 >= 10 && Window.p2.specialCount == 100) {
                    g2d.setColor(Color.MAGENTA);
                }
                if (Window.p2.specialCount > 0) {
                    g2d.fillRect(1502, 50, Window.p2.specialCount * 4 - 1, 25);
                }
            }
        }

    }

}
