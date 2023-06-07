import java.awt.*;

public class Arena {

    private int arena;
    private boolean isInitialized = false;
    public Arena(){

    }

    public void paint(Graphics2D g2d){

        if (!isInitialized){
            Timer timer = new Timer(0,1000);
            isInitialized = true;
        }

        arena = Window.getArenaSelect();

        if (arena == 0) {
            g2d.setColor(Color.cyan);
            g2d.fillRect(0,0,Window.resX,Window.resY);
            g2d.setColor(Color.yellow);
            g2d.fillOval(100, 200, 300, 300);

            g2d.setColor(Color.green);
            for (int i = 0; i <= Window.resX; i += 15) {

                g2d.fillRect(i, 950, 5, 150);

            }

        } else if (arena == 1) {
            g2d.setColor(Color.darkGray);
            g2d.fillRect(0,0,Window.resX,Window.resY);

            g2d.setColor(Color.white);
            g2d.fillOval(100, 200, 300, 300);

            g2d.fillOval(1800, 100, 10, 10);
            g2d.fillOval(1650, 150, 10, 10);
            g2d.fillOval(1200, 75, 5, 5);

        } else {
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(0,0,Window.resX,Window.resY);


        }

        int time = (Helper.checkTime(0,0));
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        g2d.drawString(""+time,1000,30);

    }
}
