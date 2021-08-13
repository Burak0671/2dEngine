package engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {

	private static long _window = 0;
	
	private static int _WIDTH = 0;
	private static int _HEIGHT = 0;
	private static boolean _FULLSCREEN = false;
	
	public static void create(int width, int height, String title, boolean fullscreen) {
		
		GLFWVidMode videoMode = null;
		
		_WIDTH = width;
		_HEIGHT = height;
		_FULLSCREEN = fullscreen;
		
		try {
			if(!glfwInit()) {
				throw new Exception("GLFW init edilemedi!");
			}
			
			if(_FULLSCREEN == true) {
				videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
				_WIDTH  = videoMode.width();
				_HEIGHT = videoMode.height();
				_window = glfwCreateWindow(_WIDTH, _HEIGHT, title, glfwGetPrimaryMonitor(), 0);
			}
			else {
				_window = glfwCreateWindow(_WIDTH, _HEIGHT, title, 0, 0);				
				videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
				glfwSetWindowPos(_window, (videoMode.width() - _WIDTH) / 2, (videoMode.height() - _HEIGHT) / 2);
			}
			
			glfwMakeContextCurrent(_window);
			
			GL.createCapabilities();
			
			glViewport(0, 0, _WIDTH, _HEIGHT);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void update() {
		glfwSwapBuffers(_window);
	}
	
	public static void close() {
		glfwDestroyWindow(_window);
		glfwTerminate();
	}
	
	public static boolean notUpdating() {
		return glfwWindowShouldClose(_window);
	}

	public static long getWindow() {
		return _window;
	}

	public static int get_WIDTH() {
		return _WIDTH;
	}

	public static int get_HEIGHT() {
		return _HEIGHT;
	}
	
}
