package oop.bomberman.entities.powerup;

import oop.bomberman.entities.Entity;
import oop.bomberman.sprite.Sprite;

public class BombItem extends Entity {
	public BombItem(int x, int y) {
		super(32, 32, x, y, Sprite.powerup_bombs);
	}

	@Override
	public void remove() {
		super.remove();
	}
}
