package engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePosition extends GLFWCursorPosCallback {

	private static double X;
	private static double Y;
	
	private static Vector3f currentRay = new Vector3f();
	private static Matrix4f projectionMatrix = Camera.getProjection();
	private static Matrix4f viewMatrix = Camera.getProjection().invert(projectionMatrix);
	
	@Override
	public void invoke(long window, double x, double y) {
		X = x;
		Y = y;
	}

	public static double getX() {
		return X;
	}

	public static  double getY() {
		return Y;
	}

	public static void setX(double x) {
		X = x;
	}

	public static void setY(double y) {
		Y = y;
	}
	
	
	// RAYCAST \\
	
	public static Vector2f getNormalisedDeviceCoordinates(double x, double y) {
		x = (2.0f * MousePosition.getX()) / Window.get_WIDTH()  - 1f;
		y = (2.0f * MousePosition.getY()) / Window.get_HEIGHT() - 1f;
		return new Vector2f((float)x, (float)y);
	}
	
	public static void updateRayCast() {
		currentRay = calculateMouseRay();
	}
	
	private static Vector3f calculateMouseRay() {
		float mouseX = (float) MousePosition.getX();
		float mouseY = (float) MousePosition.getY();
		Vector2f normalizedCoords = getNormalisedDeviceCoordinates((float)mouseX, (float)mouseY);
		Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1.0f, 1.0f);
		Vector4f eyeCoords = toEyeCoords(clipCoords);
		Vector3f worldRay = toWorldCoords(eyeCoords);
		return worldRay;
	}
	
	private static Vector3f toWorldCoords(Vector4f eyeCoords) {
		Matrix4f invertedView = new Matrix4f().invert(viewMatrix);
		Vector4f rayWorld = eyeCoords.mul(invertedView);
		Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
		mouseRay.normalize();
		return mouseRay;
	}
	
	private static Vector4f toEyeCoords(Vector4f clipCoords) {
		Matrix4f invertedProjection = new Matrix4f().invert(projectionMatrix);
		Vector4f eyeCoords = clipCoords.mul(invertedProjection);
		return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
	}

	public static Vector3f getCurrentRay() {
		return currentRay;
	}
	
}
