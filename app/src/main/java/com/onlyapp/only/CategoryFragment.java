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

public class CategoryFragment extends Fragment {
    View view;
    ArrayList<String> namesArrayList = new ArrayList<>();
    ArrayList<String> imagesArrayList = new ArrayList<>();
    String[] namesArray;
    String[] imagesArray;
    ImageButton idBack;
    TextView textViewNameCategory;
    static int position;
    static int himOrHer;
    static JSONArray jsonArraySubcategories;
    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        if(getActivity()!=null){
            ma = (MainActivity)getActivity();
        }

        idBack = (ImageButton) view.findViewById(R.id.idBack);
        textViewNameCategory = (TextView) view.findViewById(R.id.textViewNameCategory);

        try {
            JSONObject categoryData = null;
            if(himOrHer == 0){
                categoryData = ForHimFragment.jsonArrayCategories.getJSONObject(position);
            }else if(himOrHer == 1){
                categoryData = ForHerFragment.jsonArrayCategories.getJSONObject(position);
            }

            jsonArraySubcategories = categoryData.getJSONArray("subcategories");
            for (int i=0;i< jsonArraySubcategories.length();i++){
                JSONObject subcategoryData = jsonArraySubcategories.getJSONObject(i);
                namesArrayList.add(subcategoryData.getString("name"));
                imagesArrayList.add(subcategoryData.getString("image"));
            }
            textViewNameCategory.setText(categoryData.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        namesArray = namesArrayList.toArray(new String[0]);
        imagesArray = imagesArrayList.toArray(new String[0]);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_him);
        RecyclerViewHimAndHerAdapter adapter = new RecyclerViewHimAndHerAdapter(namesArray, imagesArray);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setListener(new RecyclerViewHimAndHerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GoodsFragment()).commit();
                GoodsFragment.position = position;
            }
        });

        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CatalogFragment()).commit();
                ma.selectSection = himOrHer;
            }
        });

/*        // Получим идентификатор ListView
        ListView listView = view.findViewById(R.id.listViewCategory);
        //устанавливаем массив в ListView
        listView.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles) {
                    @Override
                    public View getView(int position, View convertView,
                                        ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        //textView.setTypeface(type);
                        textView.setTextSize(15);

//                        ViewGroup viewGroup = (ViewGroup) super.getView(position, convertView, parent);
//                        TextView textView = viewGroup.findViewById(android.R.id.text1);
//                        textView.setTypeface(type);
                        return view;
                    }
                });
        listView.setTextFilterEnabled(true);

        //Обрабатываем щелчки на элементах ListView:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Intent intent = new Intent();
//                intent.setClass(CategoryActivity.this, PageThreeActivity.class);
//                intent.putExtra("title", position);
//
//                //запускаем вторую активность
//                startActivity(intent);
            }
        });

*/

        return view;
    }
}