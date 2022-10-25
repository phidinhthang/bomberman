package oop.bomberman.entities;

import oop.bomberman.sprite.Sprite;

public class Wall extends Entity {
	public Wall(int x, int y) {
		super(32, 32, x, y, Sprite.wall);
	}
}
