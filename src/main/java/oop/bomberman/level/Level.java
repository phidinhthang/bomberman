package oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

import oop.bomberman.App;
import oop.bomberman.entities.Brick;
import oop.bomberman.entities.Grass;
import oop.bomberman.entities.Movable;
import oop.bomberman.entities.Wall;
import oop.bomberman.game.Game;
import oop.bomberman.entities.enemy.Balloom;

public class Level {
	private String[] lineTiles;
	private Game game;
	private int level;
	private int width;
	private int height;

	public Level(String path, Game game) {
		try {
			this.game = game;
			URL absPath = Level.class.getResource("/" + path);

			BufferedReader in = new BufferedReader(
				new InputStreamReader(absPath.openStream())
			);

			String data = in.readLine();
			StringTokenizer tokens = new StringTokenizer(data);
			this.level = Integer.parseInt(tokens.nextToken());
			this.height = Integer.parseInt(tokens.nextToken());
			this.width = Integer.parseInt(tokens.nextToken());

			this.lineTiles = new String[this.height];
			
			for (int i = 0; i < this.height; i++) {
				this.lineTiles[i] = in.readLine().substring(0, this.width);
			}

		} catch (IOException e) {
			System.out.println("level error " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("level null pointer " + e.getMessage());
			System.out.println("level localiszed message " + e.getLocalizedMessage());
			System.out.println("level erro stack trace");
			e.printStackTrace();
		}
	}

	public void createEntities() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				System.out.println("this.lineTiles[y]" + this.lineTiles[y] + " length = " + this.lineTiles[y].length());
				addLevelEntity(this.lineTiles[y].charAt(x), x, y);
			}
		}
	}

	public void addLevelEntity(char c, int x, int y) {

		System.out.println("game " + this.game);
		if (c == '#') {
			this.game.addWall(new Wall(x * 16 * App.scale, y * 16 * App.scale));
		} else if (c == 'p') {
			this.game.setPlayer(
				new Movable(12 * App.scale, 12 * App.scale, (x * 16 + 2) * App.scale , (y * 16 + 4) * App.scale)
			);
		} else if (c == '*') {
			this.game.addBrick(new Brick(x * 16 * App.scale, y * 16 * App.scale));
		} else if (c == '1') {
			this.game.addEnemy(new Balloom(x * 16 * App.scale, y * 16 * App.scale));
		}
		Grass grass = new Grass(x * 16 * App.scale, y * 16 * App.scale);
		grass.getSprite().imageView.toBack();
		this.game.addGrass(grass);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
