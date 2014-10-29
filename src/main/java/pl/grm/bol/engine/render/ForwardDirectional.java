package pl.grm.bol.engine.render;

import pl.grm.bol.engine.components.BaseLight;
import pl.grm.bol.engine.components.DirectionalLight;
import pl.grm.bol.engine.core.Matrix4f;
import pl.grm.bol.engine.core.Transform;

public class ForwardDirectional extends Shader {
    private static final ForwardDirectional instance = new ForwardDirectional();

    public static ForwardDirectional getInstance() {
        return instance;
    }

    private ForwardDirectional() {
        super();

        addVertexShader(Shader.loadShader("forward-directional.vs"));
        addFragmentShader(Shader.loadShader("forward-directional.fs"));

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("model");
        addUniform("MVP");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    @Override
	public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);

        material.getTexture().bind();

        setUniform("model", worldMatrix);
        setUniform("MVP", projectedMatrix);

        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());

        setUniform("eyePos", getRenderingEngine().getMainCamera().getTransform().getPos());
        setUniformDirectionalLight("directionalLight", (DirectionalLight)getRenderingEngine().getActiveLight());
    }

    public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight) {
        setUniformBaseLight(uniformName + ".base", directionalLight);
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }

    public void setUniformBaseLight(String uniformName, BaseLight baseLight) {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }
}