package pl.grm.bol.game;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glRectf;

import java.util.logging.Level;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pl.grm.bol.engine.graphic.Mesh;
import pl.grm.bol.engine.graphic.RenderUtil;
import pl.grm.bol.engine.graphic.Vector3f;
import pl.grm.bol.engine.graphic.Vertex;
import pl.grm.bol.engine.graphic.rendering.states.StateOfGame;
import pl.grm.bol.lib.BLog;

public class GamePresenter {
	private GameController gameController;
	private BLog logger;
	private static int WIDTH_GAME_WINDOW = 800;
	private static int HEIGHT_GAME_WINDOW = 600;
	private static String TITLE_GAME_WINDOW = "Battle of Legends";
	private RenderUtil renderUtil;
	private Mesh mesh;

	public GamePresenter(BLog bLog, GameController gameController) {
		this.logger = bLog;
		this.gameController = gameController;
	}

	public void createMesh() {
		mesh = new Mesh();
		Vertex[] data = new Vertex[] { new Vertex(new Vector3f(-1, -1, 0)),
				new Vertex(new Vector3f(-1, 1, 0)),
				new Vertex(new Vector3f(0, 1, 0)) };
		mesh.addVertices(data);
	}

	public void createWindow() {
		Display.setTitle(TITLE_GAME_WINDOW);
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH_GAME_WINDOW,
					HEIGHT_GAME_WINDOW));
			Display.create();
		} catch (LWJGLException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
	}

	public RenderUtil getRenderUtil() {
		return renderUtil;
	}

	public void renderState(StateOfGame state) {
		switch (state) {
		case LOADING_GAME:
			glColor3f(1.0f, 0f, 1.0f);
			glRectf(-600, -800, 600, 800);
			break;
		case GAME_MENU:
			glColor3f(1.0f, 0f, 0f);
			glRectf(-600, -800, 600, 800);
			break;
		case GAME_RUNNING:

			break;
		case GAME_STOPPED:

			break;
		default:
			System.out.println("ERROR");
			break;
		}
	}

	public void setBLog(BLog bLog) {
		this.logger = bLog;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void setRenderUtil(RenderUtil renderUtil) {
		this.renderUtil = renderUtil;
	}

	public void stopGame() {
		gameController.stopGame();
	}
}
