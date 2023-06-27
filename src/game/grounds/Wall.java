package game.grounds;

public class Wall extends HighGround {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#',"Wall",20,80);
	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
