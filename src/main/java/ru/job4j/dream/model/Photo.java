package ru.job4j.dream.model;

import java.sql.Blob;

public class Photo {
    private int id;
    private byte[] image;

    public Photo(byte[] image) {
        this.image = image;
    }

    public Photo(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
