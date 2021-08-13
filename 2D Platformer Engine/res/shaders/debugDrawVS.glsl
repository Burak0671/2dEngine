#version 330 core

in vec3 position;

uniform mat4 projection_matrix;

void main(void) 
{
	gl_Position = projection_matrix * vec4(position, 1.0);
}