package oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;

import oop.bomberman.App;
import oop.bomberman.sprite.Sprite;

public class Bomb extends Entity {
	private int timeToExplosing = 120;
	private int timeAfterExplosion = 60;
	private int frameLength = 2;
	private int currentTime = 0;
	public boolean explosed = false;
	public boolean removed = false;
	private List<Frame> frames = new ArrayList<>();
	private List<Entity> collisions = new  ArrayList<>();
	private List<Entity> destroyables = new ArrayList<>();
	public boolean isRendered = false;

	public Bomb(int x, int y) {
		super(32, 32, x, y, Sprite.bomb);
	}

	public void update() {
		this.destroy();
		if (!this.explosed) {
			if (this.currentTime < this.timeToExplosing) {
				this.currentTime++;
			} else {
				this.explosed = true;
				this.createFrames();
				this.getSprite().imageView.setImage(Sprite.bomb_exploded);
				this.currentTime = 0;
			}
		} else if (!this.removed) {
			if (this.currentTime < this.timeAfterExplosion) {
				this.currentTime++;
			} else {
				this.removed = true;
				App.root.getChildren().remove(this.getSprite().imageView);
				this.frames.forEach(frame -> App.root.getChildren().remove(frame.getSprite().imageView));
				this.frames.clear();
			}
		}
	}

	public void destroy() {
		this.destroyables.forEach(destroyable -> {
			this.frames.forEach(frame -> {
				if (destroyable.getTileX() == frame.getTileX() && destroyable.getTileY() == frame.getTileY()) {
					destroyable.remove();
				}
			});
		});
	}

	public void createFrames() {
		int currentFrameLevel = 1;
		int bombTileX = this.getTileX();
		int bombTileY = this.getTileY();
		boolean hasCollisionUp = false;
		boolean hasCollisionDown = false;
		boolean hasCollisionLeft = false;
		boolean hasCollisionRight = false;

		while (currentFrameLevel++ <= this.frameLength) {
			int frameUpTileX = bombTileX;
			int frameUpTileY = bombTileY - 1;
			Frame frameUp = Frame.createFromTilePosition(frameUpTileX, frameUpTileY, Sprite.explosion_vertical);
			int frameLeftTileX = bombTileX - 1;
			int frameLeftTileY = bombTileY;
			Frame frameLeft = Frame.createFromTilePosition(frameLeftTileX, frameLeftTileY, Sprite.explosion_horizontal);
			int frameRightTileX = bombTileX + 1;
			int frameRightTileY = bombTileY;
			Frame frameRight = Frame.createFromTilePosition(frameRightTileX, frameRightTileY, Sprite.explosion_horizontal);
			int frameDownTileX = bombTileX;
			int frameDownTileY = bombTileY + 1;
			Frame frameDown = Frame.createFromTilePosition(frameDownTileX, frameDownTileY, Sprite.explosion_vertical);
			for (Entity collision: this.collisions) {
				if (collision.hasSameTilePosition(frameUp)) {
					hasCollisionUp = true;
				} else if (collision.hasSameTilePosition(frameLeft)) {
					hasCollisionLeft = true;
				} else if (collision.hasSameTilePosition(frameRight)) {
					hasCollisionRight = true;
				} else if (collision.hasSameTilePosition(frameDown)) {
					hasCollisionDown = true;
				}
			}

			if (!hasCollisionUp) {
				this.frames.add(frameUp);
			}

			if (!hasCollisionLeft) {
				this.frames.add(frameLeft);
			}

			if (!hasCollisionDown) {
				this.frames.add(frameDown);
			}

			if (!hasCollisionRight) {
				this.frames.add(frameRight);
			}
		}
	}

	public List<Frame> getFrames() {
		return this.frames;
	}

	public void addCollisions(List<? extends Entity> entities) {
		this.collisions.addAll(entities);
	}

	public void addDestroyables(List<? extends Entity>destroyables) {
		this.destroyables.addAll(destroyables);
	}
}
