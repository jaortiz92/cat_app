package controller;

import model.Cat;
import model.CatFavourite;
import access.CatDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CatService {
    private CatDAO catDAO;

    public CatService() {
        catDAO = new CatDAO();
    }

    public Cat showCats(){
        Cat cat = catDAO.getCat();
        Image image;
        try {
            URL url = new URL(cat.getUrl());
            image = ImageIO.read(url);
            cat.setImage(changeSize(image));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cat;
    }

    public boolean addCatFavorite(Cat cat){
        return catDAO.addFavourite(cat);
    }

    public CatFavourite lookFavourites(String key){
        ArrayList<CatFavourite> catsFavourites = catDAO.getCatsFavourites(key);
        CatFavourite catSelected = null;
        if (catsFavourites.size() > 0) {
            int min = 0;
            int max = catsFavourites.size() - 1;
            int random = (int) (Math.random() * (max - min));
            catSelected = catsFavourites.get(random);
        }

        Image image;
        try {
            URL url = new URL(catSelected.getImageX().getUrl());
            image = ImageIO.read(url);
            catSelected.setImage(changeSize(image));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return catSelected;
    }

    public ImageIcon changeSize(Image image){
        ImageIcon backgroundCat = new ImageIcon(image);

        if (backgroundCat.getIconWidth() > 800 | backgroundCat.getIconHeight() > 800){
            //resize
            Image background = backgroundCat.getImage();
            Image newBackground = background.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            backgroundCat = new ImageIcon(newBackground);
        }
        return  backgroundCat;
    }

    public boolean removeCatFavorite(CatFavourite catFavourite){
        return catDAO.removeCatFavourite(catFavourite);
    }
}
