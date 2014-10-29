package pl.grm.bol.engine.render;

import pl.grm.bol.engine.components.BaseLight;
import pl.grm.bol.engine.components.PointLight;
import pl.grm.bol.engine.components.SpotLight;
import pl.grm.bol.engine.core.Matrix4f;
import pl.grm.bol.engine.core.Transform;

public class ForwardSpot extends Shader {
    private static final ForwardSpot instance = new ForwardSpot();

    public static ForwardSpot getInstance() {
        return instance;
    }

    private ForwardSpot() {
        super();

        addVertexShader(Shader.loadShader("forward-spot.vs"));
        addFragmentShader(Shader.loadShader("forward-spot.fs"));

        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);

        compileShader();

        addUniform("model");
        addUniform("MVP");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("spotLight.pointLight.base.color");
        addUniform("spotLight.pointLight.base.intensity");
        addUniform("spotLight.pointLight.atten.constant");
        addUniform("spotLight.pointLight.atten.linear");
        addUniform("spotLight.pointLight.atten.exponent");
        addUniform("spotLight.pointLight.position");
        addUniform("spotLight.pointLight.range");
        addUniform("spotLight.direction");
        addUniform("spotLight.cutoff");
    }

    public void updateUniforms(Transform transform, Material material) {
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);

        material.getTexture().bind();

        setUniform("model", worldMatrix);
        setUniform("MVP", projectedMatrix);

        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());

        setUniform("eyePos", getRenderingEngine().getMainCamera().getTransform().getPos());
        setUniformSpotLight("spotLight", (SpotLight)getRenderingEngine().getActiveLight());
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

    public void setUniformSpotLight(String nameUniform, SpotLight spotLight) {
        setUniformPointLight(nameUniform + ".pointLight", spotLight);
        setUniform(nameUniform + ".direction", spotLight.getDirection());
        setUniformf(nameUniform + ".cutoff", spotLight.getCutoff());
    }
}
