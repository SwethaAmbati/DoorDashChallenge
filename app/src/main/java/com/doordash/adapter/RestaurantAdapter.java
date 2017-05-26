package com.doordash.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doordash.R;
import com.doordash.model.Restaurant;
import com.doordash.RestaurantDetailListActivity;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ListViewHolder>  {
    private Context context;
    private List<Restaurant> restaurantList ;
    private static final String TAG = "RestaurantAdapter";

    public RestaurantAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ListViewHolder(convertView);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    @Override
    public void onBindViewHolder( ListViewHolder holder, final int position) {

        holder.tv_restaurant_name.setText(restaurantList.get(position).getName());
        holder.tv_restaurant_status.setText(restaurantList.get(position).getStatus());
        holder.tv_restaurant_desc.setText(restaurantList.get(position).getDescription());
        Glide.with(context).load(restaurantList.get(position).getCover_img_url()).into(holder.iv_restaurant);
        holder.restaurantItemLinearLayout.setTag(position);
        holder.restaurantItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int id = restaurantList.get((Integer) v.getTag()).getId();
                Log.v(TAG, "id=" + id);
                Intent i = new Intent(context,RestaurantDetailListActivity.class);
                context.startActivity(i);
                String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("ID", id);
                editor.commit();
            }
        });
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_restaurant_name;
        TextView tv_restaurant_desc;
        TextView tv_restaurant_status;
        LinearLayout restaurantItemLinearLayout;
        ImageView iv_restaurant;


        public ListViewHolder(View itemView) {
            super(itemView);
            restaurantItemLinearLayout = (LinearLayout) itemView.findViewById(R.id.restaurantItemLinearLayout);
            tv_restaurant_name = (TextView) itemView.findViewById(R.id.tv_restaurant_name);
            tv_restaurant_desc = (TextView) itemView.findViewById(R.id.tv_restaurant_desc);
            tv_restaurant_status = (TextView) itemView.findViewById(R.id.tv_restaurant_status);
            iv_restaurant = (ImageView) itemView.findViewById(R.id.iv_restuarant);

        }
    }


}

