package pl.grm.bol.engine.graphic;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Util {
	public static FloatBuffer createFlippedBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	public static FloatBuffer createFloatBuffer(Vertex[] data) {
		FloatBuffer buffer = createFlippedBuffer(data.length * Vertex.SIZE);
		for (int i = 0; i < data.length; i++) {
			buffer.put(data[i].getVector3f().getX());
			buffer.put(data[i].getVector3f().getY());
			buffer.put(data[i].getVector3f().getZ());
		}
		buffer.flip();

		return buffer;
	}
}
