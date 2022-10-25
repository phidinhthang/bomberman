package oop.bomberman.controlller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;

public class Controller {
	private List<String> input;
	private boolean disabled = false;
	private boolean keyUpPressed = false;
	private boolean keyDownPressed = false;
	private boolean keyLeftPressed = false;
	private boolean keyRightPressed = false;
	
	public Controller(Scene scene) {
		this.input = new ArrayList<>();

		scene.setOnKeyPressed(e -> {
			String keyCode = e.getCode().toString();
			if (disabled) return;
			if (!input.contains(keyCode)) {
				this.input.add(keyCode);
			}

			if (keyCode.equals("RIGHT")) {
				this.resetArrowKeyValue();
				this.keyRightPressed = true;
			} else if (keyCode.equals("LEFT")) {
				this.resetArrowKeyValue();
				this.keyLeftPressed = true;
			} else if (keyCode.equals("UP")) {
				this.resetArrowKeyValue();
				this.keyUpPressed = true;
			} else if (keyCode.equals("DOWN")) {
				this.resetArrowKeyValue();
				this.keyDownPressed = true;
			}
		});

		scene.setOnKeyReleased(e -> {
			String keyCode = e.getCode().toString();
			if (this.input.contains(keyCode)) {
				this.input.remove(keyCode);
			}

			if (keyCode.equals("RIGHT")) {
				this.keyRightPressed = false;
			} else if (keyCode.equals("LEFT")) {
				this.keyLeftPressed = false;
			} else if (keyCode.equals("UP")) {
				this.keyUpPressed = false;
			} else if (keyCode.equals("DOWN")) {
				this.keyDownPressed = false;
			}
		});
	}

	public List<String> getInput() {
		return this.input;
	}

	public boolean isKeyUpPressed() {
		return this.keyUpPressed;
	}

	public boolean isKeyDownPressed() {
		return this.keyDownPressed;
	}

	public boolean isKeyLeftPressed() {
		return this.keyLeftPressed;
	}

	public boolean isKeyRightPressed() {
		return this.keyRightPressed;
	}

	private void resetArrowKeyValue() {
		this.keyDownPressed = false;
		this.keyLeftPressed = false;
		this.keyUpPressed = false;
		this.keyRightPressed = false;
	}

}
