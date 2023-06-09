import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinScreen {
    private boolean p1Winner = Window.getWinner();
    private BufferedImage winner;
    private BufferedImage loser;

    private Font f = null;
    public WinScreen(){}

    public static void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            Window.restart();
            System.out.println("k");
        }
    }
    private BufferedImage winner() {

        if (p1Winner) {

            switch (Window.p1.charType) {

                case 0: //If Julian is player1 and wins
                    try {
                        winner = ImageIO.read(new File("res//Processed Shots//JulianWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 1: //If Callum is player1 and wins
                    try {

                        winner = ImageIO.read(new File("res\\Processed Shots\\CallumWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 2: //If Naufil is player1 and wins
                    try {

                        winner = ImageIO.read(new File("res\\Processed Shots\\NaufilWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                default: //Error
                    System.out.println("Winner is not recorded");


            }

        } else {

            switch (Window.p2.charType) {

                case 0: //If Julian is player2 and wins
                    try {
                        winner = ImageIO.read(new File("res//Processed Shots//JulianWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 1: //If Callum is player2 and wins
                    try {

                        winner = ImageIO.read(new File("res\\Processed Shots\\CallumWin.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 2: //If Naufil is player2 and wins
                    try {

                        winner = ImageIO.read(new File("res\\Processed Shots\\NaufilWin.PNG"));

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

    private BufferedImage loser() {

        if (!p1Winner) {

            switch (Window.p1.charType) {

                case 0: //If Julian is player1 and loses
                    try {

                        loser = ImageIO.read(new File("res//Processed Shots//JulianLose.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 1: //If Callum is player1 and loses
                    try {

                        loser = ImageIO.read(new File("res\\Processed Shots\\CallumLose.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 2: //If Naufil is player1 and loses
                    try {

                        loser = ImageIO.read(new File("res\\Processed Shots\\NaufilLose.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                default: //Error
                    System.out.println("Loser is not recorded");


            }

        } else  if (p1Winner){

            switch (Window.p2.charType) {

                case 0: //If Julian is player2 and loses
                    try {
                        loser = ImageIO.read(new File("res//Processed Shots//JulianLose.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 1: //If Callum is player2 and loses
                    try {

                        loser = ImageIO.read(new File("res\\Processed Shots\\CallumLose.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                case 2: //If Naufil is player2 and loses
                    try {

                        loser = ImageIO.read(new File("res\\Processed Shots\\NaufilLose.PNG"));

                    } catch (IOException e) {

                        System.out.println("Invalid Image");

                    }
                    break;

                default: //Error
                    System.out.println("Loser is not recorded");


            }

        }

        return loser;

    }

    public void paint(Graphics2D g2d){

        p1Winner = Window.getWinner();

        f = new Font("Comic Sans MS", Font.BOLD, 72);
        g2d.setFont(f);

        if (p1Winner){

            g2d.setColor(new Color(139, 0, 0));
            g2d.fillRect(0, 0, 1920, 1080);

            g2d.setColor(Color.red);
            g2d.drawString("Player 1 Wins!",700,200);

            g2d.drawImage(winner(), 100, 300, 700, 700, null);
            g2d.drawImage(loser(), 1000, 400, 500, 500, null);


        } else {

            g2d.setColor(new Color(0, 0, 54));
            g2d.fillRect(0, 0, 1920, 1080);

            g2d.setColor(Color.blue);
            g2d.drawString("Player 2 Wins!",700,200);

            g2d.drawImage(winner(), 1000, 300, 700, 700, null);
            g2d.drawImage(loser(), 200, 400, 500, 500, null);

        }

    }
}
