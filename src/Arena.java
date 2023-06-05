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
            g2d.setColor(Color.BLUE); //add pictures later
        } else {
            g2d.setColor(Color.green); //add pictures later
        }
        g2d.fillRect(0,0,Window.resX,Window.resY);
    }
}
