package engine.gui;

import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.Camera;
import engine.Input;
import engine.rendering.Texture;
import engine.rendering.TexturedModel;
import engine.shader.gui.GuiTexturedShader;
import engine.toolBox.Maths;

public class Button {

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
	private GuiTexturedShader shader = null;
	private Matrix4f projectionMatrix = null;
	
	Vector2f POSITION = new Vector2f();
	Vector2f SCALE = new Vector2f();
	Vector3f ROTATION = new Vector3f();

    private boolean isHidden = false;
    //private boolean isHovering = false;
	
	public Button(Vector2f position, Vector2f scale, float rotationAngle, Vector3f rotation, String texture) {
		
		this.POSITION = position;
		this.SCALE = scale;
		this.ROTATION = rotation;
		
		this.model = new TexturedModel(vertices, indices, textures);
		this.textureModel = new Texture(texture);
		this.shader = new GuiTexturedShader();
		
		this.projectionMatrix = new Matrix4f()
				.scale(new Vector3f(SCALE.x, SCALE.y, 0))
				.translate(new Vector3f(POSITION.x, POSITION.y, 0))
				.rotate(rotationAngle, new Vector3f(ROTATION.x, ROTATION.y, ROTATION.z));	
		
	}
	
	public void checkHover() {
		if(!isHidden) {
			
			Vector2f mouseCoordinates = Maths.getNormalisedDeviceCoordinates();
			
			if (POSITION.y + SCALE.y > -mouseCoordinates.y && POSITION.y - SCALE.y < -mouseCoordinates.y &&
					POSITION.x + SCALE.x > mouseCoordinates.x && POSITION.x - SCALE.x < mouseCoordinates.x) 
			{
                
				System.out.println("Hover!");
				
            	if(Input.isMouseButtonDown(0)) {
            		System.out.println("Click!");
            	}
				
            }
			
		}
	}
	
	public void render() {
		shader.start();
		textureModel.bind();
		shader.loadProjectionMatrix(Camera.getProjection().mul(projectionMatrix));
		model.render();
		shader.stop();
	}
	
	public void show(List<TexturedModel> buttonTextureModel) {
		buttonTextureModel.add(model);
		isHidden = false;
	}
	
	public void hide(List<TexturedModel> buttonTextureModel) {
		buttonTextureModel.remove(model);
		isHidden = true;
	}
	
	
}
