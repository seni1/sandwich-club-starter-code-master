package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {


    public static final String VAL_NAME = "name";
    public static final String VAL_MAIN_NAME = "mainName";
    public static final String VAL_AKA = "alsoKnownAs";
    public static final String VAL_DESCRIPTION = "description";
    public static final String VAL_ORIGIN_PLACE = "placeOfOrigin";
    public static final String VAL_INGREDIENTS = "ingredients";
    public static final String VAL_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {

            JSONObject rootObject = new JSONObject(json);

            JSONObject nameObject = rootObject.getJSONObject(VAL_NAME);

            sandwich.setMainName(nameObject.getString(VAL_MAIN_NAME));

            sandwich.setAlsoKnownAs(getJsonArrayAsList(nameObject.getJSONArray(VAL_AKA)));

            sandwich.setPlaceOfOrigin(rootObject.getString(VAL_ORIGIN_PLACE));

            sandwich.setDescription(rootObject.getString(VAL_DESCRIPTION));

            sandwich.setImage(rootObject.getString(VAL_IMAGE));

            sandwich.setIngredients(getJsonArrayAsList(rootObject.getJSONArray(VAL_INGREDIENTS)));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;

    }

    public static ArrayList<String> getJsonArrayAsList(JSONArray array) {
        ArrayList<String > list = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
