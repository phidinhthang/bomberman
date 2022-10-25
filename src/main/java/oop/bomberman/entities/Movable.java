package oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;

import oop.bomberman.sprite.Sprite;

public abstract class Movable extends Entity {
	private double velocity = 2;
	private boolean isFacingRight = false;
	private boolean isFacingDown = false;
	private boolean isFacingUp = false;
	private boolean isFacingLeft = false;

	private ArrayList<Entity> collisions = new ArrayList<>();

	public Movable(double width, double height, double x, double y) {
		super(width, height, x, y, Sprite.player_down);
	}

	public double getVelocity() {
		return this.velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public boolean isFacingRight() {
		boolean facing = false;
		if (isFacingRight && !isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
		return facing;
	}

	public boolean isFacingLeft() {
		boolean facing = false;
		if (!isFacingRight && !isFacingDown && !isFacingUp && isFacingLeft) facing = true;
		return facing;
	}

	public boolean isFacingDown() {
		boolean facing = false;
		if (!isFacingRight && isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
		return facing;
	}

	public boolean isFacingUp() {
		boolean facing = false;
		if (!isFacingRight && !isFacingDown && isFacingUp && !isFacingLeft) facing = true;
		return facing;
	}

	public void setFaceRight() {
		this.isFacingRight = true;
		this.isFacingLeft = false;
		this.isFacingUp = false;
		this.isFacingDown = false;
	}

	public void setFaceLeft() {
		this.isFacingRight = false;
		this.isFacingLeft = true;
		this.isFacingUp = false;
		this.isFacingDown = false;		
	}

	public void setFaceDown() {
		isFacingRight = false;
		isFacingDown = true;
		isFacingUp = false;
		isFacingLeft = false;
	}

	public void setFaceUp() {
		isFacingRight = false;
		isFacingDown = false;
		isFacingUp = true;
		isFacingLeft = false;
	}

	public void addCollision(Entity entity) {
		this.collisions.add(entity);
	}

	public void removeCollision(Entity entity) {
		this.collisions.remove(entity);
	}

	public void addCollisions(List<? extends Entity> entities) {
		this.collisions.addAll(entities);
	}

	public List<Entity> getCollisions() {
		return this.collisions;
	}

	public String getVerticalCollision() {
		String collided = "NONE";

		for (Entity c : collisions) {
			if(this.isFacingUp) {
				if (c.getY() + c.getHeight() >= this.getY() && this.getY() + this.getHeight() > c.getY() + c.getHeight()
					&& ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
					(this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
					(this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
					(this.getX() + this.getWidth() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {
						collided = "UP";
						this.onCollision(c);
						System.out.println(": Collided Upward");
						return "UP";
				}
      } else if (this.isFacingDown) {
				if ( this.getY() + this.getHeight() >= c.getY() && this.getY() < c.getY()
          && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
							(this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
							(this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
							(this.getX() + this.getWidth() > c.getX()&& this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {
								collided = "DOWN";
								this.onCollision(c);
								System.out.println(": Collided downward");
								return "DOWN";
        }
			}
		}

		return collided;
	}

	    public String getHorizontalCollision() {
        String collided = "NONE";

        for (Entity c : collisions) {
            if (this.isFacingRight) {
                if (this.getX() + this.getWidth() >= c.getX() && c.getX() > this.getX()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() <= c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collided = "RIGHT";
										this.onCollision(c);
                    System.out.println(": Collided rightward");
										return "RIGHT";
                }
            } else if (this.isFacingLeft) {
                if (this.getX() <= c.getX() + c.getWidth() && c.getX() + c.getWidth() < this.getX() + this.getWidth()
                  && ((this.getY() >= c.getY() && this.getY() + this.getHeight() <= c.getY() + c.getHeight()) ||
                  (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                  (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                  (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {
                    collided = "LEFT";
										this.onCollision(c);
                    System.out.println("Collided leftward");
										return "LEFT";
                }
            }
        }
        return collided;
    }


	protected abstract void onCollision(Entity entity);
}
