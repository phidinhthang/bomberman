package oop.bomberman.entities.powerup;

import oop.bomberman.entities.Entity;
import oop.bomberman.sprite.Sprite;

public class SpeedItem extends Entity{
	public SpeedItem(int x, int y) {
		super(32, 32, x, y, Sprite.powerup_speed);
	}
}
