package engine;

import static org.lwjgl.opengl.GL11.*;

public class RenderCommand {

	public static void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	public static void clearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);		
	}
	
	public static void enableWireframe() {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	}
	public static void disableWireframe() {
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}
	
	public static void enableTexture() {
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void lineWidth(float width) {
		glLineWidth(width);
	}
	
	public static void enableTransparency() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
}
