package com.doordash;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doordash.database.DbHelper;
import com.doordash.model.Restaurant;
import com.doordash.rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailListActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView tv_restaurant_name;
    TextView tv_restaurant_desc;
    TextView tv_restaurant_status;
    TextView tv_restaurant_food_deliveryfee;
    ImageView iv_restuarant;
    DbHelper dbHelper;
    int str_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail_list);

        iv_restuarant = (ImageView) findViewById(R.id.iv_restuarant);
        tv_restaurant_name = (TextView) findViewById(R.id.tv_restaurant_name);
        tv_restaurant_desc = (TextView) findViewById(R.id.tv_restaurant_desc);
        tv_restaurant_status = (TextView) findViewById(R.id.tv_restaurant_status);
        tv_restaurant_food_deliveryfee = (TextView) findViewById(R.id.tv_restaurant_food_deliveryfee);
        Button btn_add = (Button) findViewById(R.id.btn_add);
        Button btn_remove = (Button) findViewById(R.id.btn_remove);

        dbHelper = DbHelper.getInstance(getApplicationContext());
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int restoredText = prefs.getInt("ID", 1);
        if (restoredText != 0) {
            str_id = prefs.getInt("ID", 1);

        }
        // retrieve details of restaurant
        getRestaurantDetail(str_id);

        // perform click to add to favorites
        btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                dbHelper.addToFavorites(str_id);
            }
        });

        // perform click to remove from favorite
        btn_remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                dbHelper.removeFromFavorite(str_id);
            }
        });
    }

    public void getRestaurantDetail(int id) {
        Log.d(TAG, "getRestaurantDetail called");
        ApiClient.getApi().getRestaurantDetails(id).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Restaurant restaurant = new Restaurant();
                restaurant = response.body();
                Log.v(TAG, "restaurant detail response"+restaurant);
                tv_restaurant_name.setText(restaurant.getName());
                tv_restaurant_desc.setText(restaurant.getDescription());
                tv_restaurant_status.setText(restaurant.getStatus());
                Glide.with(RestaurantDetailListActivity.this).load(restaurant.getCover_img_url()).into(iv_restuarant);
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });
    }


}
