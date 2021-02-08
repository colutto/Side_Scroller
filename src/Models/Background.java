package Models;

import org.lwjgl.util.vector.Vector3f;

import Render.Loader;

public class Background {
	
	private float[] vertices = {0,256,0,0,0,0,1024,0,0,1024,256,0};
	private float[] textureCoords = {0,0,0,1,1,1,1,0};
	private int[] indices = {0,1,3,3,1,2};
	private TexturedModel texturedModel;
	private RawModel model;
	private Vector3f position;
	private int levelSizeWidth;
	private int levelSizeHeight;
	private final int WOLKENSIZE_X = 1024;
	private static final int WOLKENSIZE_Y = 256;
	
	public Background(Loader loader, int levelSizeWidth,int levelSizeHeight)
	{
		this.levelSizeHeight = levelSizeHeight;
		this.levelSizeWidth = levelSizeWidth;
		model = loader.loadToVAO(vertices, indices, textureCoords);
		texturedModel = new TexturedModel(model,new Texture(loader.loadTexture("wolke2")));
		position = new Vector3f();
		position.y = 0;
	}

	public TexturedModel getTexturedModel() {
		return texturedModel;
	}
	public void move()
	{
		position.x -= 1;
		if(position.x<-WOLKENSIZE_X)
		{
			position.x = levelSizeWidth + WOLKENSIZE_X;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public static int getWolkensizeY() {
		return WOLKENSIZE_Y;
	}

	
	
	
	
}
