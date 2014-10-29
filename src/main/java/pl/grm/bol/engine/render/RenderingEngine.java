package pl.grm.bol.engine.render;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_LESS;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

import java.util.ArrayList;

import pl.grm.bol.engine.components.BaseLight;
import pl.grm.bol.engine.components.Camera;
import pl.grm.bol.engine.core.GameObject;
import pl.grm.bol.engine.core.Vector3f;

public class RenderingEngine {
    private Camera mainCamera;
    private Vector3f ambientLight;

    private ArrayList<BaseLight>baseLights;
    private BaseLight activeBaseLight;

    public RenderingEngine() {
        baseLights = new ArrayList<BaseLight>();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);

        glEnable(GL_TEXTURE_2D);

//        mainCamera = new Camera(80.0f, (float)Window.getWidthWindow() / (float)Window.getHeightWindow(), 0.01f, 1000.0f);
        ambientLight = new Vector3f(0.5f, 0.5f, 0.5f);
    }

    public void render(GameObject gameObject) {
        clearScreen();

        baseLights.clear();
        gameObject.addToRenderingEngine(this);

        Shader forwardAmbient = ForwardAmbient.getInstance();
        forwardAmbient.setRenderingEngine(this);

        gameObject.render(forwardAmbient);

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(false);
        glDepthFunc(GL_EQUAL);

        for(BaseLight light : baseLights) {
            light.getShader().setRenderingEngine(this);
            activeBaseLight = light;
            gameObject.render(light.getShader());
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public static void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public static void setClearColor(Vector3f color) {
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }

    public static String getOpenGLVersion() {
        return glGetString(GL_VERSION);
    }

    public static void setTextures(boolean enabled) {
        if (enabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    public static void unbindTextures() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(Camera camera) {
        this.mainCamera = camera;
    }

    public Vector3f getAmbientLight() {
        return ambientLight;
    }

    public void addLight(BaseLight baseLight) {
        baseLights.add(baseLight);
    }

    public BaseLight getActiveLight() {
        return activeBaseLight;
    }

    public void addCamera(Camera camera) {
        mainCamera = camera;
    }
}
