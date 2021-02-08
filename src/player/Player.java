package player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Models.Camera;
import Models.RawModel;
import Models.Texture;
import Models.TexturedModel;
import Render.Loader;
import Render.MasterRenderer;
import Render.Window;
import level.LevelModel;

public class Player {

	private static final int Player_size_y = 62;
	private static final int Player_size_x = 48;
	private float rotX = 0;
	private float rotY = 0;
	private float rotZ = 0;
	private float scale = 1;
	private float[] vertices = {0,64,0,0,0,0,64,0,0,64,64,0};
	private int[] indices = {0,1,3,3,1,2};
	private float[] textCoords = {0,0,0,1,1,1,1,0};
	private RawModel model;
	private TexturedModel texturedModel;
	private Loader loader;
	private Texture textureStand;
	private Texture textureWalk;
	private Texture textureWalk_Left;
	public final int Colums = 8;
	private float elapsedTime;
	private final float AnimationTime = 0.4f;
	private int index = 0;
	private LevelModel level;
	private final int gravity = -5;
	private final int jumbPower = 200;
	private Camera camera;
	private final int walkingSpeed = 120;
	private float upwardsSpeed = 0;
	private static Vector3f position;
	
	
	public Player(Vector3f position2, Loader loader,LevelModel level,Camera camera) {
		super();
		this.camera = camera;
		position = position2;
		this.loader = loader;
		this.level = level;
		model = loader.loadToVAO(vertices, indices, textCoords);
		textureStand = new Texture(loader.loadTexture("/player/second_player_stand"));
		textureWalk = new Texture(loader.loadTexture("/player/second_player_gehen"));
		textureWalk_Left = new Texture(loader.loadTexture("/player/second_player_gehenLinks"));
		
	}
	public float getRotX() {
		return rotX;
	}
	public float getRotY() {
		return rotY;
	}
	public float getRotZ() {
		return rotZ;
	}
	public float getScale() {
		return scale;
	}
	public static Vector3f getPosition() {
		return position;
	}
	
	public RawModel getModel() {
		return model;
	}
	

	public static int getPlayerSize_y() {
		return Player_size_y;
	}
	
	public static int getPlayerSizeX() {
		return Player_size_x;
	}
	public Texture getTexture() {
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			return textureWalk;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			return textureWalk_Left;
		}
		return textureStand;
	}
	
	public int getColums() {
		return Colums;
	}
	public float calculateOffset()
	{
		int colum = calculateIndex() % Colums;
		return (float)colum / (float)Colums;
	}
	private int calculateIndex()
	{
		if(elapsedTime>AnimationTime)
		{
			elapsedTime = 0;
		}
		elapsedTime += Window.getDelta();
		float lifeFactor = elapsedTime / AnimationTime;
		float atlasProgression = lifeFactor * Colums;
		return (int)Math.floor(atlasProgression);
	}
	public void move()
	{
		/*boolean jumping = false; 
		upwardsSpeed += gravity * Window.getDelta();
		this.position.y -= upwardsSpeed * Window.getDelta();*/
		
		//für die Gravitation
		if(!level.Collision_y(this.position,level.unten))
		{
			position.y -=gravity;
		}
		//um zu Springen
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)&&level.Collision_y(this.position,level.unten))
		{
			this.position.y -= jumbPower;
					
		}
		//nach rechts gehen
		if(Keyboard.isKeyDown(Keyboard.KEY_D)&&!level.Collision_x(this.position,level.rechts))
		{
			this.position.x += walkingSpeed * Window.getDelta();
		}
		//nach links gehen
		if(Keyboard.isKeyDown(Keyboard.KEY_A)&&!level.Collision_x(this.position,level.links))
		{
			this.position.x -= walkingSpeed * Window.getDelta();
		}
		//Camera folgt Spieler
		Vector3f cameraPosition = new Vector3f();
		cameraPosition = camera.getPosition();
		if(this.position.x>cameraPosition.x + MasterRenderer.getRight())
		{
			camera.setPosition(new Vector3f(cameraPosition.x + Math.round(walkingSpeed*Window.getDelta()),cameraPosition.y,cameraPosition.z));
		}
	}
	
}
