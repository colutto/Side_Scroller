package level;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Models.RawModel;
import Models.Texture;
import Render.Loader;
import player.Player;

public class LevelModel {

	private Vector2f position;
	private ArrayList<TileModel> models = new ArrayList<TileModel>();
	private Level level;
	private Texture texture;
	int[] indices = {0,1,3,3,1,2};
	float[] textureCoords = {0,0,0,1,1,1,1,0};
	float[] vertices = {0,32,0,0,0,0,32,0,0,32,32,0};
	private RawModel model;
	private Loader loader;
	public static final String oben = "oben";
	public static final String unten = "unten";
	public static final String rechts = "rechts";
	public static final String links = "links";
	
	public LevelModel(ArrayList<TileModel> models,Level level,Texture texture,Loader loader) 
	{
		this.models = models;
		this.level = level;
		this.texture = texture;
		this.loader = loader;
		model = loader.loadToVAO(vertices, indices, textureCoords);
	}


	public Vector2f getPosition() {
		return position;
	}


	public ArrayList<TileModel> getModels() {
		return models;
	}


	public Level getLevel() {
		return level;
	}


	public Texture getTexture() {
		return texture;
	}


	public RawModel getModel() {
		return model;
	}
	public boolean Collision_y(Vector3f player_position,String Seite)
	{
		boolean ergebnis = false;
		for(int i=0;i<models.size();i++)
		{
			float y = models.get(i).getPosition().y;
			float x = models.get(i).getPosition().x;
			if(Seite.equals(oben))
			{
				if(player_position.y - (y+TileModel.PIXEL_TILE) < 0)
					ergebnis = true;
			}	
			if(Seite.equals(unten))
			{
				if( player_position.y+Player.getPlayerSize_y()>y && player_position.x+5/*5 Pixel die die x Position vom Player wegen der Kollisionsberechnung verschieben*/<x+TileModel.PIXEL_TILE && x<player_position.x+Player.getPlayerSizeX()-5/*5 Pixel die die x Position vom Player wegen der Kollisionsberechnung verschieben*/)
				{
					ergebnis = true;
				}
			}
		}
		return ergebnis;
	}
	public boolean Collision_x(Vector3f player_position,String Seite)
	{
		boolean ergebnis = false;
		for(int i=0;i<models.size();i++)
		{
			float x = models.get(i).getPosition().x;
			float y = models.get(i).getPosition().y;
			if(Seite.equals(links))
			{
				if(player_position.x < x+TileModel.PIXEL_TILE && player_position.y<y && player_position.y+Player.getPlayerSize_y()-5/*5Pixel die die y Position vom Player für die Kollisionsberechnung verschieben*/>=y && player_position.x+(Player.getPlayerSizeX()/2)>x)
					ergebnis = true;
			}
			if(Seite.equals(rechts))
			{
				if(player_position.x+Player.getPlayerSizeX() > x && player_position.y<=y && player_position.y+Player.getPlayerSize_y()-5/*5Pixel die die y Position vom Player für die Kollisionsberechnung verschieben*/>=y && player_position.x+(Player.getPlayerSizeX()/2)<x)
					ergebnis = true;
			}
		}
		return ergebnis;
	}
	public float getHeight(Vector3f positionPlayer)
	{
		for(int i=0;i<models.size();i++)
		{
			float y = models.get(i).getPosition().y;
			float x = models.get(i).getPosition().x;
			if(x+TileModel.PIXEL_TILE>positionPlayer.x && x+TileModel.PIXEL_TILE<positionPlayer.x+Player.getPlayerSizeX())
			{
				return y;
			}
		}
		return 0;
	}
	
	
	
	
	
	
	
	
}
