import java.util.TimerTask;

public class Timer {
    java.util.Timer timer = new java.util.Timer();
    TimerTask task = new Helper();

    public Timer(int delay, int period) {timer.schedule(task, delay, period);}

    public void reset(int delay, int period){
        timer.schedule(task, delay, period);
    }
}
