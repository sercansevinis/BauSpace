package EasyMode;

import java.util.Random;

public class Slow extends PowerUp {

    private int amount;

    public Slow() {
        Random rand = new Random();
        amount = rand.nextInt(3) - 4;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}