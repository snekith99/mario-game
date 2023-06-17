package game.resets;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 */
public class ResetManager {

    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private final List<Resettable> resettableList;
    /**
     * Boolean value that returns true if every instance in the resettableList has been reset.
     */
    private boolean hasReset;
    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }
    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
        hasReset = false;
    }
    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(){
        for (Resettable resettable: resettableList){
            resettable.resetInstance();
        }
        cleanUp();
        hasReset = true;
    }
    /**
     * Add the Resettable instance to the list
     */
    public void appendResetInstance(Resettable resettable){
        resettableList.add(resettable);
    }

    /**
     * Clear resettableList
     */
    public void cleanUp(){
        resettableList.clear();
    }

    /**
     * Returns the value of hasReset
     */
    public Boolean hasReset() {
        return hasReset;
    }
}
