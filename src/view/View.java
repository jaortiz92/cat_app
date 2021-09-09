package view;

import controller.CatService;
import model.Cat;
import model.CatFavourite;

import javax.swing.*;

public class View {

    private final CatService catService;
    private Cat cat;
    private CatFavourite catFavourite;


    public View() {
        catService = new CatService();
        starting();
    }

    public void starting() {
        int optionMenu = -1;
        String[] buttons = {"1. Look cats", "2. Look favourites","3. Exit"};
        do {
            boolean flag = true;
            // Main Menu
            String option = (String) JOptionPane.showInputDialog(null, "Cats App", "Main Menu",
                    JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

            //Validate user selected
            for (int i = 0; i < buttons.length; i++) {
                if (option.equals(buttons[i])) {
                    optionMenu = i;
                }
            }

            switch (optionMenu) {
                case 0:
                    while (flag){
                        cat = catService.showCats();
                        flag = menuLookCat();
                    }
                    break;
                case 1:
                    while (flag) {
                        catFavourite = catService.lookFavourites(new Cat().getApiKey());
                        flag = menuLookCatFavorite();
                    }
                    break;
                case 2:
                default:
                    break;
            }
        } while (optionMenu != 2);
    }


    public boolean menuLookCat() {
        boolean flag = true;
        int selection = -1;
        String menu = "Options:" +
                "\n\t1. Look other cat" +
                "\n\t2. Favorite" +
                "\n\t3. Go back";
        String[] buttons = {"Look other cat", "Favorite", "Go back"};
        String idCat = cat.getId();
        String option = (String) JOptionPane.showInputDialog(null, menu, idCat, JOptionPane.INFORMATION_MESSAGE, cat.getImage(), buttons, buttons[0]);

        for(int i = 0; i < buttons.length; i++){
            if(option.equals(buttons[i])){
                selection = i;
            }
        }

        switch (selection){
            case 0:
                break;
            case 1:
                if (catService.addCatFavorite(cat)) {
                    JOptionPane.showMessageDialog(null, "The picture was added to favourites");
                } else {
                    JOptionPane.showMessageDialog(null, "The picture was not added to favourites");
                }
                break;
            case 2:
                flag = false;
                break;
        }

        return flag;
    }


    public boolean menuLookCatFavorite() {
        boolean flag = true;
        int selection = -1;
        String menu = "Options:" +
                "\n\t1. Look other cat favorite" +
                "\n\t2. Remove favorite" +
                "\n\t3. Go back";
        String[] buttons = {"Look other cat favorite", "Remove favorite", "Go back"};
        String idCat = catFavourite.getId();
        String option = (String) JOptionPane.showInputDialog(null, menu, idCat, JOptionPane.INFORMATION_MESSAGE, catFavourite.getImage(), buttons, buttons[0]);

        for(int i = 0; i < buttons.length; i++){
            if(option.equals(buttons[i])){
                selection = i;
            }
        }

        switch (selection){
            case 0:
                break;
            case 1:
                if (catService.removeCatFavorite(catFavourite)) {
                    JOptionPane.showMessageDialog(null, "The picture was removed of favourites");
                } else {
                    JOptionPane.showMessageDialog(null, "The picture was not removed of favourites");
                }
                break;
            case 2:
                flag = false;
                break;
        }
        return flag;
    }

}
