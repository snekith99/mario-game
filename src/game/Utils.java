package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    public static final int HEALING_POINTS = 50;
    public static final int ATTACKING_POINTS = 15;

    /**
     * 1-100 Chance
     * @return random int between 1 and 100
     */
    public static int chance() {
        Random r = new Random();
        int low = 1;
        int high = 100;
        return (r.nextInt(high - low) + low);
    }

    /**
     * Used for spawning random Sprouts
     * @param high high bound for chance
     * @return random int between 0 and high (exclusive)
     */
    public static int randSpawn(int high) {
        Random r = new Random();
        return (r.nextInt(high));
    }

    /**
     * method to randomise actor monologue
     * @param high
     * @return random int between 0 and high exclusive
     */
    public static int actorMonologueRandomize(int high) {
        Random r = new Random();
        return r.nextInt(high);
    }
}

