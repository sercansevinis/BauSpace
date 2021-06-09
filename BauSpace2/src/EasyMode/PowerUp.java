package EasyMode;

import java.util.Random;

public class PowerUp {

    protected int duration;

    public PowerUp() {
        Random rand = new Random();
        duration = rand.nextInt(10);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
