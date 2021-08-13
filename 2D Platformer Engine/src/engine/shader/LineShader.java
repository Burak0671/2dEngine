package engine.shader;

import org.joml.Matrix4f;

public class LineShader extends ShaderProgram {

	private static final String VERTEX_FILE   = "./res/shaders/debugDrawVS.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/debugDrawFS.glsl";
	
	private int location_projectionMatrix;
	
	public LineShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	@Override
	protected void getAllUniformLocations() {
		location_projectionMatrix = super.getUniformLocation("projection_matrix");
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
}
