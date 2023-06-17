package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.resets.ResetAction;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.trading.CoinManager;
import game.waters.Effect;

/**
 * Class representing the Player.
 */
public class Player extends Actor  implements Resettable, Effect {
	private final Menu menu = new Menu();
	private int invincibleEffectCounter = 10;
	private int fireEffectCounter = 20;
	private int damage = 1000;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);

		// Add Jumpable and hostile capability
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.JUMPABLE);
		this.addCapability(Status.CANTELEPORT);
		registerInstance();
	}

	/**
	 * Contains resettable features, and capabilities of the player that are used within the game once it is the player's turn to act
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (!(ResetManager.getInstance().hasReset())){
			actions.add(new ResetAction());
		}
		// Implementation of resettable features of player once reset action has been executed
		if (this.hasCapability(Status.RESETTABLE)){
			this.resetMaxHp(this.getMaxHp());
			this.removeCapability(Status.INVINCIBLE);
			this.removeCapability(Status.TALL);
			this.removeCapability(Status.RESETTABLE);
			this.removeCapability(Status.FIRE);
			this.removeCapability(Status.RESETTABLE);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		display.println(this + this.printHp() + " at " + "(" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")");

		// Mario is INVINCIBLE!
		if (this.hasCapability(Status.INVINCIBLE)){
			display.println(this + " is INVINCIBLE!");
			this.invincibleEffectCounter--;
			if (this.invincibleEffectCounter == 0){
				this.removeCapability(Status.INVINCIBLE);
			}
		}

		if (this.hasCapability(Status.FIRE)){
			display.println(this + " has FIRE ATTACK!");
			this.fireEffectCounter--;
			if (this.fireEffectCounter == 0){
				this.removeCapability(Status.FIRE);
			}
		}

		// return wallet
		display.println("Wallet: $" + CoinManager.getInstance().wallet());
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(damage, "punches");
	}

	@Override
	public void resetInstance() {
		this.addCapability(Status.RESETTABLE);
	}

	@Override
	public void healing(int healingPoints) {
		this.heal(healingPoints);
	}

	@Override
	public void attacking(int attackPoints) {
		this.damage += attackPoints;
	}
}

