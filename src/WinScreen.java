import java.awt.*;

public class WinScreen {
    private boolean p1Winner = false;
    public WinScreen(){

    }
    public void paint(Graphics2D g2d){
        p1Winner = Window.getWinner();

    }
}
