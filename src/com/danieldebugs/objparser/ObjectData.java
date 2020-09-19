package com.danieldebugs.objparser;

public class ObjectData {
    private String name;
    private Vector3f[] positions;
    private Vector2f[] textureCoordinates;
    private Vector3f[] normals;

    public ObjectData(String name, Vector3f[] positions, Vector2f[] textureCoordinates, Vector3f[] normals) {
        name = null;
        this.positions = positions;
        this.textureCoordinates = textureCoordinates;
        this.normals = normals;
    }

    public String getName() {
        return name;
    }

    public Vector3f[] getPositions() {
        return positions;
    }

    public Vector2f[] getTextureCoordinates() {
        return textureCoordinates;
    }

    public Vector3f[] getNormals() {
        return normals;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Name ").append(name);
        string.append("\nPositions\n");
        for (Vector3f v : positions) {
            string.append(v.getX()).append(" ");
            string.append(v.getY()).append(" ");
            string.append(v.getZ()).append("\n");;
        }
        string.append("\nTexture Coordinates\n");
        for (Vector2f v : textureCoordinates) {
            string.append(v.getX()).append(" ");
            string.append(v.getY()).append("\n");;
        }
        string.append("\nNormals\n");
        for (Vector3f v : normals) {
            string.append(v.getX()).append(" ");
            string.append(v.getY()).append(" ");
            string.append(v.getZ()).append("\n");
        }
        return string.toString();
    }
}
