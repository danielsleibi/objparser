import com.danieldebugs.objparser.OBJLoader;
import org.lwjgl.opengl.GL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {


        if (!glfwInit())
            throw new RuntimeException("Failed to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(500, 500, "Test OBJ Parser", 0, 0);
        if (window == 0)

            throw new IllegalStateException("Failed to create window");
        String RES = "/media/danielsleibi/Acer/Intellij Projects/Sunny/res/meshes/";
        File objFile = new File(RES + "Cube.obj");
        StringBuilder obj = new StringBuilder();
        Scanner sc = new Scanner(objFile);
        while (sc.hasNextLine()) {
            obj.append(sc.nextLine());
            obj.append("\n");
        }

        System.out.println(obj);4333333333333333334444444444642   
        OBJLoader loader = new OBJLoader();
        loader.load(obj.toString());
        System.out.println(loader.toString());


        glfwSwapInterval(1);
        glfwShowWindow(window);
        glfwMakeContextCurrent(window);
        // Rendering utils
        GL.createCapabilities();

        int posVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, posVBO);
        glBufferData(GL_ARRAY_BUFFER, loader.getObject().getPositions().length * Float.BYTES, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);

        int texturesVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, texturesVBO);
        glBufferData(GL_ARRAY_BUFFER, loader.getObject().getTextureCoordinates().length * Float.BYTES, GL_STATIC_DRAW);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 2 * Float.BYTES, 0);

        int normalsVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, normalsVBO);
        glBufferData(GL_ARRAY_BUFFER, loader.getObject().getNormals().length * Float.BYTES, GL_STATIC_DRAW);

        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);


        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


            glDrawElements(GL_TRIANGLES, 0, GL_UNSIGNED_INT, 0);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }


    }
}
