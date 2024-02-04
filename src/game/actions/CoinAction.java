package game.actions;

import engine.actions.Action;
import engine.actors.Actor;
import engine.positions.GameMap;
import game.trading.Coin;
import game.trading.CoinManager;

public class CoinAction extends Action {
    private final Coin coin;

    public CoinAction(Coin coin) {
        this.coin = coin;
    }

    /**
     * removes coin and adds the amount of coin to the coin manager
     * 
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return message actor gains amount
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        CoinManager.getInstance().addAmount(coin.getAmount());
        return actor + " gains " + coin.getAmount();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + coin + "($" + coin.getAmount() + ")";
    }
}
