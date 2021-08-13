package engine.gameObjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.Camera;
import engine.rendering.TexturedModel;
import engine.rendering.Texture;
import engine.shader.ModelShader;

public class Box implements GameObject {

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
		
	float[] textures = new float[] {
		0,0,
		1,0,
		1,1,
		0,1
	};
		
	private TexturedModel model = null;
	private Texture textureModel = null;
	private ModelShader shader = null;
	private Matrix4f projectionMatrix = null;
	
	Vector2f POSITION = new Vector2f();
	Vector2f SCALE = new Vector2f();
	Vector3f ROTATION = new Vector3f();
	
	public Box(Vector2f position, Vector2f scale, float rotationAngle, Vector3f rotation, String texture) {
		
		this.POSITION = position;
		this.SCALE = scale;
		this.ROTATION = rotation;
		
		this.model = new TexturedModel(vertices, indices, textures);
		this.textureModel = new Texture(texture);
		this.shader = new ModelShader();
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(rotationAngle, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));	
		
	}
	
	@Override
	public void render() {
		shader.start();
		textureModel.bind();
		shader.loadProjectionMatrix(Camera.getProjection().mul(projectionMatrix));
		model.render();
		shader.stop();
	}

	@Override
	public void setPosition(Vector2f newPosition) {
		POSITION = newPosition;
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(0, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public void setScale(Vector2f newScale) {
		SCALE = newScale;
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(0, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public void setRotation(float angle, Vector3f newRotation) {
		ROTATION = newRotation;
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(angle, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public void increasePosition(Vector2f position) {
		POSITION.add(position);
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(0, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public void increaseScale(Vector2f scale) {
		SCALE.add(scale);
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(0, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public void increaseRotation(float angle, Vector3f rotation) {
		ROTATION.add(rotation);
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(angle, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public Vector2f getPosition() {
		return POSITION;
	}

	@Override
	public Vector2f getScale() {
		return SCALE;
	}

	@Override
	public void setPosition(Vec2 pos) {
		POSITION.x = pos.x;
		POSITION.y = pos.y;
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(0, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public void setRotation(double angle, Vec3 rot) {
		ROTATION.x = rot.x;
		ROTATION.y = rot.y;
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate((float)angle, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));
	}

	@Override
	public Vector3f getRotation() {
		return ROTATION;
	}
	
}
