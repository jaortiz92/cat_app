package Model;

import javax.swing.*;

public class CatFavourite {
    private String id;
    private String image_id;
    private ImageX imageX;
    private ImageIcon image;

    public CatFavourite(String id, String image_id, ImageX imageX) {
        this.id = id;
        this.image_id = image_id;
        this.imageX = imageX;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public ImageX getImageX() {
        return imageX;
    }

    public void setImageX(ImageX imageX) {
        this.imageX = imageX;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
