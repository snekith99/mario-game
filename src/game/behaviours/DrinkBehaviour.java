package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.waters.Fountain;

public class DrinkBehaviour implements Behaviour{

    /**
     * Grants Actor the drink behaviour, allowing them to stand on the fountain and gain the benefits
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Status.FOUNTAIN) && (!destination.containsAnActor())) {
                return new MoveActorAction(destination, exit.getName());
            }
        }
        return null;
    }
}
