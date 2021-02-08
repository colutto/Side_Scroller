package Render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import Models.Camera;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.LevelShader;
import Toolbox.Maths;
import level.LevelModel;
import level.TileModel;
import player.Player;

public class LevelRenderer {

	public LevelShader backShader;
	private Camera camera;
	private Matrix4f orhtographicMatrix;
	
	public LevelRenderer(Camera camera,Matrix4f orhtographicMatrix)
	{
		backShader = new LevelShader();
		this.camera = camera;
		this.orhtographicMatrix = orhtographicMatrix;
		backShader.start();
		backShader.loadOrhtographicMatrix(orhtographicMatrix);
		backShader.stop();
	}

	public void render(LevelModel level)
	{	
		prepare(level);
		draw(level);
		close();
		
	}
	private void prepare(LevelModel level)
	{
		backShader.start();
		GL30.glBindVertexArray(level.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		//GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glCullFace(GL11.GL_BACK);
		backShader.loadViewMatrix(Maths.createViewMatrix(camera));
		backShader.loadRows(TileModel.getNumberofRows());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, level.getTexture().getTextureID());
		
	}
	private void draw(LevelModel level)
	{
		for(int i=0;i<level.getModels().size();i++)
		{
			TileModel tile = level.getModels().get(i);
			backShader.loadOffset(new Vector2f(tile.getOffsetX(),tile.getOffsetY()));
			backShader.loadTransformationMatrix(Maths.createTranformationMatrix(tile.getPosition(),0, 0, 0, 1,TileModel.getPixelTile()));
			GL11.glDrawElements(GL11.GL_TRIANGLES, level.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			
		}
	}
	private void close()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		backShader.stop();
	}
  
}
