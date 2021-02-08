package Models;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private float pitch = 0;
	private float yaw = 0;
	private Vector3f position;
	private int levelSizeHeight;
	private int levelSizeWidth;
	private final int CORRECTION = 2;
	
	
	public Camera(Vector3f position,int levelSizeWidth, int levelSizeHeight)
	{
		this.position = position;
		this.levelSizeHeight = levelSizeHeight;
		this.levelSizeWidth = levelSizeWidth;
	}
	
	public float getPitch() {
		return pitch;
	}

	
	public float getYaw() {
		return yaw;
	}

	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void move()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)/*&&position.x>0*/)
			position.x -= 10; 
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)/*&&position.x<levelSizeWidth-Display.getWidth()-CORRECTION*/)
			position.x += 10; 
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)&&position.y>0)
			position.y += 10; 
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)&&position.y<levelSizeHeight-Display.getHeight())
			position.y -= 10; 
	}

	
}
