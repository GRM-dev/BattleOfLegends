package pl.bol.game.filehandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.ini4j.Wini;
import org.lwjgl.opengl.Display;

import pl.bol.game.Game;

public class ResourcesLoader {
	private Game game;
	private static BLog bLog;
	private static final String PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\";
	public static final String APP_DATA = System.getenv("APPDATA");
	public static final String BOL_CONF_PATH = APP_DATA + "\\BoL-Game\\";
	public static final String LOG_FILE_NAME = "game.log";
	public static final String CONFIG_FILE_NAME = "config.ini";

	public ResourcesLoader(Game game) {
		this.game = game;
		bLog = new BLog();
	}

	public static void setGameIcon() {
		try {
			ByteBuffer[] icons = new ByteBuffer[2];
			icons[0] = ResourcesLoader.loadIcon("gameIcon_16.png", 16, 16);
			icons[1] = ResourcesLoader.loadIcon("gameIcon_32.png", 32, 32);
			Display.setIcon(icons);
		} catch (IOException ex) {
			bLog.log(Level.SEVERE, ex.toString(), ex);
		}
	}

	public static void getConfig() {
		File dir = new File(BOL_CONF_PATH);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(BOL_CONF_PATH + CONFIG_FILE_NAME);
		if (!file.exists()) {
			createConfig(file);
		}
	}

	private static void createConfig(File file) {
		Wini ini = null;
		try {
			file.createNewFile();
			ini = new Wini(file);
			ini.put("Sound", "master_volume", "100");
			ini.store();
		} catch (IOException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		} catch (SecurityException e) {
			bLog.log(Level.SEVERE, e.toString(), e);
		}
	}

	public static void writeConfigParamLauncher(Wini ini, String param,
			String value) throws IOException {
		ini.put("Launcher", param, value);
		ini.store();
	}

	public static ByteBuffer loadIcon(String filename, int width, int height)
			throws IOException {
		BufferedImage image = ImageIO
				.read(new File(PATH + "icons/" + filename)); // load

		byte[] imageBytes = new byte[width * height * 4];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int pixel = image.getRGB(j, i);
				for (int k = 0; k < 3; k++)
					// red, green, blue
					imageBytes[(i * 16 + j) * 4 + k] = (byte) (((pixel >> (2 - k) * 8)) & 255);
				imageBytes[(i * 16 + j) * 4 + 3] = (byte) (((pixel >> (3) * 8)) & 255); // alpha
			}
		}
		return ByteBuffer.wrap(imageBytes);
	}
}
