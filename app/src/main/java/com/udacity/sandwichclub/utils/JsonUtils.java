package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    //TODO: What's the variable "VAL_NAME" for?
    public static final String VAL_NAME = "name";
    public static final String VAL_MAIN_NAME = "mainName";
    public static final String VAL_AKA = "alsoKnownAs";
    public static final String VAL_DESCRIPTION = "description";
    public static final String VAL_ORIGIN_PLACE = "placeOfOrigin";
    public static final String VAL_INGREDIENTS = "ingredients";
    public static final String VAL_IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {
        //TODO: Why are we declaring the Sandwich object?
        Sandwich sandwich = new Sandwich();

        try {
            //TODO: What's the rootObject, and why are we declaring it?
            JSONObject rootObject = new JSONObject(json);

            //Here creating a JSON "nameObject"
            JSONObject nameObject = rootObject.getJSONObject(VAL_NAME);

            //Here we're calling "setMainName" method from Sandwich.java class
            // on the sandwich object just created.
            sandwich.setMainName(nameObject.getString(VAL_MAIN_NAME));
            //Here we're calling "setAlsoKnownAs" method with attributes: "getJsonArrayList",
            // that had to be declared as a method bellow.
            //TODO: Why we use an Array here as opposed to String?
            sandwich.setAlsoKnownAs(getJsonArrayAsList(rootObject.getJSONArray(VAL_AKA)));

            sandwich.setPlaceOfOrigin(rootObject.getString(VAL_ORIGIN_PLACE));

            sandwich.setDescription(rootObject.getString(VAL_DESCRIPTION));

            sandwich.setPlaceOfOrigin(rootObject.getString(VAL_ORIGIN_PLACE));

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
