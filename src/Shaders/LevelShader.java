package Shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

public class LevelShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/Shaders/LevelVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/Shaders/LevelFragmentShader.txt";
	
	private int location_ViewMatrix;
	private int location_orhtographicMatrix;
	private int location_TransformationMatrix;
	private int location_Offset;
	private int location_Rows;
	
	public LevelShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	
	
	
	
	@Override
	protected void getAllUniformLocations() {
		location_ViewMatrix = super.getUniformLocation("ViewMatrix");
		location_orhtographicMatrix = super.getUniformLocation("orhtographicMatrix");
		location_TransformationMatrix = super.getUniformLocation("transformationMatrix");
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
		super.loadMatrix(location_ViewMatrix, ViewMatrix);
	}
	public void loadOrhtographicMatrix(Matrix4f orhtographicMatrix)
	{
		super.loadMatrix(location_orhtographicMatrix, orhtographicMatrix);
	}
	public void loadTransformationMatrix(Matrix4f transformationMatrix)
	{
		super.loadMatrix(location_TransformationMatrix, transformationMatrix);
	}
	public void loadOffset(Vector2f vector)
	{
		super.loadVector2f(location_Offset, vector);
	}
	public void loadRows(float row)
	{
		super.loadFloat(location_Rows, row);
	}
}
