package player;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import Models.Camera;
import Models.RawModel;
import Toolbox.Maths;

public class Player_Renderer {

	private Camera camera;
	private Matrix4f orthographicMatrix;
	private PlayerShader shader;
	
	public Player_Renderer(Camera camera, Matrix4f orthographicMatrix)
	{
		shader = new PlayerShader();
		this.camera = camera;
		this.orthographicMatrix = orthographicMatrix;
		shader.start();
		shader.loadOrhtographicMatrix(orthographicMatrix);
		shader.stop();
	}
	public void render(Player player)
	{
		prepare(player);
		draw(player.getModel());
		close();
	}
	private void prepare(Player player)
	{
		shader.start();
		RawModel model = player.getModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		shader.loadRows(player.getColums());
		shader.loadOffset(player.calculateOffset());
		shader.loadViewMatrix(Maths.createViewMatrix(camera));
		shader.loadTransformationMatrix(Maths.createTranformationMatrix(player.getPosition(), player.getRotX(), player.getRotY(), player.getRotZ(), player.getScale(),Player.getPlayerSize_y()));
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,player.getTexture().getTextureID());
	}
	private void draw(RawModel model)
	{
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
	}
	private void close()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
}
