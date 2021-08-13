package engine.rendering;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

public class Texture {
	
	private int id;
	private int WIDTH;
	private int HEIGHT;

	public Texture(String fileName) {
		
		IntBuffer width  = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);
		IntBuffer comp   = BufferUtils.createIntBuffer(1);
		
		ByteBuffer data = stbi_load("./res/sprites/" + fileName, width, height, comp, 4);
		
		id = glGenTextures();
		this.WIDTH  = width.get();
		this.HEIGHT = height.get();
		
		// NOT: Texture parametlerden önce texture bind etmezsen, texture'lar siyah gözükür!!
		glBindTexture(GL_TEXTURE_2D, id);
		
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.WIDTH, this.HEIGHT, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		stbi_image_free(data);
		
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public int getId() {
		return id;
	}
	
}
