#version 140

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform mat4 u_projTrans;
uniform int white;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);

    if (white != 0) {
        gl_FragColor = vec4(color.rgb * 0.5 + 0.8, color.a);
    } else {
        gl_FragColor = color * v_color;
    }
}