package Shaders;

import org.lwjgl.util.vector.Matrix4f;

public class BackgroundShader extends ShaderProgram 
{
	private static final String VERTEXFILE = "src/Shaders/BackgroundVertexShader.txt";
	private static final String FRAGMENTFILE = "src/Shaders/BackgroundFragmentShader.txt";
	private int location_viewMatrix;
	private int location_orthographicMatrix;
	private int location_transformationMatrix;

	public BackgroundShader() {
		super(VERTEXFILE, FRAGMENTFILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_orthographicMatrix = super.getUniformLocation("orthographicMatrix");
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");	
	}
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	public void loadViewMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_viewMatrix, matrix);
	}
	public void loadOrthographicMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_orthographicMatrix, matrix);
	}

}
