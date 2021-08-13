package engine.gameObjects;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.Camera;
import engine.rendering.ShaderedModel;
import engine.shader.LightShader;

public class Light {
	
	float[] vertices = new float[] {
		-.5f, .5f, 0,
		 .5f, .5f, 0,
		 .5f,-.5f, 0,
		-.5f,-.5f, 0
	};
			
	int[] indices = new int[] {
		 0,1,2,
		 2,3,0
	};
	
	private ShaderedModel model = null;
	private LightShader shader = null;
	private Matrix4f projectionMatrix = null;
	
	private Vector2f POSITION = new Vector2f();
	private Vector3f COLOUR = new Vector3f();
	
	private float SIZE = 0;
	private float time = 0;
	
	public Light(Vector2f position, float size, Vector3f colour) {
		
		this.POSITION = position;
		this.SIZE = size;
		this.COLOUR = colour;
		
		this.model = new ShaderedModel(vertices, indices);
		this.shader = new LightShader();
		
		this.projectionMatrix = new Matrix4f()
				.scale(SIZE, SIZE, 0)
				.translate(new Vector3f(POSITION.x, POSITION.y, 0));
		
	}
	
	public void render() {
		shader.start();
		shader.setLightColour(COLOUR);
		shader.loadProjectionMatrix(Camera.getProjection().mul(projectionMatrix));
		time += 0.1f;
		shader.time(time);
		model.render();
		shader.stop();
	}

	public Vector3f getColour() {
		return COLOUR;
	}

	public void setColour(Vector3f colour) {
		this.COLOUR = colour;
		this.shader.setLightColour(COLOUR);
	}

	public Vector2f getPosition() {
		return POSITION;
	}

	public void setPosition(Vector2f position) {
		this.POSITION = position;
		
		this.projectionMatrix = new Matrix4f()
				.scale(SIZE, SIZE, 0)
				.translate(new Vector3f(POSITION.x, POSITION.y, 0));
	}

	public float getSize() {
		return SIZE;
	}

	public void setSize(float size) {
		this.SIZE = size;
	}
	
}
