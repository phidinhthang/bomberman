package oop.bomberman.entities;

import oop.bomberman.App;
import oop.bomberman.entities.powerup.BombItem;
import oop.bomberman.entities.powerup.FlameItem;
import oop.bomberman.entities.powerup.SpeedItem;

public class Player extends Movable {
	public Player(int x, int y) {
		super(12 * App.scale, 12 * App.scale, x, y);
	}
	private int maxBombCount = 1;
	private int flameLength = 1;
	private int remainingBombCount = maxBombCount;
	private long lastPlaceBombAt = 0;
	private int placeBombWaitingTime = 400;

	public void increaseFlameLength() {
		this.flameLength++;
	}

	public int getFlameLength() {
		return this.flameLength;
	}

	public void placeBomb() {
		if (this.canPlaceBomb()) {
			this.remainingBombCount--;
			this.lastPlaceBombAt = System.currentTimeMillis();
		}
	}

	public void afterBombExplosed() {
		this.remainingBombCount++;
	}

	public boolean canPlaceBomb() {
		if (remainingBombCount >= 1 && System.currentTimeMillis() - this.lastPlaceBombAt >= this.placeBombWaitingTime) {
			return true;
		}
		return false;
	}

	@Override
	public void onCollision(Entity entity) {
		if (entity instanceof BombItem) {
			this.maxBombCount++;
			this.remainingBombCount++;
			entity.remove();
		} else if (entity instanceof SpeedItem) {
			this.setVelocity(this.getVelocity() + 0.75);
			entity.remove();
		} else if (entity instanceof FlameItem) {
			this.increaseFlameLength();
			entity.remove();
		}
	}
}
