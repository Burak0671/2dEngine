package engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

	private static Vector3f position;
	private static Matrix4f projection;
	
	private int WIDTH  = 0;
	private int HEIGHT = 0;
	
	public Camera(int width, int height) {
		
		this.WIDTH  = width;
		this.HEIGHT = height;
		
		position = new Vector3f(0, 0, 0);
		projection = new Matrix4f().setOrtho2D(-WIDTH/2, WIDTH/2, -HEIGHT/2, HEIGHT/2);
	}
	
	public void setPosition(Vector3f newPosition) {
		position = newPosition;
	}
	
	public void increasePosition(Vector3f positionn) {
		position.add(positionn);
	}
	
	public void increaseZoomLevel(float value) {
		this.WIDTH  += value;
		this.HEIGHT += value;
		projection = new Matrix4f().setOrtho2D(-WIDTH/2, WIDTH/2, -HEIGHT/2, HEIGHT/2);
		if(this.WIDTH >= 4580 && this.HEIGHT >= 3740) {
			this.WIDTH  = 4580;
			this.HEIGHT = 3740;
		}
	}

	public void decreaseZoomLevel(float value) {
		this.WIDTH  -= value;
		this.HEIGHT -= value;
		projection = new Matrix4f().setOrtho2D(-WIDTH/2, WIDTH/2, -HEIGHT/2, HEIGHT/2);
		if(this.WIDTH <= 2060 && this.HEIGHT <= 1220) {
			this.WIDTH  = 2060;
			this.HEIGHT = 1220;
		}
	}
	
	public void setZoomLevel(float value) {
		this.WIDTH  = (int) value;
		this.HEIGHT = (int) value;
	}
	
	public static Vector3f getPosition() {
		return position;
	}

	public static Matrix4f getProjection() {
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(getPosition());
		
		target = projection.mul(pos, target);
		
		return target;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}
	
}
