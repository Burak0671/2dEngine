package engine.rendering;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import engine.misc.Buffers;

public class LineModel {

	private int drawCount;
	private int vaoID;
	
	private int vertexVBO;
	
	public LineModel(float[] vertices) {
		
		drawCount = vertices.length;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		vertexVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
		glBufferData(GL_ARRAY_BUFFER, Buffers.createBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
	}

	public void render() {
		
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
		
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glDrawArrays(GL_LINE_STRIP, 0, drawCount);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
		
	}
	
}
