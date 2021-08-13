#version 330 core

in vec3 fragmentPosition;
in vec3 rgb;
in float time;

out vec4 out_Color;

//float noise(vec3 co){
//	return fract(sin(dot(co.xy ,vec2(12.9898,78.233))) * 43758.5453);
//}

void main(void) 
{

	float distance = 1.2 - (0.1 * sin(time / 5) / 5 + 0.1 / length(fragmentPosition) * 0.15);
	out_Color = vec4(rgb.xyz, 1 * (1 - distance));
	
}