package Render;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	private final static int height = 960;
	private final static int width = 1600;
	private static float delta;
	private static float lastFrameTime;
	
	
	public static void DisplayOpen()
	{
		ContextAttribs attribs = new ContextAttribs(3,3)
				.withForwardCompatible(true)
				.withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create(new PixelFormat().withSamples(8),attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, width, height);
		lastFrameTime = getCurrentTime();
	}
	public static void DisplayUpdate()
	{
		Display.update();
		long currentTime = getCurrentTime();
		delta = (currentTime - lastFrameTime) / 1000;
		lastFrameTime = currentTime;
		Display.sync(60);
	}
	public static void DisplayClose() {
		Display.destroy();
		
	}
	public static int getHeight()
	{
		return Display.getX();
	}
	private static long getCurrentTime()
	{
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}
	public static float getDelta() {
		return delta;
	}
	
}
