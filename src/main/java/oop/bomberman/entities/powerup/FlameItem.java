package oop.bomberman.entities.powerup;

import oop.bomberman.entities.Entity;
import oop.bomberman.sprite.Sprite;

public class FlameItem extends Entity {
	public FlameItem(int x, int y) {
		super(32, 32, x, y, Sprite.powerup_flames);
	}
}
