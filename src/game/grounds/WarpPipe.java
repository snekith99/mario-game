package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;
import game.Utils;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.enemies.Goomba;
import game.enemies.PiranhaPlant;
import game.resets.Resettable;

public class WarpPipe extends HighGround implements Resettable {
    /**
     * displayChar for WarpPipe
     */
    private static final char DISPLAY_CHAR = 'C';
    /**
     * Name of Highground (WarpPipe)
     */
    private static final String NAME = "Warp Pipe";
    /**
     * fallDamage from WarpPipe
     */
    private static final int FALL_DAMAGE = 0;
    /**
     * successRate for WarpPipe
     */
    private static final int SUCCESS_RATE = 100;

    private boolean piranhaSpawn = false;

    public WarpPipe getDestWarpPipe() {
        return destWarpPipe;
    }

    private WarpPipe destWarpPipe;

    private Location location;


    public WarpPipe(WarpPipe destWarpPipe) {
        super(DISPLAY_CHAR, NAME, FALL_DAMAGE, SUCCESS_RATE);
        this.destWarpPipe = destWarpPipe;
        registerInstance();
    }

    public WarpPipe(){
        super(DISPLAY_CHAR, NAME, FALL_DAMAGE, SUCCESS_RATE);
        registerInstance();
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor()){
             if (actor.hasCapability(Status.CANTELEPORT)) {
                 actions.add(new TeleportAction(this));
             }
            return actions;
        }
        else {
            actions.add(new JumpAction(this, direction, location));
        }
        return actions;
    }

    public Location getLocation(){
        return this.location;
    }


    public void setDestPipe(WarpPipe destPipe) {
        this.destWarpPipe = destPipe;
    }


    @Override
    public void tick(Location location) {
        if (this.location == null){
            this.location = location;
        }

        if ((this.hasCapability(Status.RESETTABLE)) && (!this.location.containsAnActor())){
            location.addActor(new PiranhaPlant());
            this.removeCapability(Status.RESETTABLE);
        }

        if (!piranhaSpawn){
            location.addActor(new PiranhaPlant());
            piranhaSpawn = true;
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }


}
