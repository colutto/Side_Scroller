package player;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import Shaders.ShaderProgram;

public class PlayerShader extends ShaderProgram {

	private static final String vertexFile = "src/player/PlayerVertexShader.txt";
	private static final String fragmentFile = "src/player/PlayerFragmentShader.txt";
	
	private int location_viewMatrix;
	private int location_orthographicMatrix;
	private int location_transformationMatrix;
	private int location_Offset;
	private int location_Rows;
	
	public PlayerShader() 
	{
		super(vertexFile, fragmentFile);
	}
	@Override
	protected void getAllUniformLocations() {
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_orthographicMatrix = super.getUniformLocation("orthographicMatrix");
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_Offset = super.getUniformLocation("Offset");
		location_Rows = super.getUniformLocation("Rows");
		
	}
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		
	}
	public void loadViewMatrix(Matrix4f ViewMatrix)
	{
		super.loadMatrix(location_viewMatrix, ViewMatrix);
	}
	public void loadOrhtographicMatrix(Matrix4f orhtographicMatrix)
	{
		super.loadMatrix(location_orthographicMatrix, orhtographicMatrix);
	}
	public void loadTransformationMatrix(Matrix4f transformationMatrix)
	{
		super.loadMatrix(location_transformationMatrix, transformationMatrix);
	}
	public void loadOffset(float offset)
	{
		super.loadFloat(location_Offset, offset);
	}
	public void loadRows(float row)
	{
		super.loadFloat(location_Rows, row);
	}
	
}
