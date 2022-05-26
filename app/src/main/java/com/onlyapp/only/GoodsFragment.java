package com.onlyapp.only;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class GoodsFragment extends Fragment {
    View view;
    ImageButton idBack;
    TextView textViewNameSubcategory, textViewNumber;

    ArrayList<String> namesArrayList = new ArrayList<>();
    ArrayList<String> descriptionsShortArrayList = new ArrayList<>();
    ArrayList<String> pricesArrayList = new ArrayList<>();
    ArrayList<String> favoritesArrayList = new ArrayList<>();
    ArrayList<String> imagesArrayList = new ArrayList<>();
    String[] namesArray = {"Product1", "Product2"};
    String[] descriptionsShortArray;
    String[] pricesArray;
    String[] favoritesArray;
    String[] imagesArray;

    static int position;

    static JSONArray jsonArrayGoods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goods, container, false);

        idBack = (ImageButton) view.findViewById(R.id.idBack);
        textViewNameSubcategory = (TextView) view.findViewById(R.id.textViewNameGoods);
        textViewNumber = (TextView) view.findViewById(R.id.textViewNumber);

        try {
            JSONObject subcategoryData = CategoryFragment.jsonArraySubcategories.getJSONObject(position);
            textViewNameSubcategory.setText(subcategoryData.getString("name"));

            jsonArrayGoods = subcategoryData.getJSONArray("goods");
            for (int i = 0; i < jsonArrayGoods.length(); i++) {
                JSONObject goodsData = jsonArrayGoods.getJSONObject(i);
                namesArrayList.add(goodsData.getString("name"));
                descriptionsShortArrayList.add(goodsData.getString("descriptionShort"));
                pricesArrayList.add(goodsData.getString("price"));
                JSONArray imagesArrayData = goodsData.getJSONArray("images");
                JSONObject imageData = imagesArrayData.getJSONObject(0);
                imagesArrayList.add(imageData.getString("image"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        namesArray = namesArrayList.toArray(new String[0]);
        descriptionsShortArray = descriptionsShortArrayList.toArray(new String[0]);
        pricesArray = pricesArrayList.toArray(new String[0]);
        imagesArray = imagesArrayList.toArray(new String[0]);

        textViewNumber.setText(String.valueOf(namesArray.length));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_goods);
        RecyclerViewGoodsAdapter adapter = new RecyclerViewGoodsAdapter(namesArray, descriptionsShortArray, pricesArray, imagesArray);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setListener(new RecyclerViewGoodsAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProductPageFragment()).commit();
                ProductPageFragment.position = position;
            }
        });

        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoryFragment()).commit();
            }
        });

        return view;
    }
}