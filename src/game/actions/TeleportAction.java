package game.actions;

import engine.actions.Action;
import engine.actions.MoveActorAction;
import engine.actors.Actor;
import engine.positions.GameMap;
import engine.positions.Location;
import game.grounds.WarpPipe;

public class TeleportAction extends Action {

    private WarpPipe sourcePipe;

    /**
     * Constructor
     * 
     * @param sourcePipe
     */

    public TeleportAction(WarpPipe sourcePipe) {
        this.sourcePipe = sourcePipe;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (sourcePipe.getDestWarpPipe().getLocation().containsAnActor()) {
            map.removeActor(sourcePipe.getDestWarpPipe().getLocation().getActor());
        }
        map.moveActor(actor, sourcePipe.getDestWarpPipe().getLocation());
        sourcePipe.getDestWarpPipe().setDestPipe(sourcePipe);

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports ";
    }
}
