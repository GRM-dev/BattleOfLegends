package pl.grm.bol.engine.render;

import pl.grm.bol.engine.components.BaseLight;
import pl.grm.bol.engine.components.PointLight;
import pl.grm.bol.engine.core.Matrix4f;
import pl.grm.bol.engine.core.Transform;

public class ForwardPoint extends Shader {
    private static final ForwardPoint instance = new ForwardPoint();

    public static ForwardPoint getInstance() {
        return instance;
    }

    private ForwardPoint() {
        super();

        addVertexShader(Shader.loadShader("forward-point.vs"));
        addFragmentShader(Shader.loadShader("forward-point.fs"));

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("model");
        addUniform("MVP");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("pointLight.base.color");
        addUniform("pointLight.base.intensity");
        addUniform("pointLight.atten.constant");
        addUniform("pointLight.atten.linear");
        addUniform("pointLight.atten.exponent");
        addUniform("pointLight.position");
        addUniform("pointLight.range");
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
        setUniformPointLight("pointLight", (PointLight)getRenderingEngine().getActiveLight());
    }

    public void setUniformPointLight(String nameUniform, PointLight pointLight) {
        setUniformBaseLight(nameUniform + ".base", pointLight);
        setUniformf(nameUniform + ".atten.constant", pointLight.getConstant());
        setUniformf(nameUniform + ".atten.linear", pointLight.getLinear());
        setUniformf(nameUniform + ".atten.exponent", pointLight.getExponent());
        setUniform(nameUniform + ".position", pointLight.getTransform().getPos());
        setUniformf(nameUniform + ".range", pointLight.getRange());
    }

    public void setUniformBaseLight(String uniformName, BaseLight baseLight) {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }
}
