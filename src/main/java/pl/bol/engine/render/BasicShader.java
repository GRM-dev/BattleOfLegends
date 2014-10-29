package pl.bol.engine.render;

import pl.bol.engine.core.Matrix4f;
import pl.bol.engine.core.Transform;

public class BasicShader extends Shader {
	private static final BasicShader instance = new BasicShader();

	public static BasicShader getInstance() {
		return instance;
	}

	private BasicShader() {
		super();

		addVertexShader(Shader.loadShader("basicVertex.vs"));
		addFragmentShader(Shader.loadShader("basicFragment.fs"));
		compileShader();

		addUniform("transform");
		addUniform("color");
	}

	public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);

        material.getTexture().bind();

		setUniform("transform", projectedMatrix);
		setUniform("color", material.getColor());
	}
}
