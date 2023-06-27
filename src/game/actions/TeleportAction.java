package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.WarpPipe;

public class TeleportAction extends Action {

    private WarpPipe sourcePipe;

    /**
     * Constructor
     * @param sourcePipe
     */

    public TeleportAction(WarpPipe sourcePipe) {
        this.sourcePipe = sourcePipe;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (sourcePipe.getDestWarpPipe().getLocation().containsAnActor()){
            map.removeActor(sourcePipe.getDestWarpPipe().getLocation().getActor());
        }
        map.moveActor(actor, sourcePipe.getDestWarpPipe().getLocation());
        sourcePipe.getDestWarpPipe().setDestPipe(sourcePipe);

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports " ;
    }
}
