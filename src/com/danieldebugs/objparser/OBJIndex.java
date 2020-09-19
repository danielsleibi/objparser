package com.danieldebugs.objparser;

public class OBJIndex {
    private int positionIndex, textureIndex, normalIndex;

    public OBJIndex(int positionIndex, int textureIndex, int normalIndex) {
        this.positionIndex = positionIndex;
        this.textureIndex = textureIndex;
        this.normalIndex = normalIndex;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public int getTextureIndex() {
        return textureIndex;
    }

    public int getNormalIndex() {
        return normalIndex;
    }
}
