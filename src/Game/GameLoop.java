package Game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Models.Background;
import Models.Camera;
import Render.Loader;
import Render.MasterRenderer;
import Render.Window;
import level.LevelImport;
import level.LevelModel;
import level.TileModel;
import player.Player;

public class GameLoop {

	public static void main(String args[]) {
		
		Window.DisplayOpen();
		
		Loader loader  = new Loader();
		LevelModel firstLevel = LevelImport.createLevel("erstesLevel", "erstesLevel", loader);
		TileModel.setNumberofRows(8);
		
		Camera camera = new Camera(new Vector3f(0,0,0),firstLevel.getLevel().getLevelSizeWidth(),firstLevel.getLevel().getLeveSizeHeight());
		Player player = new Player(new Vector3f(0,0,0),loader,firstLevel,camera);
		
		MasterRenderer renderer = new MasterRenderer(camera,player);
		
		
		
		Background background = new Background(loader,firstLevel.getLevel().getLevelSizeWidth(),firstLevel.getLevel().getLeveSizeHeight());
		
		
		
		
		while(!Display.isCloseRequested())
		{
			renderer.renderLevel(firstLevel, background);
			camera.move();
			background.move();
			player.move();
			
			
			Window.DisplayUpdate();
		}
		loader.cleanUp();
		Window.DisplayClose();
		
	}
	
	
}


