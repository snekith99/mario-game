package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.allies.PrincessPeach;
import game.allies.Toad;
import game.enemies.Bowser;
import game.grounds.*;
import game.consumables.PowerStar;
import game.consumables.SuperMushroom;
import game.trees.Mature;
import game.trees.Sapling;
import game.trees.Sprout;
import game.waters.HealthFountain;
import game.waters.PowerFountain;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());
			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sapling(), new Sprout(), new Mature(), new Lava(), new WarpPipe(), new HealthFountain(), new PowerFountain());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				"..............H..................................##.............................",
				".........................................H....A.##..............................",
				".........+................H.............+#____####.................+............",
				".......................................+#_____###++.............................",
				"....................H..................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+...............H.....A..##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);

			// Add items
			gameMap.at(43, 10).addItem(new PowerStar());
			gameMap.at(43,9).addItem(new SuperMushroom());
			

			world.addGameMap(gameMap);
			List<String> map2 = Arrays.asList(
					".LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
					"L.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
					"LL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
					"LLL.LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL",
					"###################............................#####",
					"###################............................###.."
			);


			GameMap lavaMap = new GameMap(groundFactory, map2);
			world.addGameMap(lavaMap);

			// Add actors
			Actor toad = new Toad();
			Actor mario = new Player("Mario", 'm', 500);
			Actor peach = new PrincessPeach();
			Actor bowser = new Bowser(new Location(lavaMap,4, 2));

			// Add Peach to world
			gameMap.addActor(peach,lavaMap.at(51,5));
			// Add toad to world
			gameMap.addActor(toad, gameMap.at(44,11));
			// Add mario to world
			world.addPlayer(mario, gameMap.at(42, 10));
			// Add bowser to world
			gameMap.addActor(bowser, lavaMap.at(41, 5));
			// Run the world

			WarpPipe lavaWarpPipe = new WarpPipe();
			WarpPipe MushroomWarpPipe1 = new WarpPipe(lavaWarpPipe);
			WarpPipe MushroomWarpPipe2 = new WarpPipe(lavaWarpPipe);
			WarpPipe MushroomWarpPipe3 = new WarpPipe(lavaWarpPipe);
			WarpPipe MushroomWarpPipe4 = new WarpPipe(lavaWarpPipe);
			WarpPipe MushroomWarpPipe5 = new WarpPipe(lavaWarpPipe);


			lavaMap.at(0,0).setGround(lavaWarpPipe);
			gameMap.at(42,6).setGround(MushroomWarpPipe1);
			gameMap.at(1,1).setGround(MushroomWarpPipe2);
			gameMap.at(17,11).setGround(MushroomWarpPipe3);
			gameMap.at(32,17).setGround(MushroomWarpPipe4);
			gameMap.at(1,11).setGround(MushroomWarpPipe5);

			world.run();
	}
}
