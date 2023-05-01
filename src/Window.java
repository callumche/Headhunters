import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {
    public Window(){

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HeadHunters");
        Window win = new Window();
        frame.add(win);

    }
}