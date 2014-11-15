package pl.bol.devwindow;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.bol.game.Game;
import pl.bol.game.filehandler.BLog;
import pl.bol.game.filehandler.ResourcesLoader;
import pl.grm.bol.lib.FileOperation;

public class DevWindow {
	private Game game;
	private BLog bLog;
	private String jarFileAbsPath;
	private final String TITLE_DEV_WINDOW = "Dev window";
	private final int WIDTH_DEV_WINDOW = 800;
	private final int HEIGHT_DEV_WINDOW = 600;

	public DevWindow(Game game) {
		this.game = game;
		bLog = new BLog();
	}

	public boolean startProcess(File confDir) {
		String separator = System.getProperty("file.separator");
		String javaPath = System.getProperty("java.home") + separator + "bin"
				+ separator + "java";
		String launcherPId = FileOperation.getProcessId(
				System.getProperty("user.dir")).trim();
		bLog.info("Updater file to run: " + ResourcesLoader.BOL_CONF_PATH
				+ "DevWindow" + "  & launcher jar Path: " + jarFileAbsPath
				+ "  & directory: " + System.getProperty("user.dir"));

		ProcessBuilder processBuilder = new ProcessBuilder(javaPath, "-jar",
				ResourcesLoader.BOL_CONF_PATH + "DevWindow", jarFileAbsPath,
				launcherPId, System.getProperty("user.dir"));
		try {
			processBuilder.directory(confDir);
			processBuilder.start();
			return true;
		} catch (IOException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
		return false;
	}

	public synchronized boolean createDevWindow() {
		try {
			jarFileAbsPath = FileOperation.getCurrentJar(DevWindow.class);
		} catch (UnsupportedEncodingException e1) {
			bLog.log(Level.SEVERE, e1.toString(), e1);
		}
		File confDir = new File(ResourcesLoader.BOL_CONF_PATH);
		if (!confDir.exists()) {
			confDir.mkdir();
		}

		if (startProcess(confDir)) {
			return true;
		}
		return false;
	}

	public void createDevWindow(final String title) {
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(getWidthDevWindow(),
					getHeightDevWindow()));
			Display.create();
		} catch (LWJGLException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
	}

	public String getTitleDevWindow() {
		return TITLE_DEV_WINDOW;
	}

	public int getWidthDevWindow() {
		return WIDTH_DEV_WINDOW;
	}

	public int getHeightDevWindow() {
		return HEIGHT_DEV_WINDOW;
	}
}
