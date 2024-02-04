package game.behaviours;

import engine.actions.Action;
import engine.actions.MoveActorAction;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import game.Status;
import game.waters.Fountain;

public class DrinkBehaviour implements Behaviour {

    /**
     * Grants Actor the drink behaviour, allowing them to stand on the fountain and
     * gain the benefits
     * 
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
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
