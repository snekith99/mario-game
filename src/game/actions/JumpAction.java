package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.HighGround;
import game.Status;
import game.Utils;

public class JumpAction extends Action {
    private final HighGround highGround;
    private final String direction;
    private final Location location;


    /**
     * Constructor
     * @param highGround high ground
     * @param direction direction of high ground from actor
     * @param location location of high ground
     */
    public JumpAction(HighGround highGround, String direction, Location location) {
        this.highGround = highGround;
        this.direction = direction;
        this.location = location;
    }
    /**
     * Execute function for jump action, allows actor to jump with certain features with capabilities.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int damage = highGround.getFallDamage();
        // Allows actor who consumed super mushroom to have 100% successrate and no fall damage
        if (actor.hasCapability(Status.TALL)){
            MoveActorAction moveActor = new MoveActorAction(location, direction);
            moveActor.execute(actor, map);
            return actor + " jumped and is standing on top of " + highGround;
        }
        // allows only player to be able to jump
        if (actor.hasCapability(Status.JUMPABLE)) {
            // gains success rate and returns damage
            if (!(Utils.chance() <= highGround.getSuccessRate())) {
                actor.hurt(damage);
                return actor + " failed " + highGround + " jump. \n" + actor + " lost " + damage + " damage.";
            }
            // Allows actor to move to high ground
            MoveActorAction moveActorAction = new MoveActorAction(location, direction);
            moveActorAction.execute(actor, map);
            return actor + " jumped and is standing on top of " + highGround;
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + direction + " " + highGround + "(" + location.x() + "," + location.y() + ")";
    }
}
