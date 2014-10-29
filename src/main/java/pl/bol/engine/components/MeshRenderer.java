package pl.bol.engine.components;

import pl.bol.engine.core.Transform;
import pl.bol.engine.render.Material;
import pl.bol.engine.render.Mesh;
import pl.bol.engine.render.Shader;

public class MeshRenderer extends GameComponent {
    private Mesh mesh;
    private Material material;

    public MeshRenderer(Mesh mesh, Material material) {
        this.mesh = mesh;
        this.material = material;
    }

    @Override
    public void render(Shader shader) {
        shader.bind();
        shader.updateUniforms(getTransform(), material);
        mesh.draw();
    }
}
