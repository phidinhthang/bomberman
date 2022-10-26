package oop.bomberman.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.ImageView;
import oop.bomberman.App;
import oop.bomberman.animator.BalloomAnimator;
import oop.bomberman.animator.EnemyAnimator;
import oop.bomberman.animator.PlayerAnimator;
import oop.bomberman.controlller.Controller;
import oop.bomberman.controlller.EnemyComp;
import oop.bomberman.controlller.EnemyMover;
import oop.bomberman.controlller.Mover;
import oop.bomberman.entities.Bomb;
import oop.bomberman.entities.Brick;
import oop.bomberman.entities.Grass;
import oop.bomberman.entities.Movable;
import oop.bomberman.entities.Player;
import oop.bomberman.entities.Wall;
import oop.bomberman.entities.powerup.BombItem;
import oop.bomberman.entities.powerup.FlameItem;
import oop.bomberman.entities.powerup.SpeedItem;
import oop.bomberman.level.Level;
import oop.bomberman.sprite.Sprite;

public class Game {
	private Player player;
	private Controller playerController;
	private Mover playerMover;
	private Sprite playerSprite;
	private PlayerAnimator playerAnimator;

	private List<Brick> bricks = new ArrayList<>();
	private List<Wall> walls = new ArrayList<>();
  private List<Grass> grasses = new ArrayList<>();
	private List<Bomb> bombs = new ArrayList<>();
	private List<BombItem> bombItems = new ArrayList<>();
	private List<SpeedItem> speedItems = new ArrayList<>();
	private List<FlameItem> flameItems = new ArrayList<>();
	private List<EnemyComp> enemieComps = new ArrayList<>();

	private Level level;

	public Game() {}

	public void init() {
		this.level = new Level("levels/Level1.txt", this);
		System.out.println("level " + this.level);
		this.level.createEntities();

		this.player.addCollisions(this.bricks);
		this.player.addCollisions(this.walls);
		this.player.addCollisions(this.bombs);
		this.player.addCollisions(this.flameItems);
		this.player.addCollisions(this.bombItems);
		this.player.addCollisions(this.speedItems);

		this.enemieComps.forEach(enemy -> {
			enemy.getEntity().addCollisions(this.bricks);
			enemy.getEntity().addCollisions(this.walls);
			enemy.getEntity().addCollisions(this.bombs);
		});

		this.playerAnimator = new PlayerAnimator(this.player.getSprite());

		App.setWindowWidth(this.level.getWidth() * 16 * App.scale);
		App.setWindowHeight(this.level.getHeight() * 16 * App.scale);
	}

	public void update() {
		this.bombs.removeIf(bomb -> bomb.isRemoved() == true);
		this.bricks.removeIf(brick -> brick.isRemoved() == true);
		this.enemieComps.removeIf(enemy -> enemy.getEntity().isRemoved() == true);
		this.player.getCollisions().removeIf(collision -> collision.isRemoved() == true);
		this.playerMover.update();
    this.playerAnimator.update(playerMover);

		this.enemieComps.forEach(enemy -> {
			enemy.getMover().update();
			enemy.getAnimator().update(enemy.getMover());
			enemy.getEntity().getCollisions().removeIf(collision -> collision.isRemoved() == true);
		});
		this.bombs.forEach(bomb -> bomb.update(this.player.getFlameLength()));

		System.out.println(playerController.getInput());
		if (playerController.getInput().contains("ENTER")) {
			System.out.println("enter");
			this.addBomb();
		}
	}

	public void draw() {
		for (int i = 0; i < this.grasses.size(); i++) {
			Grass grass = this.grasses.get(i);
			grass.draw();
		}
		
		for (int i = 0; i < this.bricks.size(); i++) {
			Brick brick = this.bricks.get(i);
			brick.draw();
		}

		for (int i = 0; i < this.walls.size(); i++) {
			Wall wall = this.walls.get(i);
			wall.draw();
		}
		
		for (int i = 0; i < this.bombItems.size(); i++) {
			BombItem bombItem = this.bombItems.get(i);
			bombItem.draw();
		}

		for (int i = 0; i < this.speedItems.size(); i++) {
			SpeedItem speedItem = this.speedItems.get(i);
			speedItem.draw();
		}

		for (int i = 0; i < this.flameItems.size(); i++) {
			FlameItem flameItem = this.flameItems.get(i);
			flameItem.draw();
		}

		for (int i = 0; i < this.bombs.size(); i++) {
			Bomb bomb = this.bombs.get(i);
			bomb.draw();
			bomb.getSprite().imageView.toFront();

			if (!bomb.isRendered && bomb.explosed) {
				bomb.getFrames().forEach(frame -> {
					frame.draw();
				});
				bomb.isRendered = true;
			}
		}


		for (int i = 0; i < this.enemieComps.size(); i++){
			EnemyComp enemyComp = this.enemieComps.get(i);
			enemyComp.getAnimator().updateView(enemyComp.getMover());
		}

		this.player.getSprite().imageView.toFront();
		this.playerAnimator.updateView(playerMover);
	}

	public void addBrick(Brick brick) {
		this.bricks.add(brick);
	}

	public void addGrass(Grass grass) {
		this.grasses.add(grass);
	}

	public void addWall(Wall wall) {
		this.walls.add(wall);
	}

	public void addBombItem(BombItem bombItem) {
		this.bombItems.add(bombItem);
	}

	public void addFlameItem(FlameItem flameItem) {
		this.flameItems.add(flameItem);
	}

	public void addSpeedItem(SpeedItem speedItem) {
		this.speedItems.add(speedItem);
	}

	public void addEnemy(Movable enemy) {
		EnemyMover mover = new EnemyMover(enemy);
		EnemyAnimator animator = new BalloomAnimator(enemy.getSprite());
		this.enemieComps.add(
			new EnemyComp(enemy, mover, animator)	
		);
	}

	public void setPlayer(Player player) {
		this.player = player;
		this.playerController = new Controller(App.scene);
    this.playerMover = new Mover(this.playerController, this.player);
	}

	public void addBomb() {
		if (!this.player.canPlaceBomb()) return;
		this.player.placeBomb();
		
		int tileX = (int) (this.player.getX() + 12) / 32;
		int tileY = (int) (this.player.getY() + 8) / 32;
		int bombPositionX = tileX * 32;
		int bombPosisionY = tileY * 32;

		Bomb bomb = new Bomb(bombPositionX, bombPosisionY);
		this.bombs.add(
			bomb
		);
		bomb.addCollisions(this.walls);
		bomb.addDestroyables(this.bricks);
		bomb.setOwner(this.player);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				player.addCollision(bomb);
				enemieComps.forEach(enemy -> {
					enemy.getEntity().addCollision(bomb);
					bomb.addDestroyable(enemy.getEntity());
				});
			}
		}, 400);
	}
}
