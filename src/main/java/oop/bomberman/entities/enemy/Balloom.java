package oop.bomberman.entities.enemy;

import oop.bomberman.entities.Entity;
import oop.bomberman.entities.Movable;
import oop.bomberman.sprite.Sprite;

public class Balloom extends Movable {
	public Balloom(int x, int y) {
		super(32, 32, x, y);
	}

	@Override
	public void onCollision(Entity entity) {}

	public void remove() {
		super.remove();
	}
}
