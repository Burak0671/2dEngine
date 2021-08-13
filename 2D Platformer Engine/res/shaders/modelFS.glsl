#version 330 core

in vec2 texture_Coords;

out vec4 out_Color;

uniform sampler2D sampler;

void main(void) 
{

	out_Color = texture(sampler, texture_Coords);

}