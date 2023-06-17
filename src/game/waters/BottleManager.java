package game.waters;

import java.util.Stack;

public class BottleManager {
    /**
     * Creating instance
     */
    private static BottleManager instance;
    /**
     * Stack of water in the bottle
     */
    private Stack<Water> bottles = new Stack<>();

    /**
     * To get instance of the bottlemanager
     * @return instance of bottlemanager
     */
    public static BottleManager getInstance() {
        if (instance == null) {
            instance = new BottleManager();
        }
        return instance;
    }

    /**
     * Getter for bottle
     * @return bottle
     */
    public Stack<Water> getBottle(){
        return bottles;
    }

    /**
     * Refill water
     * @param water water to be refilled
     */
    public void refill(Water water){
        bottles.push(water);
    }

    /**
     * Drink water
     * @return water that is being drunk
     */
    public Water drink(){
        return bottles.pop();
    }
}
