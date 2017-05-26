package com.doordash.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doordash.database.DbHelper;
import com.doordash.R;
import com.doordash.adapter.RestaurantAdapter;
import com.doordash.model.Restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link FavoriteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "FavoriteListFragment";
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private View fragmentView;
    private List<Restaurant> favoriteRestaurantList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DbHelper dbHelper;
    Set<Integer> idHash;
    DiscoverFragment discoverFragment;


    public FavoriteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteListFragment newInstance(String param1, String param2) {
        FavoriteListFragment fragment = new FavoriteListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView called");
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_favorite_list,container,false);
        fragmentView.setTag(TAG);
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_favorite_restaurants);
        favoriteRestaurantList = new ArrayList<>();
        dbHelper = DbHelper.getInstance(getActivity().getApplicationContext());
        discoverFragment = new DiscoverFragment();
        List<Integer> favIds = new ArrayList<Integer>();
        favIds = dbHelper.getAllFavorites();
        Log.v(TAG, "favIds"+favIds);
        List<Restaurant> restaurantList = discoverFragment.getRestaurantList();
        idHash = new HashSet<>(favIds);
        Log.v(TAG, "idHash"+idHash.toString());
        getFavoriteRestaurantList(idHash,restaurantList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RestaurantAdapter(getActivity(), favoriteRestaurantList);
        recyclerView.setAdapter(adapter);
        return fragmentView;
    }

    // checking if the favIds and present in restaurantList to filter available favoriteRestaurantList
    public void getFavoriteRestaurantList(Set<Integer>idHash,List<Restaurant>restaurantList){

        Log.d(TAG, "getFavoriteRestaurantList called");
        for (Restaurant restaurant : restaurantList) {
            if (idHash.contains(restaurant.getId())) {
                favoriteRestaurantList.add(restaurant);
                Log.v(TAG, "favoriteRestaurantList"+favoriteRestaurantList.toString());
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Favorites");
    }





}
