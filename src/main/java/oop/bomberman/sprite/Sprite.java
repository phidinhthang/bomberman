package oop.bomberman.sprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	public ImageView imageView;

	public static Image grass = Sprite.createImage("grass");
	public static Image brick = Sprite.createImage("brick");
	public static Image wall = Sprite.createImage("wall");

	public static Image bomb = Sprite.createImage("bomb");
	public static Image bomb_exploded = Sprite.createImage("bomb_exploded");
	public static Image explosion_horizontal = Sprite.createImage("explosion_horizontal");
	public static Image explosion_vertical = Sprite.createImage("explosion_vertical");

	public static Image player_up = Sprite.createImage("player_up");
	public static Image player_down = Sprite.createImage("player_down");
	public static Image player_left = Sprite.createImage("player_left");
	public static Image player_right = Sprite.createImage("player_right");

	public static Image player_up_1 = Sprite.createImage("player_up_1");
	public static Image player_up_2 = Sprite.createImage("player_up_2");
	
	public static Image player_down_1 = Sprite.createImage("player_down_1");
	public static Image player_down_2 = Sprite.createImage("player_down_2");
	
	public static Image player_right_1 = Sprite.createImage("player_right_1");
	public static Image player_right_2 = Sprite.createImage("player_right_2");
	
	public static Image player_left_1 = Sprite.createImage("player_left_1");
	public static Image player_left_2 = Sprite.createImage("player_left_2");
	
	public static Image balloom_dead = Sprite.createImage("balloom_dead");

	public static Image balloom_left1 = Sprite.createImage("balloom_left1");
	public static Image balloom_left2 = Sprite.createImage("balloom_left2");
	public static Image balloom_left3 = Sprite.createImage("balloom_left3");

	public static Image balloom_right1 = Sprite.createImage("balloom_right1");
	public static Image balloom_right2 = Sprite.createImage("balloom_right2");
	public static Image balloom_right3 = Sprite.createImage("balloom_right3");

	public static Image powerup_bombs = Sprite.createImage("powerup_bombs");
	public static Image powerup_speed = Sprite.createImage("powerup_speed");
	public static Image powerup_flames = Sprite.createImage("powerup_flames");	

	public Sprite(Image image) {
		try {
			ImageView spriteImageView = new ImageView(image);
			
			this.imageView = spriteImageView;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sprite(Image image, int x, int y) {
		this(image);		
		this.imageView.setX(x);
		this.imageView.setY(y);
		this.imageView.setFitWidth(32);
		this.imageView.setFitHeight(32);
	}

	public static Image createImage(String name) {
		Image image = new Image(Sprite.class.getResourceAsStream(String.format("/sprites/%s.png", name)), 32, 32, false, false);
		return image;
	}
}
