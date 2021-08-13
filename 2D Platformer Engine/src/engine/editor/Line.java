package engine.editor;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.Camera;
import engine.rendering.LineModel;
import engine.shader.LineShader;

public class Line {

	float[] vertices = new float[] {
		-1f, 1f, 0
	};
	
	private LineModel  model = null;
	private LineShader shader = null;
	private Matrix4f projectionMatrix = null;
	
	Vector2f POSITION = new Vector2f();
	Vector2f SCALE = new Vector2f();
	Vector3f ROTATION = new Vector3f();
	
	public Line(Vector2f position, Vector2f scale, float rotationAngle, Vector3f rotation) {
		
		this.POSITION = position;
		this.SCALE = scale;
		this.ROTATION = rotation;
		
		this.model  = new LineModel(vertices);
		this.shader = new LineShader();
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x * 10, SCALE.y * 10, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(rotationAngle, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}
	
	public void render() {
		shader.start();
		shader.loadProjectionMatrix(Camera.getProjection().mul(projectionMatrix));
		model.render();
		shader.stop();
	}

	public Vector2f getPOSITION() {
		return POSITION;
	}

	public Vector2f getSCALE() {
		return SCALE;
	}

	public Vector3f getROTATION() {
		return ROTATION;
	}
}
