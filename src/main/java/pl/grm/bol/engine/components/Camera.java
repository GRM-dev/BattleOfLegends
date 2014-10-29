package pl.grm.bol.engine.components;

import org.lwjgl.input.Keyboard;

import pl.grm.bol.engine.core.*;
import pl.grm.bol.engine.render.RenderingEngine;
import pl.grm.bol.engine.render.Window;

public class Camera extends GameComponent {
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

//	private Vector3f pos;
//	private Vector3f forward;
//	private Vector3f up;
    private Matrix4f projection;
	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidthWindow() / 2, Window.getHeightWindow() / 2);

	public Camera(float fov, float aspect, float zNear, float zFar) {
//		this.pos = new Vector3f(0, 0, 0);
//		this.forward = new Vector3f(0, 0, 1).normalize();
//		this.up = new Vector3f(0, 1, 0).normalize();
        this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
	}

//	public Vector3f getPos() {
//		return pos;
//	}
//
//	public void setPos(Vector3f pos) {
//		this.pos = pos;
//	}
//
//	public Vector3f getForward() {
//		return forward;
//	}
//
//	public void setForward(Vector3f forward) {
//		this.forward = forward;
//	}
//
//	public Vector3f getUp() {
//		return up;
//	}
//
//	public void setUp(Vector3f up) {
//		this.up = up;
//	}
//
	public void move(Vector3f dir, float amt) {
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}
//
//	public Vector3f getLeft() {
//		return forward.cross(up).normalize();
//	}
//
//	public Vector3f getRight() {
//		return up.cross(forward).normalize();
//	}

//    public void rotateY(float angle)
//    {
//        Vector3f Haxis = yAxis.cross(forward).normalize();
//
//        forward = forward.rotate(yAxis, angle).normalize();
//
//        up = forward.cross(Haxis).normalize();
//    }
//
//    public void rotateX(float angle)
//    {
//        Vector3f Haxis = yAxis.cross(forward).normalize();
//
//        forward = forward.rotate(Haxis, angle).normalize();
//
//        up = forward.cross(Haxis).normalize();
//    }

    @Override
	public void input(float delta) {
		float sensitivity = -0.5f;
		float movAmt = (10 * delta);

		if (Input.getKey(Input.KEY_ESCAPE)) {
			Input.setCursor(true);
			mouseLocked = false;
		}
		if (Input.getMouseDown(0)) {
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}

		if (mouseLocked) {
            Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);

            boolean rotY = deltaPos.getX() != 0;
            boolean rotX = deltaPos.getY() != 0;

            if(rotY)
                getTransform().setRot(getTransform().getRot().mul(new Quaternion(yAxis, (float)Math.toRadians(deltaPos.getX() * sensitivity))).normalize());
            if(rotX)
                getTransform().setRot(getTransform().getRot().mul(new Quaternion(getTransform().getRot().getRight(), (float) Math.toRadians(-deltaPos.getY() * sensitivity))).normalize());

            if(rotY || rotX)
                Input.setMousePosition(new Vector2f(Window.getWidthWindow()/2, Window.getHeightWindow()/2));
		}

		if (Input.getKey(Keyboard.KEY_W))
			move(getTransform().getRot().getForward(), movAmt);
		if (Input.getKey(Keyboard.KEY_S))
			move(getTransform().getRot().getForward(), -movAmt);
		if (Input.getKey(Keyboard.KEY_A))
			move(getTransform().getRot().getLeft(), movAmt);
		if (Input.getKey(Keyboard.KEY_D))
			move(getTransform().getRot().getRight(), movAmt);
	}

    public Matrix4f getViewProjection() {
        Matrix4f cameraRotation = getTransform().getRot().toRotationMatrix();
        Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

        return projection.mul(cameraRotation.mul(cameraTranslation));

    }

    @Override
    public void addToRenderingEngine(RenderingEngine renderingEngine) {
        renderingEngine.addCamera(this);
    }

}
