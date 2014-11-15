package pl.bol.engine.inputs.mouse;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import pl.bol.engine.graphic.Vector2f;

public class MouseInput {
	private static ArrayList<Integer> currentButtons = new ArrayList<Integer>();
	private static ArrayList<Integer> downButtons = new ArrayList<Integer>();
	private static ArrayList<Integer> upButtons = new ArrayList<Integer>();
	public static final int NUM_MOUSECODE = 5;

	public static void update() {
		upButtons.clear();
		for (int i = 0; i < NUM_MOUSECODE; i++) {
			if (!getButton(i) && !currentButtons.contains(i)) {
				upButtons.add(i);
			}
		}

		downButtons.clear();
		for (int i = 0; i < NUM_MOUSECODE; i++) {
			if (getButton(i) && !currentButtons.contains(i)) {
				downButtons.add(i);
			}
		}

		currentButtons.clear();
		for (int i = 0; i < NUM_MOUSECODE; i++) {
			if (getButton(i)) {
				currentButtons.add(i);
			}
		}
	}

	public static boolean getButton(int buttonKey) {
		return Mouse.isButtonDown(buttonKey);
	}

	public static boolean getButtonDown(int buttonKey) {
		if (downButtons.contains(buttonKey))
			return true;

		return false;
	}

	public static boolean getButtonUp(int buttonKey) {
		if (upButtons.contains(buttonKey))
			return true;

		return false;
	}

	public static Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
}
