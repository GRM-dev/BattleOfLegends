package pl.grm.bol.engine.graphic;

import org.lwjgl.util.vector.*;

public class Vertex {

	public static final int SIZE = 3;
	private Vector3f vector3f;

	public Vertex(Vector3f vector3f) {
		this.setVector3f(vector3f);
	}

	public Vector3f getVector3f() {
		return vector3f;
	}

	public void setVector3f(Vector3f vector3f) {
		this.vector3f = vector3f;
	}
}
