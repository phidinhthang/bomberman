package oop.bomberman.entities;

import oop.bomberman.sprite.Sprite;

public class Grass extends Entity{
	public Grass(int x, int y) {
		super(32, 32, x, y, Sprite.grass);
	}	
}
