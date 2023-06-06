import java.awt.*;

public class Arena {

    private int arena;
    public Arena(){

    }

    public void paint(Graphics2D g2d){
        arena = Window.getArenaSelect();
        if (arena==0){
            g2d.setColor(Color.yellow); //add pictures later
        } else if (arena == 1){
            g2d.setColor(Color.darkGray); //add pictures later
        } else {
            g2d.setColor(Color.green); //add pictures later
        }
        g2d.fillRect(0,0,Window.resX,Window.resY);

        if (arena == 0) {



        } else if (arena == 1) {

            g2d.setColor(Color.white);
            g2d.fillOval(100, 200, 300, 300);

            g2d.fillOval(1800, 100, 10, 10);
            g2d.fillOval(1650, 150, 10, 10);
            g2d.fillOval(1200, 75, 5, 5);

        } else {



        }
    }
}
