package pl.bol.engine.components;

import pl.bol.engine.core.GameObject;
import pl.bol.engine.render.RenderingEngine;
import pl.bol.engine.core.Transform;
import pl.bol.engine.render.Shader;

public abstract class GameComponent {
    private GameObject parent;

    public void render(Shader shader) {}
    public void update(float delta) {}
    public void input(float delta) {}

    public void addToRenderingEngine(RenderingEngine renderingEngine) {}

    public Transform getTransform() { return parent.getTransform(); }

    public void setParent(GameObject parent) { this.parent = parent; }
}
