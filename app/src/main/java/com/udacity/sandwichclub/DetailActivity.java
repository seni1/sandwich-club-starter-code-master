package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //TODO: Why declare as public and not private?
    public ImageView sandwichIv;
    public TextView descriptionTv;
    public TextView originTv;
    public TextView alsoKnownAs;
    public TextView ingredientsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       sandwichIv = findViewById(R.id.image_iv);
       descriptionTv = findViewById(R.id.description_tv);
       originTv = findViewById(R.id.origin_tv);
       alsoKnownAs = findViewById(R.id.also_known_tv);
       ingredientsTv = findViewById(R.id.ingredients_tv);



        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        //TODO: Why is this method here, and not in the "populateUI" method?
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(sandwichIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //TODO: Why only 2 attributes: description and origin get populated via this method?
    private void populateUI(Sandwich sandwich) {
        descriptionTv.setText(sandwich.getDescription());
        originTv.setText(sandwich.getPlaceOfOrigin());
        //TODO:  Don't understand this "for" loops: I see that Arrays get set through loops, while Strings through setText - WHY!

        for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i ++) {
            alsoKnownAs.append(sandwich.getAlsoKnownAs().get(i));
        }

        for (int i = 0; i < sandwich.getIngredients().size(); i ++) {
            ingredientsTv.append(sandwich.getIngredients().get(i) + "\n");
        }

    }
}
