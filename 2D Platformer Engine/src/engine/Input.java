package engine;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Input extends GLFWScrollCallback {
	
	private static double xOffset;
	private static double yOffset;
	
	public static boolean isKeyPressed(int keycode) {
		return glfwGetKey(Window.getWindow(), keycode) == GLFW_PRESS;
	}
	
	public static boolean isKeyReleased(int keycode) {
		return glfwGetKey(Window.getWindow(), keycode) == GLFW_RELEASE;
	}
	
	public static boolean isMouseButtonDown(int mouseCode) {
		return glfwGetMouseButton(Window.getWindow(), mouseCode) == GLFW_PRESS;
	}
	
	public static boolean isMouseButtonReleased(int mouseCode) {
		return glfwGetMouseButton(Window.getWindow(), mouseCode) == GLFW_RELEASE;
	}
	
	public static void checkEvents() {
		glfwPollEvents();
	}

	@Override
	public void invoke(long ekran, double xoffset, double yoffset) { // <- For scroll whell.
		xOffset = xoffset;
		yOffset = yoffset;
	}

	public static double getxOffset() {
		return xOffset;
	}

	public static double getyOffset() {
		return yOffset;
	}
	
}
