package sandbox;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.Camera;
import engine.EngineCommand;
import engine.Input;
import engine.KeyCode;
import engine.MousePosition;
import engine.RenderCommand;
import engine.Timer;
import engine.Window;
import engine.editor.Editor;
import engine.editor.Line;
import engine.gameObjects.Box;
import engine.gameObjects.GameObject;
import engine.gameObjects.Light;
import engine.physics.Box2D;
import engine.toolBox.Maths;

public class Game {

	//private static List<TexturedModel> buttons = new ArrayList<TexturedModel>();
	private static List<Box> boxes = new ArrayList<Box>();
	private static List<Light> lights = new ArrayList<Light>();
	
	private static boolean tileChange = true;
	
	public static void main(String[] args) {
		
		Window.create(1290, 920, "Platformer Engine", true);
		
		Camera camera = new Camera(Window.get_WIDTH(), Window.get_HEIGHT());
		
		GameObject box1 = new Box(new Vector2f(0,0),  new Vector2f(50,50), 0, new Vector3f(0, 0, 0), "lava.jpg");
		GameObject box2 = new Box(new Vector2f(0,0),  new Vector2f(50,50), 0, new Vector3f(0, 0, 0), "stone.png");
		
		Light light1 = new Light(new Vector2f(-.7f, .5f), 500, new Vector3f(1, 0, 0));
		Light light2 = new Light(new Vector2f(-.8f, .5f), 500, new Vector3f(1, 1, 0));
		Light light3 = new Light(new Vector2f(-.8f + 0.05f, .6f), 500, new Vector3f(0, 0, 1));
		
		Line line = new Line(new Vector2f(-1f, 0f),  new Vector2f(20,0), 0, new Vector3f(0, 0, 0));
		
		//Button btn1 = new Button(new Vector2f(-1f, -4f),  new Vector2f(50,50), 0, new Vector3f(0, 0, 0), "lava.jpg");

		Box2D physics = new Box2D();
		physics.addDynamicBox(box1);		
		//physics.addStaticBox(box2);

		// OPENGL SETTINGS \\
		RenderCommand.enableTransparency();
		
		// CROSS PLATFORM FPS LIMITER \\
		double frame_cap = 1.0 / 60.0;
		double frame_time = 0;
		int frames = 0;
		double time = Timer.getTime();
		double unprocessed = 0;
		
		// GLFW SETTINGS \\
		glfwSetCursorPosCallback(Window.getWindow(), new MousePosition()); // <- Update mouse position.
		glfwSetScrollCallback(Window.getWindow(), new Input());
		
		while(!Window.notUpdating()) {
			
			boolean can_render = false;
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed += passed;
			frame_time  += passed;
			time = time_2;
			
			while(unprocessed >= frame_cap) {
				unprocessed -= frame_cap;
				can_render = true;
				
				Vector2f mousePos = Maths.getNormalisedDeviceCoordinates();
				
				//double mouse_grid_x = Math.floor((MousePosition.getY()) + (MousePosition.getX()));
				//double mouse_grid_y = Math.floor((-MousePosition.getX()) + (MousePosition.getY()));
				
				//mousePos.x = (float) Math.round((mousePos.y / 5f) + (mousePos.x / 5f));
				//mousePos.y = (float) Math.round((-mousePos.x / 5f) + (mousePos.y / 5f));
				
				// Engine Inputs \\
				if(Input.isKeyPressed(KeyCode.KEY_ESCAPE)) {
					EngineCommand.closeGame();
				}
				if(Input.isKeyPressed(KeyCode.KEY_1)) {
					RenderCommand.enableWireframe();
				}
				if(Input.isKeyPressed(KeyCode.KEY_2)) {
					RenderCommand.disableWireframe();
				}
				
				// Game Inputs \\
				if(Input.isKeyPressed(KeyCode.KEY_A)) {
					camera.increasePosition(new Vector3f(6, 0, 0));
					if(Input.isKeyPressed(KeyCode.KEY_LEFT_SHIFT) || Input.isKeyPressed(KeyCode.KEY_RIGHT_SHIFT)) {
						camera.increasePosition(new Vector3f(12, 0, 0));
					}
				}
				if(Input.isKeyPressed(KeyCode.KEY_W)) {
					camera.increasePosition(new Vector3f(0, -6, 0));
					if(Input.isKeyPressed(KeyCode.KEY_LEFT_SHIFT) || Input.isKeyPressed(KeyCode.KEY_RIGHT_SHIFT)) {
						camera.increasePosition(new Vector3f(0, -12, 0));
					}
				}
				if(Input.isKeyPressed(KeyCode.KEY_S)) {
					camera.increasePosition(new Vector3f(0, 6, 0));
					if(Input.isKeyPressed(KeyCode.KEY_LEFT_SHIFT) || Input.isKeyPressed(KeyCode.KEY_RIGHT_SHIFT)) {
						camera.increasePosition(new Vector3f(0, 12, 0));
					}
				}
				if(Input.isKeyPressed(KeyCode.KEY_D)) {
					camera.increasePosition(new Vector3f(-6, 0, 0));
					if(Input.isKeyPressed(KeyCode.KEY_LEFT_SHIFT) || Input.isKeyPressed(KeyCode.KEY_RIGHT_SHIFT)) {
						camera.increasePosition(new Vector3f(-12, 0, 0));
					}
				}
				if(Input.isKeyPressed(KeyCode.KEY_UP)) {
					camera.decreaseZoomLevel(20);
					if(Input.isKeyPressed(KeyCode.KEY_LEFT_SHIFT) || Input.isKeyPressed(KeyCode.KEY_RIGHT_SHIFT)) {
						camera.decreaseZoomLevel(40);
					}
				}
				if(Input.isKeyPressed(KeyCode.KEY_DOWN)) {
					camera.increaseZoomLevel(20);
					if(Input.isKeyPressed(KeyCode.KEY_LEFT_SHIFT) || Input.isKeyPressed(KeyCode.KEY_RIGHT_SHIFT)) {
						camera.increaseZoomLevel(40);
					}
				}
				
				if(mousePos.x >= 0.90) {
					camera.increasePosition(new Vector3f(-15, 0, 0));
				}
				if(mousePos.x <= -0.90) {
					camera.increasePosition(new Vector3f(15, 0, 0));
				}
				if(mousePos.y >= 0.90) {
					camera.increasePosition(new Vector3f(0, 15, 0));
				}
				if(mousePos.y <= -0.90) {
					camera.increasePosition(new Vector3f(0, -15, 0));
				}
				
				if(Input.getyOffset() >= 0) {
					camera.decreaseZoomLevel(50);
				}
				if(Input.getyOffset() <= 0) {
					camera.increaseZoomLevel(50);
				}
				
				if(Input.isKeyPressed(KeyCode.KEY_Q)) {
					tileChange = !tileChange;
				}
				if(Input.isKeyPressed(KeyCode.KEY_LEFT_CONTROL) && Input.isKeyPressed(KeyCode.KEY_Z)) {
					if(tileChange) {
						if(boxes.size() > 0) {
							boxes.remove(boxes.size() - 1);
						}
					}
					else {
						if(lights.size() > 0) {
							lights.remove(lights.size() - 1);
						}
					}
				}
				if(Input.isKeyPressed(KeyCode.KEY_E)) {
					lights.clear();
					boxes.clear();
				}
				if(Input.isMouseButtonDown(1)) {
					if(tileChange) {	
						Box box = new Box(new Vector2f(mousePos.x * 50f, -mousePos.y * 50f),  new Vector2f(50,50), 0, new Vector3f(0, 0, 0), "lava.jpg");
						boxes.add(box);
						Editor.boxesPos.add(box.getPosition());
					}
					else {
						Light light = new Light(new Vector2f(mousePos.x * 50f, -mousePos.y * 50f), 60, new Vector3f(1, 1, 0));
						lights.add(light);
						Editor.lightsPos.add(light.getPosition());
					}
				}
				
				//MousePosition.updateRayCast();
				//box2.setPosition(new Vector2f(-Camera.getPosition().x / 50, -Camera.getPosition().y / 50));
				//box2.setPosition(new Vector2f(MousePosition.getCurrentRay().x, -MousePosition.getCurrentRay().y));
				//System.out.println("X:" + MousePosition.getCurrentRay().x + " Y: " + -MousePosition.getCurrentRay().y
				//			+ " Z: " + MousePosition.getCurrentRay().z);
				box2.setPosition(new Vector2f(mousePos.x * 50, -mousePos.y * 50));

				Input.checkEvents();
				
				if(frame_time >= 1) {
					frame_time = 0;
					System.out.println("FPS:" + frames);
					frames = 0;
				}
			}
	
			if(can_render) {
				
				// Rendering \\
				RenderCommand.clearColor(0.4f, 0.4f, 0.4f, 1.0f);
				RenderCommand.clear();
		        
				//btn1.checkHover();
				//btn1.render();
				
				light1.render();
				light2.render();
				light3.render();
				
				line.render();
				
				box1.render();
				box2.render();
				
				for(Box box: boxes) {
					box.render();
				}
				for(Light light: lights) {
					light.render();
				}
				
				physics.updateDynamicObject(box1);
				physics.updatePhysicsWorld();
				
				Window.update();
				frames++;
			}
		}
		
		Window.close();
		physics.deleteObjects();
		
		boxes.clear();
		lights.clear();
		
		Editor.clearAll();
	}
}
