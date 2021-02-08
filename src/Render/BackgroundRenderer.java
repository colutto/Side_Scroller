package Render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Models.Background;
import Models.Camera;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.BackgroundShader;
import Toolbox.Maths;

public class BackgroundRenderer {

	private BackgroundShader shader;
	private Camera camera;
	private Matrix4f orthographicMatrix;
	
	public BackgroundRenderer(Camera camera, Matrix4f orthographicMatrix)
	{
		shader = new BackgroundShader();
		this.camera = camera;
		this.orthographicMatrix = orthographicMatrix;
		shader.start();
		shader.loadOrthographicMatrix(orthographicMatrix);
		shader.stop();
	}
	
	public void render(Background background)
	{
		prepare(background);
		draw(background.getTexturedModel());
		close();
		
	}
	private void prepare(Background background)
	{
		shader.start();
		RawModel model = background.getTexturedModel().getModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		shader.loadViewMatrix(Maths.createViewMatrix(camera));
		shader.loadTransformationMatrix(Maths.createTranformationMatrix(background.getPosition(), 0, 0, 0, 1,Background.getWolkensizeY()));
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,background.getTexturedModel().getTexture().getTextureID());
		
	}
	private void draw(TexturedModel texturedModel)
	{
		GL11.glDrawElements(GL11.GL_TRIANGLES, texturedModel.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
	}
	private void close()
	{
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		GL11.glDisable(GL11.GL_CULL_FACE);
		shader.stop();
	}
}
