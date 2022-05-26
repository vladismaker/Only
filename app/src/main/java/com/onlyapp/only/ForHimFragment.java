package com.onlyapp.only;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ForHimFragment extends Fragment {
    View view;
    ArrayList<String> namesArrayList = new ArrayList<>();
    ArrayList<String> imagesArrayList = new ArrayList<>();
    String[] namesArray;
    String[] imagesArray;
    MainActivity ma;
    static JSONArray jsonArrayCategories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_for_him, container, false);

        if(getActivity()!=null){
            ma = (MainActivity)getActivity();
        }
        ma.selectSection =0;

        //Данные из JSON для каталога
        try {
            JSONArray jsonArraySection = ma.jsonObject.getJSONArray("section");
            JSONObject sectionData=jsonArraySection.getJSONObject(0);
            jsonArrayCategories = sectionData.getJSONArray("categories");
            for (int i=0;i< jsonArrayCategories.length();i++){
                JSONObject categoryData=jsonArrayCategories.getJSONObject(i);
                namesArrayList.add(categoryData.getString("name"));
                imagesArrayList.add(categoryData.getString("image"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        namesArray = namesArrayList.toArray(new String[0]);
        imagesArray = imagesArrayList.toArray(new String[0]);

        //String[] names = {"Одежда", "Обувь", "Сумки", "Аксессуары", "Часы"};
        //int[] images = {R.drawable.section_men, R.drawable.obuv_men, R.drawable.bag_men, R.drawable.acsessuars_men, R.drawable.watch_men};

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_him);
        RecyclerViewHimAndHerAdapter adapter = new RecyclerViewHimAndHerAdapter(namesArray, imagesArray);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setListener(new RecyclerViewHimAndHerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoryFragment()).commit();
                CategoryFragment.position = position;
                CategoryFragment.himOrHer = 0;
            }
        });

        return view;
    }
}