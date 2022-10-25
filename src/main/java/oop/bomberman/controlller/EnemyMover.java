package oop.bomberman.controlller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import oop.bomberman.entities.Movable;

public class EnemyMover {
		private MovingDirection movingDirection;
		private Movable movable;
		private int maxMovingTime = 120;
		private int currentMovingTime = 0;

		private static final List<MovingDirection> directionValues = Collections.unmodifiableList(Arrays.asList(MovingDirection.values()));
		private static final Random random = new Random();

		public EnemyMover(Movable movable) {
			this.movable = movable;
			this.movingDirection = MovingDirection.LEFT;
		}

		public void update() {
			if (this.currentMovingTime < this.maxMovingTime) {
				this.currentMovingTime++;
			} else {
				this.currentMovingTime = 0;
				if (movingDirection == MovingDirection.DOWN) {
					movingDirection = MovingDirection.UP;
				} else if (movingDirection == MovingDirection.UP) {
					movingDirection = MovingDirection.DOWN;
				} else if (movingDirection == MovingDirection.LEFT) {
					movingDirection = MovingDirection.RIGHT;
				} else {
					movingDirection = MovingDirection.LEFT;
				}
			}

			if (this.movingDirection != null) {
				this.updatePosition();
			}
		}

		public void updatePosition() {
			if (this.movingDirection == MovingDirection.RIGHT) {
				if (movable.getHorizontalCollision() == "NONE") {
					movable.setX(movable.getX() + movable.getVelocity());
					movable.setFaceRight();
				} else {
					this.nextMove();
				}
			} else if (this.movingDirection == MovingDirection.LEFT) {
				if (movable.getHorizontalCollision() == "NONE") {
					movable.setX(movable.getX() - movable.getVelocity());
					movable.setFaceLeft();
				} else {
					this.nextMove();
				}
			} else if (this.movingDirection == MovingDirection.UP) {
				if (movable.getVerticalCollision() == "NONE") {
					movable.setY(movable.getY() - movable.getVelocity());
					movable.setFaceUp();
				} else {
					this.nextMove();
				}
			} else if (this.movingDirection == MovingDirection.DOWN) {
				if (movable.getVerticalCollision() == "NONE") {
					movable.setY(movable.getY() + movable.getVelocity());
					movable.setFaceDown();
				} else {
					this.nextMove();
				}
			}

		}

		public Movable getEntity() {
			return this.movable;
		}

		private void nextMove() {
			MovingDirection nextDirection = directionValues.get(random.nextInt(directionValues.size()));
			this.movingDirection = nextDirection;
			System.out.println(this.movingDirection);
			this.currentMovingTime = 0;
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
}
