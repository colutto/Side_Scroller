package Render;

import Models.RawModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Loader {

	private ArrayList<Integer> vbos = new ArrayList<>();
	private ArrayList<Integer> vaos = new ArrayList<>();
	private ArrayList<Integer> textures = new ArrayList<>();
	
	public RawModel loadToVAO(float[] position,int[] indices,float[] textureCoords)
	{
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(position, 0,3);
		storeDataInAttributeList(textureCoords, 1, 2);
		unbindVAO();
		return new RawModel(vaoID,indices.length);
	}
	private int createVAO()
	{
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	private void storeDataInAttributeList(float[] data,int attributeNumber,int dimension)
	{
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDateInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, dimension, GL11.GL_FLOAT, false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	private void unbindVAO()
	{
		GL30.glBindVertexArray(0);
	}
	private FloatBuffer storeDateInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	 private void bindIndicesBuffer(int[] indices)
	    {
	        int vboID=GL15.glGenBuffers();
	        vbos.add(vboID);
	        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,vboID);
	        IntBuffer buffer=storeDataInIntBuffer(indices);
	        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
	    }
	 private IntBuffer storeDataInIntBuffer(int[] data)
	    {
	        IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
	        buffer.put(data);
	        buffer.flip();
	        return buffer;
	    }
	 public void cleanUp()
	 {
		 for(int vao:vaos)
		 {
			 GL30.glDeleteVertexArrays(vao);
		 }
		 for(int vbo:vbos)
		 {
			 GL15.glDeleteBuffers(vbo);
		 }
		 for(int texture:textures)
		 {
			 GL11.glDeleteTextures(texture);
		 }
	 }
	 
	 public int loadTexture(String fileName)
	    {
	        Texture texture=null;
	        try
	        {
	            texture=TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
	            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
	            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR_MIPMAP_LINEAR);
	            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS,0);
	            if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic)
	            {
	                float amount = Math.min(4f, GL11.glGetFloat(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
	                GL11.glTexParameterf(GL11.GL_TEXTURE_2D,EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT,amount);
	            }else
	            {
	                System.out.println("Anisotropic don�t work");
	            }
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        int textureID=texture.getTextureID();
	        textures.add(textureID);
	        return textureID;
	    }
}