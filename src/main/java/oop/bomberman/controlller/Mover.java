package oop.bomberman.controlller;

import java.util.ArrayList;

import oop.bomberman.entities.Movable;

enum MovingDirection {
	LEFT,
	RIGHT,
	UP,
	DOWN
}

public class Mover {
	private MovingDirection movingDirection;
	private Controller controller;
	private Movable movable;


	public Mover(Controller controller, Movable movable) {
		this.controller = controller;
		this.movable = movable;
	}

	public boolean isMoving() {
		return this.movingDirection != null;
	}

	public boolean isMovingDown() {
		return this.movingDirection == MovingDirection.DOWN;
	}

	public boolean isMovingUp() {
		return this.movingDirection == MovingDirection.UP;
	}

	public boolean isMovingRight() {
		return this.movingDirection == MovingDirection.RIGHT;
	}

	public boolean isMovingLeft() {
		return this.movingDirection == MovingDirection.LEFT;
	}

	public void update() {
		if (controller.isKeyUpPressed()) {
			this.movingDirection = MovingDirection.UP;
		} else if (controller.isKeyDownPressed()) {
			this.movingDirection = MovingDirection.DOWN;
		} else if (controller.isKeyLeftPressed()) {
			this.movingDirection = MovingDirection.LEFT;
		} else if (controller.isKeyRightPressed()) {
			this.movingDirection = MovingDirection.RIGHT;
		} else {
			this.movingDirection = null;
		}

		if (this.movingDirection != null) {
			this.updatePosition();
		}
	}

	public void updatePosition() throws NullPointerException {
		if (this.isMovingRight()) {
			System.out.println("is moving right");
			if (movable.isFacingRight() && movable.getHorizontalCollision() == "NONE") {
				movable.setX(movable.getX() + movable.getVelocity());
			} else {
				movable.setFaceRight();
			}
		} else if (this.isMovingLeft()) {
			if (movable.isFacingLeft() && movable.getHorizontalCollision() == "NONE") {
				movable.setX(movable.getX() - movable.getVelocity());
			} else {
				movable.setFaceLeft();
			}
		} else if (this.isMovingUp()) {
			if (movable.isFacingUp() && movable.getVerticalCollision() == "NONE") {
				movable.setY(movable.getY() - movable.getVelocity());
			} else {
				movable.setFaceUp();
			}
		} else if (this.isMovingDown()) {
			if (movable.isFacingDown() && movable.getVerticalCollision() == "NONE") {
				movable.setY(movable.getY() + movable.getVelocity());
			} else {
				movable.setFaceDown();
			}
		}
	}

	public Movable getEntity() {
		return this.movable;
	}
}
