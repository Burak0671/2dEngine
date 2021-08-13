#version 330 core

in vec3 position;
in vec2 uv;

out vec2 texture_Coords;

uniform mat4 projection_matrix;

void main(void) 
{

	texture_Coords = uv;

	gl_Position = projection_matrix * vec4(position, 1.0);

}