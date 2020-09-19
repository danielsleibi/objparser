package com.danieldebugs.objparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class OBJLoader {
    private ObjectData[] data;

    public boolean load(String content) {


        ArrayList<ObjectData> data = new ArrayList<>();
        String name = null;
        ArrayList<Vector3f> positions = new ArrayList<>();
        ArrayList<Vector2f> textureCoordinates = new ArrayList<>();
        ArrayList<Vector3f> normals = new ArrayList<>();
        ArrayList<OBJIndex> indices = new ArrayList<>();
        boolean firstObject = true;
        BufferedReader br = new BufferedReader(new StringReader(content));

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String[] args = line.split(" ");
                switch (args[0]) {
                    case "o" -> {
                        if (firstObject) {
                            name = processName(args);
                            firstObject = false;
                            continue;
                        }
                        processObject(data, name, positions, textureCoordinates, normals);
                        name = processName(args);
                    }
                    case "v" -> processPosition(args, positions);
                    case "vt" -> processTextureCoordinate(args, textureCoordinates);
                    case "vn" -> processNormal(args, normals);
                    case "f" -> processFace(args, indices);
                }

            }
            processObject(data, name, positions, textureCoordinates, normals);
            this.data = new ObjectData[data.size()];
            for (int i = 0; i < this.data.length; i++) {
                this.data[i] = data.get(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void processObject(ArrayList<ObjectData> objects, String name, ArrayList<Vector3f> positions, ArrayList<Vector2f> textureCoordinates, ArrayList<Vector3f> normals) {
        ObjectData data = new ObjectData(name, toArrayV3F(positions), toArrayV2F(textureCoordinates), toArrayV3F(normals));
        objects.add(data);
        positions.clear();
        textureCoordinates.clear();
        normals.clear();
    }

    private void processPosition(String[] args, ArrayList<Vector3f> positions) {
        float x = Float.parseFloat(args[1]);
        float y = Float.parseFloat(args[2]);
        float z = Float.parseFloat(args[3]);
        positions.add(new Vector3f(x, y, z));
    }

    private void processTextureCoordinate(String[] args, ArrayList<Vector2f> textureCoordinates) {
        float x = Float.parseFloat(args[1]);
        float y = Float.parseFloat(args[2]);
        textureCoordinates.add(new Vector2f(x, y));
    }

    private void processNormal(String[] args, ArrayList<Vector3f> normals) {
        float x = Float.parseFloat(args[1]);
        float y = Float.parseFloat(args[2]);
        float z = Float.parseFloat(args[3]);
        normals.add(new Vector3f(x, y, z));
    }

    private void processFace(String[] args, ArrayList<OBJIndex> indices) {

        for (int i = 0; i < 3; i++) {
            String[] vData = args[i + 1].split("/");
            int x = Integer.parseInt(vData[0]);
            int y = !vData[1].equals("") ? Integer.parseInt(vData[1]) : -1;
            int z = !vData[1].equals("") ? Integer.parseInt(vData[2]) : -1;
            indices.add(new OBJIndex(x, y, z));

        }
    }

    private String processName(String[] args) {
        String name = "";
        for (int i = 1; i < args.length; i++) {
            name += args[i];
        }
        return name;
    }

    private Vector3f[] toArrayV3F(ArrayList<Vector3f> list) {
        Vector3f[] array = new Vector3f[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private Vector2f[] toArrayV2F(ArrayList<Vector2f> list) {
        Vector2f[] array = new Vector2f[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }


    private void add() {

    }


    public ObjectData getObject() {
        return data[0];
    }

    public ObjectData[] getObjects() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (ObjectData obj : data) {
            string.append(obj.toString());
        }

        return string.toString();
    }

}
