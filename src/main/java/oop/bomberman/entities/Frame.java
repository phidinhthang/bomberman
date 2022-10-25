package oop.bomberman.entities;

import javafx.scene.image.Image;
import oop.bomberman.sprite.Sprite;

public class Frame extends Entity {
	public Frame(int x, int y, Image image) {
		super(32, 32, x, y, image);
	}

	public static Frame createFromTilePosition(int tileX, int tileY, Image image) {
		return new Frame(tileToPosition(tileX), tileToPosition(tileY), image);
	}
}
