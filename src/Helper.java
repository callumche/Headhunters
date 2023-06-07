import java.util.TimerTask;

public class Helper extends TimerTask{
    public static int i = 0;
    public void run(){
        checkTime(1,i);
    }
    public static int checkTime(int num, int currentNum){
        i += num;
        return i;
    }
}
