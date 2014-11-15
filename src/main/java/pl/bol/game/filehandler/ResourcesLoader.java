package pl.bol.game.filehandler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class ResourcesLoader {
	private static BLog bLog;
	private static final String PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\";
	public static final String APP_DATA = System.getenv("APPDATA");
	public static final String BOL_CONF_PATH = APP_DATA + "\\BoL-Game\\";
	public static final String LOG_FILE_NAME = "game.log";
	public static final String CONFIG_FILE_NAME = "config.ini";

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
