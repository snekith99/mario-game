package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.items.Fire;

public class AttackBehaviour implements Behaviour{
    /**
     * target to be attacked
     */
    private final Actor target;
    public AttackBehaviour(Actor subject) {this.target = subject;}

    /**
     * Enemies use this to get an attack action if the player is within one of its exits.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    if (actor.hasCapability(Status.CANUSEFIRE)){
                        destination.addItem(new Fire());
                    }
                    return new AttackAction(target, exit.getName());

                }
            }
        }

        return null;
    }
}
