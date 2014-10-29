package pl.bol.engine.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import pl.bol.engine.core.Vector2f;

public class Window {
	public static void createWindow(int width, int height, String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static void render() {
		Display.update();
	}

	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}

	public static int getWidthWindow() {
		return Display.getDisplayMode().getWidth();
	}

	public static int getHeightWindow() {
		return Display.getDisplayMode().getHeight();
	}

	public static String getTitleWindow() {
		return Display.getTitle();
	}

	public static void dispose() {
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}

    public Vector2f getCenter() {
        return new Vector2f(getWidthWindow() / 2, getHeightWindow() / 2);
    }
}
