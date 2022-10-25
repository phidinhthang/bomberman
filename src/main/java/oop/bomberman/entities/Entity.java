package oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import oop.bomberman.App;
import oop.bomberman.sprite.Sprite;

public abstract class Entity extends Rectangle {
	private Sprite sprite;
	private boolean removed;

	public Entity(double width, double height, double x, double y, Image image) {
		this.setWidth(width);
		this.setHeight(height);
		this.setX(x);
		this.setY(y);
		this.sprite = new Sprite(image);
		App.root.getChildren().add(sprite.imageView);
	}

	public int getTileX() {
		return (int)this.getX() / 32;
	}

	public int getTileY() {
		return (int)this.getY() / 32;
	}

	public static int tileToPosition(int tileCoordinate) {
		return tileCoordinate * 32;
	}

	public boolean hasSameTilePosition(Entity entity) {
		if (this.getTileX() == entity.getTileX() && this.getTileY() == entity.getTileY()) {
			return true;
		}

		return false;
	}

	public void draw() {
		this.sprite.imageView.setX(this.getX());
		this.sprite.imageView.setY(this.getY());
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public void remove() {
		this.setWidth(0);
		this.setHeight(0);
		this.setX(0);
		this.setY(0);
		this.removed = true;
	}

	public boolean isRemoved() {
		return this.removed;
	}
}
