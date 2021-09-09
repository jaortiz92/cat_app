package access;

import Model.Cat;


import Model.CatFavourite;
import Model.ImageX;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;


public class CatDAO {
    public Cat getCat(){
        Response response;
        Cat cat = null;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        try {
            response = client.newCall(request).execute();
            String responseJson = response.body().string();
            responseJson = responseJson.substring(1, responseJson.length() -1);

            JSONObject jsonObject = new JSONObject(responseJson);

            cat = new Cat(
                    jsonObject.getString("id"),
                    jsonObject.getString("url"),
                    jsonObject.getInt("width"),
                    jsonObject.getInt("height")
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cat;
    }

    public boolean addFavourite(Cat cat){
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"image_id\":\""+ cat.getId() +"\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", cat.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<CatFavourite> getCatsFavourites(String key){
        ArrayList<CatFavourite> catsFavourites = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .method("GET", null)
                .addHeader("x-api-key", key)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseJson = response.body().string();
            JSONArray jsonArray = new JSONArray(responseJson);

            for (Object jsonObject: jsonArray){
                JSONObject json = (JSONObject) jsonObject;
                JSONObject image = json.getJSONObject("image");
                ImageX imageX = new ImageX(
                        image.getString("id"),
                        image.getString("url")
                );

                catsFavourites.add(new CatFavourite(
                        String.valueOf(json.getInt("id")),
                        json.getString("image_id"),
                        imageX
                    )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return catsFavourites;
    }

    public boolean removeCatFavourite(CatFavourite catFavourite){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites/" + catFavourite.getId())
                .method("DELETE", body)
                .addHeader("x-api-key", catFavourite.getApiKey())
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
