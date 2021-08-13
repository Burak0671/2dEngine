package engine.gameObjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.joml.Vector2f;
import org.joml.Vector3f;

public interface GameObject {
	
	public void render();
	
	public void setPosition(Vector2f newPosition);
	public void setScale(Vector2f newScale);
	public void setRotation(float angle, Vector3f newRotation);
	
	public void increasePosition(Vector2f position);
	public void increaseScale(Vector2f scale);
	public void increaseRotation(float angle, Vector3f rotation);
	
	public Vector2f getPosition();
	public Vector2f getScale();
	public Vector3f getRotation();
	
	/* Box2D Fizik motoru için... */
	
	public void setPosition(Vec2 pos);
	public void setRotation(double angle, Vec3 rot);
}
