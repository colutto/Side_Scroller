package level;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Models.RawModel;
import Render.Loader;
import player.Player;

public class TileModel {

	static final int PIXEL_TILE = 32;
	private Vector2f firstPoint;
	private int textureNumber;
	private Vector3f position;
	private static int NumberofRows;
	

	public TileModel(Vector2f firstPoint,int textureNumber) {
		super();
		this.firstPoint = firstPoint;
		this.textureNumber = textureNumber;
		position = new Vector3f(firstPoint.y*30,firstPoint.x*30,0);
	}

	public int getTextureNumber() {
		return textureNumber;
	}


	public float getOffsetX()
	{
		int colum = textureNumber % NumberofRows;
		return (float)colum / (float)NumberofRows;
	}
	public float getOffsetY()
	{
		int row = textureNumber / NumberofRows;
		return (float)row / (float)NumberofRows;
	}

	public Vector3f getPosition() {
		return position;
	}

	public static void setNumberofRows(int numberofRows) {
		NumberofRows = numberofRows;
	}

	public static int getNumberofRows() {
		return NumberofRows;
	}

	public static int getPixelTile() {
		return PIXEL_TILE;
	}
	

	
	
	
}
