package pl.grm.bol.game;

import java.util.*;

import org.lwjgl.input.*;
import org.lwjgl.util.vector.*;

public class Input {

	private ArrayList<Integer> currentButtons = new ArrayList<Integer>();
	private ArrayList<Integer> downButtons = new ArrayList<Integer>();
	private ArrayList<Integer> upButtons = new ArrayList<Integer>();
	public final int NUM_MOUSECODE = 5;

	private ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	private ArrayList<Integer> downKeys = new ArrayList<Integer>();
	private ArrayList<Integer> upKeys = new ArrayList<Integer>();
	public final int NUM_KEYCODES = 256;

	public boolean getButton(int buttonKey) {
		return Mouse.isButtonDown(buttonKey);
	}

	public boolean getButtonDown(int buttonKey) {
		if (downButtons.contains(buttonKey))
			return true;

		return false;
	}

	public boolean getButtonUp(int buttonKey) {
		if (upButtons.contains(buttonKey))
			return true;

		return false;
	}

	public Vector2f getMousePosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}

	public void update() {
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

		upKeys.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (!getKey(i) && !currentKeys.contains(i)) {
				upKeys.add(i);
			}
		}

		downKeys.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (getKey(i) && !currentKeys.contains(i)) {
				downKeys.add(i);
			}
		}

		currentKeys.clear();
		for (int i = 0; i < NUM_KEYCODES; i++) {
			if (getKey(i)) {
				currentKeys.add(i);
			}
		}
	}

	public boolean getKey(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}

	public boolean getKeyDown(int keyCode) {
		if (downKeys.contains(keyCode))
			return true;

		return false;
	}

	public boolean getKeyUp(int keyCode) {
		if (upKeys.contains(keyCode))
			return true;

		return false;
	}
}
