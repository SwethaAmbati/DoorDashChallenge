package com.doordash.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doordash.R;
import com.doordash.adapter.RestaurantAdapter;
import com.doordash.model.Restaurant;
import com.doordash.rest.ApiClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private View fragmentView;
    private static List<Restaurant> restaurantList;
    private static final String TAG = "DiscoverFragment";

    RecyclerView.LayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public List<Restaurant> getRestaurantList(){
       return  restaurantList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Discover");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView called");
        fragmentView = inflater.inflate(R.layout.fragment_discover,container,false);
        fragmentView.setTag(TAG);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        double latitude = 37.422740;
        double longitude = -122.139956;

        discoverRestaurants(latitude,longitude);

        return fragmentView;
    }

    // retrieve list of restaurants for given lat and lng
    public void discoverRestaurants(double lat, double lng) {
        Log.d(TAG, "discoverRestaurants called");
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage(getResources().getString(R.string.progress_spinner_message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show progress spinner
        progressDialog.show();
        ApiClient.getApi().getRestaurantList(lat,lng).enqueue(new Callback<List<Restaurant>>() { // getApi the top games, this is the default http call
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                restaurantList = response.body();
                Log.v(TAG, "restaurantList"+restaurantList);
                recyclerView=(RecyclerView)fragmentView.findViewById(R.id.rv_restaurants);
                //   recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(layoutManager);
                adapter = new RestaurantAdapter(getActivity(), restaurantList);
                // dismiss progress spinner
                progressDialog.dismiss();
                recyclerView.setAdapter(adapter);
            }
                @Override
                public void onFailure (Call < List < Restaurant >> call, Throwable t){

                }

            });
    }

}