package pl.grm.bol.engine.render;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.TextureLoader;

public class Texture {
	private int id;

	public Texture(int id) {
		this.id = id;
	}

	public Texture(String fileName) {
		this(loadTexture(fileName));
	}

	public int getID() {
		return id;
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public static int loadTexture(String fileName) {
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length - 1];

		try {
			int id = TextureLoader.getTexture(ext,
					new FileInputStream(new File("../src/main/resources/Textures/" + fileName))).getTextureID();

			return id;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return 0;
	}
}
