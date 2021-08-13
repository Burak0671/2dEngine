package engine.toolBox;

import org.joml.Vector2f;

import engine.MousePosition;
import engine.Window;

public class Maths {
	public static Vector2f getNormalisedDeviceCoordinates() {
		double x = (2.0f * MousePosition.getX()) / Window.get_WIDTH()  - 1f;
		double y = (2.0f * MousePosition.getY()) / Window.get_HEIGHT() - 1f;
		return new Vector2f((float)x, (float)y);
	}
	
}
