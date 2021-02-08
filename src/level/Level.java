package level;

public class Level {

	private int[][] Coordinates;
	private float R;
	private float G;
	private float B;
	private float H;
	private float S;
	private int rows;
	private int colum;
	private int levelSizeWidth;
	private int leveSizeHeight;
	private final int TileSize = 31;
	
	
	public Level(int[][] coordinates, float r, float g, float b, float h, float s,int rows,int colum) 
	{
		Coordinates = coordinates;
		R = r;
		G = g;
		B = b;
		H = h;
		S = s;
		this.rows = rows;
		this.colum = colum;
		levelSizeWidth = colum * TileSize;
		leveSizeHeight = rows * TileSize;
	}


	public int[][] getCoordinates() {
		return Coordinates;
	}


	public float getR() {
		return R;
	}


	public float getG() {
		return G;
	}


	public float getB() {
		return B;
	}


	public float getH() {
		return H;
	}


	public float getS() {
		return S;
	}


	public int getRows() {
		return rows;
	}


	public int getColum() {
		return colum;
	}


	public int getLevelSizeWidth() {
		return levelSizeWidth;
	}


	public int getLeveSizeHeight() {
		return leveSizeHeight;
	}
	
	
	
	
}
