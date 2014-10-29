package pl.bol.engine.core;

import pl.bol.engine.components.GameComponent;
import pl.bol.engine.render.RenderingEngine;
import pl.bol.engine.render.Shader;

import java.util.ArrayList;

public class GameObject {
    private ArrayList<GameObject>children;
    private ArrayList<GameComponent>components;
    private Transform transform;

    public GameObject() {
        children = new ArrayList<GameObject>();
        components = new ArrayList<GameComponent>();
        transform = new Transform();
    }

    public void render(Shader shader) {
        for(GameComponent component : components)
            component.render(shader);

        for(GameObject child : children)
            child.render(shader);
    }

    public void update(float delta) {
        for(GameComponent component : components)
            component.update(delta);

        for(GameObject child : children)
            child.update(delta);
    }

    public void input(float delta) {
        for(GameComponent component : components)
            component.input(delta);

        for(GameObject child : children)
            child.input(delta);
    }

    public void addChild(GameObject child) {
        children.add(child);
        child.getTransform().setParent(transform);
    }

    public GameObject addComponent(GameComponent component) {
        components.add(component);
        component.setParent(this);

        return this;
    }

    public Transform getTransform() {
        return transform;
    }

    public void addToRenderingEngine(RenderingEngine renderingEngine) {
        for(GameComponent component : components)
            component.addToRenderingEngine(renderingEngine);

        for(GameObject child : children)
            child.addToRenderingEngine(renderingEngine);
    }
}
