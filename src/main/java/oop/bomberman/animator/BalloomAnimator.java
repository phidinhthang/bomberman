package oop.bomberman.animator;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.util.Duration;
import oop.bomberman.controlller.EnemyMover;
import oop.bomberman.controlller.Mover;
import oop.bomberman.sprite.Sprite;

public class BalloomAnimator extends EnemyAnimator {
	public BalloomAnimator(Sprite sprite) {
		super(
			sprite,
			new Duration(300),
			3,
			Sprite.balloom_left1,
			Sprite.balloom_right1
		);
	}

	public void update(EnemyMover mover) {
		List<Image> leftImages = new ArrayList<>();
		leftImages.add(Sprite.balloom_left1);
		leftImages.add(Sprite.balloom_left2);
		leftImages.add(Sprite.balloom_left3);
		List<Image> rightImages = new ArrayList<>();
		rightImages.add(Sprite.balloom_right1);
		rightImages.add(Sprite.balloom_right2);
		rightImages.add(Sprite.balloom_right3);
		super._update(mover, leftImages, rightImages);
	}
}
