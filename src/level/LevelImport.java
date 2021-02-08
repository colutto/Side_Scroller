package level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import Models.Texture;
import Render.Loader;

public class LevelImport {

	
	public static LevelModel createLevel(String filename,String TextureFilename,Loader loader)
	{
		Level level = importLevelFile(filename);
		int[][] Coordinates = level.getCoordinates();
		ArrayList<TileModel> models = new ArrayList();
		for(int row=0;row<level.getRows();row++)
		{
			for(int colum=0;colum<level.getColum();colum++)
			{
				if(Coordinates[row][colum] != 0)
				{
					models.add(new TileModel(new Vector2f(row,colum),Coordinates[row][colum]));
				}
			}
		}
		return new LevelModel(models,level,new Texture(loader.loadTexture(TextureFilename)),loader);
		
	}
	
	
	private static Level importLevelFile(String filename)
	{
		File file = new File("res/"+filename+".txt");
		if(!file.canRead())
		{
			System.out.println("File konnte nicht gelesen werden");
		}
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String zeile = null;
			zeile = in.readLine();
			String[] currentLine = zeile.split(" ");
			int colum = Integer.parseInt(currentLine[0]);
			int row = Integer.parseInt(currentLine[1]);
			int [][] Coordinates = new int[row][colum];
			zeile = in.readLine();
			currentLine = zeile.split(" ");
			float R = Float.parseFloat(currentLine[1]);
			float G = Float.parseFloat(currentLine[2]);
			float B = Float.parseFloat(currentLine[3]);
			float H = Float.parseFloat(currentLine[4]);
			float S = Float.parseFloat(currentLine[5]);
			zeile = in.readLine();//Überspringen
			zeile = in.readLine();
			currentLine = zeile.split(" ");
			int count = 0;
			for(int i=0;i<row;i++)
			{
				for(int y=0;y<colum;y++)
				{
					Coordinates[i][y] = Integer.parseInt(currentLine[count++]);
				}
			}
			return new Level(Coordinates,R,G,B,H,S,row,colum);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File wurde nicht importiert");
			return null;
		}
	}
}
