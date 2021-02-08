package Render;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import Models.Background;
import Models.Camera;
import Models.TexturedModel;
import Toolbox.Maths;
import level.LevelModel;
import player.Player;
import player.Player_Renderer;

public class MasterRenderer {

	private Matrix4f orhtographicMatrix;
	private final float NEAR_PLANE = 0f; 
	private final float FAR_PLANE = 1;
	private static final float RIGHT = 800;
	private static final float LEFT = -800;
	private final float BOTTOM = -450;
	private static final float TOP = 450;
	private Camera camera;
	private Player player;
	
	
	private LevelRenderer levelRenderer;
	private BackgroundRenderer backgroundRenderer;
	private Player_Renderer playerRenderer;
	
	public MasterRenderer(Camera camera,Player player)
	{
		this.camera = camera;
		this.player = player;
		orhtographicMatrix = Maths.orthographicMatrix(FAR_PLANE, NEAR_PLANE, RIGHT, LEFT, BOTTOM, TOP);
		levelRenderer = new LevelRenderer(this.camera,orhtographicMatrix);
		backgroundRenderer = new BackgroundRenderer(this.camera, orhtographicMatrix);
		playerRenderer = new Player_Renderer(camera, orhtographicMatrix);
	}
	public void renderLevel(LevelModel level,Background background)
	{
		prepare();
		backgroundRenderer.render(background);
		levelRenderer.render(level);
		playerRenderer.render(player);
	}
	
	private void prepare()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0.4f, 0.8f, 1f, 1);
	}
	public static float getLeft() {
		return LEFT;
	}
	public static float getTop() {
		return TOP;
	}
	public static float getRight() {
		return RIGHT;
	}
	
	
	
	
	

}
