package com.onlyapp.only;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectSizeFragment extends Fragment {
    View view;
    ImageButton buttonClose;
    FrameLayout buttonXS;
    int position;

    TextView textViewXS, textViewS, textViewM, textViewL, textViewXL, textViewXXL;
    TextView[] textViewsArray = {textViewXS, textViewS, textViewM, textViewL, textViewXL, textViewXXL};
    int[] textViewNumberResource = {R.id.textViewNumberXS, R.id.textViewNumberS, R.id.textViewNumberM, R.id.textViewNumberL, R.id.textViewNumberXL, R.id.textViewNumberXXL};
    FrameLayout frameXS, frameS, frameM, frameL, frameXL, frameXXL;
    FrameLayout[] frameSizeArray = {frameXS, frameS, frameM, frameL, frameXL, frameXXL};
    int[] frameSizeResourceArray = {R.id.buttonXS, R.id.buttonS, R.id.buttonM, R.id.buttonL, R.id.buttonXL, R.id.buttonXXL};

    ArrayList<String> namesSizeArrayList = new ArrayList<>();
    ArrayList<String> numberArrayList = new ArrayList<>();
    String[] namesSizeArray;
    String[] numberArray;
    String[] sizes = {"XS", "S", "M", "L", "XL", "XXL"};
    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select_size, container, false);

        init();

        if(getActivity()!=null){
            ma = (MainActivity)getActivity();
        }

        position = ProductPageFragment.position;
        try {
            JSONArray jsonArraySizes = ProductPageFragment.goodsData.getJSONArray("sizes");
            for (int i = 0; i < jsonArraySizes.length(); i++) {
                JSONObject sizesData = jsonArraySizes.getJSONObject(i);
                namesSizeArrayList.add(sizesData.getString("name"));
                numberArrayList.add(sizesData.getString("number"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        namesSizeArray = namesSizeArrayList.toArray(new String[0]);
        numberArray = numberArrayList.toArray(new String[0]);

        for (int i = 0; i < numberArray.length; i++) {
            if(numberArray[i].equals("0")){
                frameSizeArray[i].setVisibility(View.GONE);
            } else if(numberArray[i].equals("1")){
                textViewsArray[i].setVisibility(View.VISIBLE);
            }
        }

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductPageFragment.backSelectSize.setVisibility(View.GONE);
                getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
            }
        });

        frameSizeArray[0].setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //
                //getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
                //

/*
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("first name", "Vlad");
                    obj.put("last name", "Lebedev");
                    obj.put("age", "20");

                    FileWriter writer = new FileWriter("file.json");
                    writer.write(obj.toString());
                    writer.flush();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                */
                Fragment addBasketFragment = new AddBasketFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.add_basket_container_fragment, addBasketFragment).commit();
            }
        });
        frameSizeArray[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
                //
                Fragment addBasketFragment = new AddBasketFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.add_basket_container_fragment, addBasketFragment).commit();
            }
        });
        frameSizeArray[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
                //
                Fragment addBasketFragment = new AddBasketFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.add_basket_container_fragment, addBasketFragment).commit();
            }
        });
        frameSizeArray[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
                //
                Fragment addBasketFragment = new AddBasketFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.add_basket_container_fragment, addBasketFragment).commit();
            }
        });
        frameSizeArray[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
                //
                Fragment addBasketFragment = new AddBasketFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.add_basket_container_fragment, addBasketFragment).commit();
            }
        });
        frameSizeArray[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //getParentFragment().getChildFragmentManager().beginTransaction().remove(SelectSizeFragment.this).commit();
                //
                Fragment addBasketFragment = new AddBasketFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.add_basket_container_fragment, addBasketFragment).commit();
            }
        });


        return view;
    }

    public void init(){
        buttonClose = view.findViewById(R.id.idClose);
        buttonXS = view.findViewById(R.id.buttonXS);

        for (int i = 0; i < frameSizeArray.length; i++) {
            frameSizeArray[i] = view.findViewById(frameSizeResourceArray[i]);
        }
        for (int i = 0; i < textViewsArray.length; i++) {
            textViewsArray[i] = view.findViewById(textViewNumberResource[i]);
        }
    }

}