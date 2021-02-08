package Toolbox;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Models.Camera;
import Render.MasterRenderer;

public class Maths {

	 public static Matrix4f createTranformationMatrix(Vector3f translation, float rx, float ry, float rz,float scale,int Size)
	    {
		 	final float yKorrektur = MasterRenderer.getTop()-Size;
	        Matrix4f matrix=new Matrix4f();
	        Vector3f nullPosition = new Vector3f(translation.x + MasterRenderer.getLeft(),(-translation.y + yKorrektur),0);
	        matrix.setIdentity();
	        Matrix4f.translate(nullPosition,matrix,matrix);
	        Matrix4f.rotate((float) Math.toRadians(rx),new Vector3f(1,0,0),matrix,matrix);
	        Matrix4f.rotate((float) Math.toRadians(ry),new Vector3f(0,1,0),matrix,matrix);
	        Matrix4f.rotate((float) Math.toRadians(rz),new Vector3f(0,0,1),matrix,matrix);
	        Matrix4f.scale(new Vector3f(scale,scale,scale),matrix,matrix);
	        return matrix;
	    }

	    public static Matrix4f createViewMatrix(Camera camera)
	    {
	        Matrix4f viewMatrix= new Matrix4f();
	        viewMatrix.setIdentity();
	        Matrix4f.rotate((float)Math.toRadians(camera.getPitch()),new Vector3f(1,0,0),viewMatrix,viewMatrix);
	        Matrix4f.rotate((float)Math.toRadians(camera.getYaw()),new Vector3f(0,1,0),viewMatrix,viewMatrix);
	        Vector3f cameraPos=camera.getPosition();
	        Vector3f negativeCameraPos=new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
	        Matrix4f.translate(negativeCameraPos,viewMatrix,viewMatrix);
	        return viewMatrix;
	    }
	    public static Matrix4f orthographicMatrix(float far,float near,float right,float left,float bottom,float top)
	    {
	    	Matrix4f orthographicMatrix = new Matrix4f();
	    	orthographicMatrix.m00 = 2/(right-left);
	    	orthographicMatrix.m11 = 2/(top-bottom);
	    	orthographicMatrix.m22 = -(2/(far-near));
	    	orthographicMatrix.m33 = 1;
	    	orthographicMatrix.m03 = -((right+left)/(right-left));
	    	orthographicMatrix.m13 = -((top+bottom)/(top-bottom));
	    	orthographicMatrix.m23 = -((far+near)/(far-near));
	   
	    	return orthographicMatrix;
	    }
}
