package oop.bomberman.animator;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import oop.bomberman.controlller.Mover;
import oop.bomberman.sprite.Sprite;

public class SpriteAnimator extends Transition {
	protected Sprite sprite;
	private int maxCount;
	private int count = 1;
	private Image upImage;
	private Image downImage;
	private Image leftImage;
	private Image rightImage;
	private List<Image> animatedImages;

	public SpriteAnimator(
		Sprite sprite,
		Duration duration,
		int count,
		Image upImage,
		Image rightImage,
		Image downImage,
		Image leftImage
	) {
		this.sprite = sprite;
		this.maxCount = count;
		this.upImage = upImage;
		this.rightImage = rightImage;
		this.leftImage = leftImage;
		this.downImage = downImage;
		this.animatedImages = new ArrayList<>();
		this.setCycleDuration(duration);
	}

	protected void update(Mover mover, List<Image> upImages, List<Image> rightImages, List<Image> downImages, List<Image> leftImages) {
		if (mover.isMoving()) {
			this.play();
			this.count = this.maxCount;

			if (mover.getEntity().isFacingDown()) {
				this.animatedImages = downImages;
			} else if (mover.getEntity().isFacingLeft()) {
				this.animatedImages = leftImages;
			} else if (mover.getEntity().isFacingRight()) {
				this.animatedImages = rightImages;
			} else if (mover.getEntity().isFacingUp()) {
				this.animatedImages = upImages;
			}
		} else {
			this.animatedImages.clear();
			this.count = 1;
			
			if (mover.getEntity().isFacingDown()) {
				this.animatedImages.add(downImage);
			} else if (mover.getEntity().isFacingLeft()){
				this.animatedImages.add(leftImage);
			} else if (mover.getEntity().isFacingRight()) {
				this.animatedImages.add(rightImage);
			} else if (mover.getEntity().isFacingUp()) {
				this.animatedImages.add(upImage);
			} else {
				this.animatedImages.add(downImage);
			}
		}
	}

	public void updateView(Mover mover) {		
		ImageView imageView = this.sprite.imageView;
		this.sprite.imageView.relocate(mover.getEntity().getX() - (32 - mover.getEntity().getWidth()) / 2, mover.getEntity().getY() - (32 - mover.getEntity().getHeight()));
	}

	@Override
	protected void interpolate(double frac) {
		final int index = Math.min((int) Math.floor(frac * count), count - 1);
		
		this.sprite.imageView.setImage(this.animatedImages.get(index));
	}
}
