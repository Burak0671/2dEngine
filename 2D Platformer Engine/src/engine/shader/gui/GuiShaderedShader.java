package engine.shader.gui;

import org.joml.Matrix4f;

import engine.shader.ShaderProgram;

public class GuiShaderedShader extends ShaderProgram {

	private static final String VERTEX_FILE   = "./res/shaders/modelVS.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/modelFS.glsl";
	
	private int location_projectionMatrix;
	
	public GuiShaderedShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "uv");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = super.getUniformLocation("projection_matrix");
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
}
