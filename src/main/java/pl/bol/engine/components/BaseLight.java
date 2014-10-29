package pl.bol.engine.components;

import pl.bol.engine.render.RenderingEngine;
import pl.bol.engine.core.Vector3f;
import pl.bol.engine.render.Shader;

public class BaseLight extends GameComponent {
	private Vector3f color;
	private float intensity;
    private Shader shader;

	public BaseLight(Vector3f color, float intensity) {
		this.color = color;
		this.intensity = intensity;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public Shader getShader() {
        return shader;
    }

    @Override
    public void addToRenderingEngine(RenderingEngine renderingEngine) {
        renderingEngine.addLight(this);
    }
}
