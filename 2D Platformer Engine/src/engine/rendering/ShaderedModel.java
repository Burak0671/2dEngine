package engine.rendering;

import static org.lwjgl.opengl.GL30.*;

import engine.misc.Buffers;

public class ShaderedModel {

	private int drawCount;
	private int vaoID;
	
	private int vertexVBO;
	private int indicesVBO;
	
	public ShaderedModel(float[] vertices, int[] indices) {
		
		drawCount = indices.length;
		
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		vertexVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
		glBufferData(GL_ARRAY_BUFFER, Buffers.createBuffer(vertices), GL_STATIC_DRAW);
		
		indicesVBO = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesVBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Buffers.createBuffer(indices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
	}
	
	public void render() {
		
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
				
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesVBO);
		glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		
	}

	public int getVaoID() {
		return vaoID;
	}
	
}
