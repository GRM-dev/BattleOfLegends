package pl.bol.engine.render;

import pl.bol.engine.core.*;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Shader {
	private int program;
	private HashMap<String, Integer> uniforms;
    private RenderingEngine renderingEngine;

	public Shader() {
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();

		if (program == 0) {
			System.err.println("Tworzenie shader'a niepowiedzione, ERROR");
			System.exit(1);
		}
	}

	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}

	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);
	}

	private void addProgram(String text, int type) {
		int shader = glCreateShader(type);

		if (shader == 0) {
			System.err.println("Tworzenie shader'a niepowiedzione, ERROR");
			System.exit(1);
		}

		glShaderSource(shader, text);
		glCompileShader(shader);

		if (glGetShader(shader, GL_COMPILE_STATUS) == 0) {
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}

		glAttachShader(program, shader);
	}

	public void compileShader() {
		glLinkProgram(program);

		if (glGetProgram(program, GL_LINK_STATUS) == 0) {
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}

		glValidateProgram(program);
		if (glGetProgram(program, GL_VALIDATE_STATUS) == 0) {
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
	}

	public void bind() {
		glUseProgram(program);
	}

	public void addUniform(String uniform) {
		int uniformLocation = glGetUniformLocation(program, uniform);

		// if(uniformLocation == 0xFFFFFFFF) {
		// System.err.println(uniform + "ERROR");
		// new Exception().printStackTrace();
		// System.exit(1);
		// }

		uniforms.put(uniform, uniformLocation);
	}

	public void setUniformi(String uniformName, int value) {
		glUniform1i(uniforms.get(uniformName), value);
	}

	public void setUniformf(String uniformName, float value) {
		glUniform1f(uniforms.get(uniformName), value);
	}

	public void setUniform(String uniformName, Vector3f value) {
		glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
	}

	public void setUniform(String uniformName, Matrix4f value) {
		glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
	}

	public void updateUniforms(Transform transform, Material material) {

	}

    public static String loadShader(String fileName) {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;

        try
       	{
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
        	String line;

            while((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
        	}

            shaderReader.close();
        } catch(Exception e) {
            e.printStackTrace();
        	System.exit(1);
        }

        return shaderSource.toString();
    }

    public void setRenderingEngine(RenderingEngine renderingEngine) {
        this.renderingEngine = renderingEngine;
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }

    public void setAttribLocation(String attributeName, int location) {
        glBindAttribLocation(program, location, attributeName);
    }
}