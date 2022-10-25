package oop.bomberman.animator;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import oop.bomberman.controlller.EnemyMover;
import oop.bomberman.controlller.Mover;
import oop.bomberman.sprite.Sprite;

public abstract class EnemyAnimator extends Transition {
	protected Sprite sprite;
	private int maxCount;
	private int count = 1;
	private Image leftImage;
	private Image rightImage;
	private List<Image> animatedImages;

	public EnemyAnimator(
		Sprite sprite,
		Duration duration,
		int count,
		Image leftImage,
		Image rightImage
	) {
		this.sprite = sprite;
		this.maxCount = count;
		this.leftImage = leftImage;
		this.rightImage = rightImage;
		this.animatedImages = new ArrayList<>();
		this.setCycleDuration(duration);
	}

	public void _update(EnemyMover mover, List<Image> leftImages, List<Image> rightImages) {
		if (mover.isMoving()) {
			this.play();
			this.count = this.maxCount;

			if (mover.getEntity().isFacingLeft()) {
				this.animatedImages = leftImages;
			} else {
				this.animatedImages = rightImages;
			}
		} else {
			this.animatedImages.clear();
			this.count = 1;

			if (mover.getEntity().isFacingLeft()) {
				this.animatedImages.add(leftImage);
			} else {
				this.animatedImages.add(rightImage);
			}
		}
	}

	public void updateView(EnemyMover mover) {
		this.sprite.imageView.relocate(mover.getEntity().getX(), mover.getEntity().getY());
	}

	@Override
	protected void interpolate(double frac) {
		final int index = Math.min((int) Math.floor(frac * count), count - 1);
		
		this.sprite.imageView.setImage(this.animatedImages.get(index));
	}

	abstract public void update(EnemyMover mover);
}
