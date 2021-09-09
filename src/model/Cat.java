package model;

import javax.swing.*;

public class Cat {
    private String id;
    private String url;
    private final String apiKey = "06eadb5f-9094-4bc4-b2ae-9a8ec241c4ab";
    private int width;
    private int height;
    private ImageIcon image;

    public Cat(String url){
        this.url = url;
    }

    public Cat(){}

    public Cat(String id, String url, int width, int height){
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
