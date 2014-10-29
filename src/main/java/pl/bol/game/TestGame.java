package pl.bol.game;

import pl.bol.engine.components.*;
import pl.bol.engine.core.*;
import pl.bol.engine.render.*;

public class TestGame extends Game {
    private GameObject planeObject;

    public void init() {
        float filedDepth = 10.0f;
        float filedWidth = 10.0f;

        Vertex[] dataScene = new Vertex[]{
                new Vertex(new Vector3f(-filedWidth, 0, -filedDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex(new Vector3f(-filedWidth, 0, filedDepth * 5), new Vector2f(0.0f, 1.0f)),
                new Vertex(new Vector3f(filedWidth * 5, 0, -filedDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex(new Vector3f(filedWidth * 5, 0, filedDepth * 5), new Vector2f(1.0f, 1.0f))};

        int indicesScene[] = {0, 1, 2,
                2, 1, 3};

        Vertex[] dataScene2 = new Vertex[]{
                new Vertex(new Vector3f(-filedWidth / 10, 0, -filedDepth / 10), new Vector2f(0.0f, 0.0f)),
                new Vertex(new Vector3f(-filedWidth / 10, 0, filedDepth / 10 * 5), new Vector2f(0.0f, 1.0f)),
                new Vertex(new Vector3f(filedWidth / 10 * 5, 0, -filedDepth / 10), new Vector2f(1.0f, 0.0f)),
                new Vertex(new Vector3f(filedWidth / 10 * 5, 0, filedDepth / 10 * 5), new Vector2f(1.0f, 1.0f))};

        int indicesScene2[] = {0, 1, 2,
                2, 1, 3};

        Mesh mesh = new Mesh(dataScene, indicesScene, true);
        Mesh mesh2 = new Mesh(dataScene2, indicesScene2, true);

        Material material = new Material(new Texture("testTexture.png"), new Vector3f(1, 1, 1), 1, 8);

        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

        planeObject = new GameObject();
        planeObject.addComponent(meshRenderer);
        planeObject.getTransform().getPos().set(0, -1, 5);

        GameObject directionalLightObject = new GameObject();
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), 0.7f, new Vector3f(1, 1, 1));
        directionalLightObject.addComponent(directionalLight);

        GameObject pointLightObject = new GameObject();
        PointLight pointLight = new PointLight(new Vector3f(1, 0, 1), 3f, new Vector3f(0, 0, 1));
        pointLightObject.addComponent(pointLight);

        GameObject spotLightObject = new GameObject();
        SpotLight spotLight = new SpotLight(new Vector3f(0, 1, 1), 1f, new Vector3f(0, 0, 0.01f), 0.7f);
        spotLightObject.addComponent(spotLight);

        pointLight.getTransform().getPos().set(5, 0, 5);
        spotLight.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), (float)Math.toRadians(90.0f)));

        getRootObject().addChild(planeObject);
        getRootObject().addChild(directionalLightObject);
        getRootObject().addChild(pointLightObject);
        getRootObject().addChild(spotLightObject);

//        getRootObject().addChild(new GameObject().addComponent(new Camera(70.0f, (float)Window.getWidthWindow() / (float)Window.getHeightWindow(), 0.01f, 1000.0f)));

        GameObject testMesh = new GameObject().addComponent(new MeshRenderer(mesh2, material));
        GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh2, material));

        testMesh.getTransform().getPos().set(0, 2, 0);
        testMesh.getTransform().setRot(new Quaternion(new Vector3f(0, 1, 0), 0.4f));

        testMesh2.getTransform().getPos().set(0, 0, 5);

        testMesh.addChild(testMesh2);
        testMesh2.addChild(new GameObject().addComponent(new Camera(70.0f, (float)Window.getWidthWindow() / (float)Window.getHeightWindow(), 0.01f, 1000.0f)));

        getRootObject().addChild(testMesh);
    }
}
