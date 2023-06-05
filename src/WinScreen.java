import java.awt.*;

public class WinScreen {
    private boolean p1Winner = false;
    public WinScreen(){

    }
    public void paint(Graphics2D g2d){
        p1Winner = Window.getWinner();
        if (p1Winner){
            g2d.drawString("Player 1 win",500,500);
        } else {
            g2d.drawString("Player 2 win",500,500);
        }
    }
}
