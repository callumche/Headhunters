import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinScreen {
    private boolean p1Winner = false;
    private BufferedImage winner;
    private BufferedImage loser;

    private Font f = null;
    public WinScreen(){}

    private BufferedImage winnner() { //Work in Progress

        p1Winner = Window.getWinner();

        if (p1Winner) {

            switch (Window.p1.charType) {

                case 0: //If Julian is player1 and wins
                    try {
                        System.out.println("Julian Win!");
                        winner = ImageIO.read(new File("res//Sprites//JulianWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 1: //If Callum is player1 and wins
                    try {

                        System.out.println("Callum Win!");
                        winner = ImageIO.read(new File("res\\Sprites\\CallumWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 2: //If Naufil is player1 and wins
                    try {

                        System.out.println("Naufil Win!");
                        winner = ImageIO.read(new File("res\\Sprites\\NaufilWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                default: //Error
                    System.out.println("Winner is not recorded");


            }

        }

        return winner;

    }
    public void paint(Graphics2D g2d){
        p1Winner = Window.getWinner();
        f = new Font("Comic Sans MS", Font.BOLD, 72);
        g2d.setFont(f);
        if (p1Winner){
            g2d.setColor(Color.red);
            g2d.drawString("Player 1 Wins!",700,200);
        } else {
            g2d.setColor(Color.blue);
            g2d.drawString("Player 2 Wins!",700,200);
        }

        g2d.drawImage(winner, 300, 500, 500, 500, null);

    }
}
