package oop.bomberman.animator;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.util.Duration;
import oop.bomberman.controlller.Mover;
import oop.bomberman.sprite.Sprite;

public class PlayerAnimator extends SpriteAnimator {
	public PlayerAnimator(Sprite sprite) {
		super(
			sprite,
			new Duration(300),
			2,
			Sprite.player_up,
			Sprite.player_right,
			Sprite.player_down,
			Sprite.player_left
		);
	}

	public void update(Mover mover) {
		List<Image> upImages = new ArrayList<>();
		upImages.add(Sprite.player_up_1);
		upImages.add(Sprite.player_up_2);
		List<Image> downImages = new ArrayList<>();
		downImages.add(Sprite.player_down_1);
		downImages.add(Sprite.player_down_2);
		List<Image> leftImages = new ArrayList<>();
		leftImages.add(Sprite.player_left_1);
		leftImages.add(Sprite.player_left_2);
		List<Image> rightImages = new ArrayList<>();
		rightImages.add(Sprite.player_right_1);
		rightImages.add(Sprite.player_right_2); 
		super.update(mover, upImages, rightImages, downImages, leftImages);
	}
}
