#version 330 core

in vec3 position;

out vec3 fragmentPosition;
out vec3 rgb;
out float time;

uniform mat4 projection_matrix;
uniform vec3 rgbColor;
uniform float pass_time;

void main(void) 
{

	fragmentPosition = position;
	rgb = rgbColor;
	time = pass_time;

	gl_Position = projection_matrix * vec4(position, 1.0);
	
	float offset = (gl_Position.x + (gl_Position.y * 0.2)) * 0.5;
	gl_Position.y += sin(offset + time) * 0.1;
	
	
}