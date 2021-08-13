package engine.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class LightShader extends ShaderProgram{

	private static final String VERTEX_FILE   = "./res/shaders/lightVS.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/lightFS.glsl";
	
	private int location_projectionMatrix;
	private int location_rgb;
	private int location_time;
	
	public LightShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = super.getUniformLocation("projection_matrix");
		location_rgb = super.getUniformLocation("rgbColor");
		location_time = super.getUniformLocation("pass_time");
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void setLightColour(Vector3f colour) {
		super.loadVector(location_rgb, colour);
	}
	
	public void time(float time) {
		super.loadFloat(location_time, time);
	}
	
}
