package game.trees;

import game.Status;
import game.grounds.HighGround;
import game.resets.Resettable;

abstract public class Tree extends HighGround implements Resettable {

    int counter;
    /**
     * Constructor
     */
    public Tree(char displayChar, String name, int fallDamage, int successRate) {
        super(displayChar, name, fallDamage, successRate);

        // reset Tree
        registerInstance();
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }
}
