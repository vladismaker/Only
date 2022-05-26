package com.onlyapp.only;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ProductPageFragment extends Fragment {
    View view;
    static int position;
    String[] imagesArr;
    ArrayList<String> imagesArrayList = new ArrayList<>();

//    static FragmentTransaction transaction;
    static Fragment selectSizeFragment;
    static JSONObject goodsData;

    Button buttonAddBasket;
    ImageButton idBack;
    FrameLayout textDescriptionFrame, textBrandFrame, textCompositionFrame;
    TextView textViewNameProductPage, textViewDescriptionShortProductPage, textViewPriceProductPage, textViewDescriptionProductPage, textViewBrandProductPage, textViewCompositionProductPage;
    ImageView imageViewDescriptionProductPage, imageViewBrandProductPage, imageViewCompositionProductPage;
    static ImageView backSelectSize;
    boolean textDescriptionStatus = true, textBrandStatus = false, textCompositionStatus=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_page, container, false);

        init();

        try {
            goodsData = GoodsFragment.jsonArrayGoods.getJSONObject(position);
            textViewNameProductPage.setText(goodsData.getString("name"));
            textViewDescriptionShortProductPage.setText(goodsData.getString("descriptionShort"));
            textViewPriceProductPage.setText(goodsData.getString("price"));
            textViewDescriptionProductPage.setText(goodsData.getString("description"));
            textViewBrandProductPage.setText(goodsData.getString("brand"));
            textViewCompositionProductPage.setText(goodsData.getString("compositionAndCareRecommendations"));

            JSONArray imagesArrayData = goodsData.getJSONArray("images");
            for (int i = 0; i < imagesArrayData.length(); i++) {
                JSONObject imageData = imagesArrayData.getJSONObject(i);
                imagesArrayList.add(imageData.getString("image"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        imagesArr = imagesArrayList.toArray(new String[0]);

        showElements();

        textDescriptionFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDescriptionStatus= !textDescriptionStatus;
                showElements();
                if(textDescriptionStatus){
                    textViewDescriptionProductPage.getParent().requestChildFocus(textViewDescriptionProductPage, textViewDescriptionProductPage);
                }
            }
        });
        textCompositionFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCompositionStatus= !textCompositionStatus;
                showElements();
            }
        });
        textBrandFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBrandStatus= !textBrandStatus;
                showElements();
            }
        });

        idBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GoodsFragment()).commit();
            }
        });
        buttonAddBasket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backSelectSize.setVisibility(View.VISIBLE);
                selectSizeFragment = new SelectSizeFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.select_size_container_fragment, selectSizeFragment).commit();
            }
        });

        setupProductsViewPager();

        return view;
    }

    public void init(){
        idBack = view.findViewById(R.id.idBack);
        buttonAddBasket = view.findViewById(R.id.button_add_basket);
        backSelectSize = view.findViewById(R.id.backSelectSize);

        textDescriptionFrame = view.findViewById(R.id.textDescriptionFrame);
        textBrandFrame = view.findViewById(R.id.textBrandFrame);
        textCompositionFrame = view.findViewById(R.id.textCompositionFrame);

        textViewNameProductPage = view.findViewById(R.id.textViewNameProduct);
        textViewPriceProductPage = view.findViewById(R.id.textViewPriceProduct);
        textViewDescriptionShortProductPage = view.findViewById(R.id.textViewDescriptionShortProduct);
        textViewDescriptionProductPage = view.findViewById(R.id.textViewDescriptionProductPage);
        textViewBrandProductPage = view.findViewById(R.id.textViewBrandProductPage);
        textViewCompositionProductPage = view.findViewById(R.id.textViewCompositionProductPage);

        //imageViewMain = view.findViewById(R.id.imageViewProduct);

        imageViewDescriptionProductPage = view.findViewById(R.id.imageViewDescriptionProductPage);
        imageViewBrandProductPage = view.findViewById(R.id.imageViewBrandProductPage);
        imageViewCompositionProductPage = view.findViewById(R.id.imageViewCompositionProductPage);
    }

    public void showElements(){
        if(textDescriptionStatus){
            textViewDescriptionProductPage.setVisibility(View.VISIBLE);
            imageViewDescriptionProductPage.setImageResource(R.drawable.ic_up);
        }else {
            textViewDescriptionProductPage.setVisibility(View.GONE);
            imageViewDescriptionProductPage.setImageResource(R.drawable.ic_down);
        }

        if(textBrandStatus){
            textViewBrandProductPage.setVisibility(View.VISIBLE);
            textViewBrandProductPage.getParent().requestChildFocus(textViewBrandProductPage, textViewBrandProductPage);
            imageViewBrandProductPage.setImageResource(R.drawable.ic_up);
        }else {
            textViewBrandProductPage.setVisibility(View.GONE);
            imageViewBrandProductPage.setImageResource(R.drawable.ic_down);
        }

        if(textCompositionStatus){
            textViewCompositionProductPage.setVisibility(View.VISIBLE);
            textViewCompositionProductPage.getParent().requestChildFocus(textViewCompositionProductPage, textViewCompositionProductPage);
            imageViewCompositionProductPage.setImageResource(R.drawable.ic_up);
        }else {
            textViewCompositionProductPage.setVisibility(View.GONE);
            imageViewCompositionProductPage.setImageResource(R.drawable.ic_down);
        }
    }

    private void setupProductsViewPager(){
        ViewPager2 productsViewPager = view.findViewById(R.id.productsViewPagerNew);
        productsViewPager.setClipToPadding(false);
        productsViewPager.setClipChildren(false);
        productsViewPager.setOffscreenPageLimit(3);
        productsViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(5));
        compositePageTransformer.addTransformer(((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r*0.15f);
        }));
        productsViewPager.setPageTransformer(compositePageTransformer);
        productsViewPager.setAdapter(new ImageProductAdapter(imagesArr, getContext()));
    }

    public void setAddBasketFragment(){
        Fragment addBasketFragment = new AddBasketFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.select_size_container_fragment, addBasketFragment).commit();
    }
/*    private List<Product> getProducts(){
        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.poster = R.drawable.product1;
        for (int i = 0; i < imagesArray.lenght; i++) {

        }
        productList.add(product);

        return productList;
    }*/
}