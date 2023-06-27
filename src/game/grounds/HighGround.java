package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.trading.Coin;

public abstract class HighGround extends Ground {
    /**
     * fallDamage from HighGround
     */
    private final int fallDamage;
    /**
     * successRate of HighGround
     */
    private final int successRate;
    /**
     * Name of HighGround
     */
    private final String name;
    /**
     * Constructor
     */
    public HighGround(char displayChar, String name, int fallDamage, int successRate) {
        super(displayChar);
        this.name = name;
        this.fallDamage = fallDamage;
        this.successRate = successRate;
    }

    /**
     * Getter for fallDamage
     * @return fallDamage from HighGround
     */
    public int getFallDamage(){
        return fallDamage;
    }

    /**
     * Getter for successRate
     * @return successRate of HighGround
     */
    public int getSuccessRate(){
        return successRate;
    }

    /**
     * Actions that are given to the actor
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return action list
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {

        // Creating actionList
        ActionList actions = new ActionList();

        if (location.containsAnActor()){

            // check if actor is invincible
            if (actor.hasCapability(Status.INVINCIBLE)){

                // Destroy to dirt and drop $5
                location.setGround(new Dirt());
                location.addItem(new Coin(5));
            }
            return actions;
        }

        // Regular JumpAction otherwise
        if (!(actor.hasCapability(Status.INVINCIBLE))||!(actor.hasCapability(Status.FLYING))){
            actions.add(new JumpAction(this, direction, location));
        }
        return actions;
    }

    @Override
    public String toString() {
        // Returns name of HighGround
        return name;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE)||actor.hasCapability(Status.FLYING);
    }
}
