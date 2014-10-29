package pl.grm.bol.engine.components;

import pl.grm.bol.engine.core.GameObject;
import pl.grm.bol.engine.core.Transform;
import pl.grm.bol.engine.render.RenderingEngine;
import pl.grm.bol.engine.render.Shader;

public abstract class GameComponent {
    private GameObject parent;

    public void render(Shader shader) {}
    public void update(float delta) {}
    public void input(float delta) {}

    public void addToRenderingEngine(RenderingEngine renderingEngine) {}

    public Transform getTransform() { return parent.getTransform(); }

    public void setParent(GameObject parent) { this.parent = parent; }
}
