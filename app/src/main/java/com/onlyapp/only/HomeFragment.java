package com.onlyapp.only;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view==null){
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        setupProductsViewPager();
        setupProductsViewPagerMainForWeek();
        
        return view;
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
        productsViewPager.setAdapter(new ProductAdapter(getNewProducts()));
    }

    private void setupProductsViewPagerMainForWeek(){
        ViewPager2 productsViewPager = view.findViewById(R.id.productsViewPagerMain);
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
        productsViewPager.setAdapter(new ProductAdapterMainForWeek(getProductsMainForWeek()));
    }

    private List<Product> getNewProducts(){
        List<Product> productsNew = new ArrayList<>();

        Product product1 = new Product();
        product1.poster = R.drawable.product1;
        product1.name = "Palm Angels";
        product1.description = "пуховик с принтом из коллаборации с Moncler";
        product1.price = "136 700 Р";
        productsNew.add(product1);

        Product product2 = new Product();
        product2.poster = R.drawable.product2;
        product2.name = "Alexander McQueen";
        product2.description = "сумка-мессенджер размера мини с принтом граффити";
        product2.price = "59 650 Р";
        productsNew.add(product2);

        Product product3 = new Product();
        product3.poster = R.drawable.product3;
        product3.name = "Versace";
        product3.description = "кеды на платформе с узором Greca";
        product3.price = "43 600 Р";
        productsNew.add(product3);

        return productsNew;
    }

    private List<Product> getProductsMainForWeek(){
        List<Product> productsMainForWeek = new ArrayList<>();

        Product product1 = new Product();
        product1.poster = R.drawable.product_main_for_week1;
        product1.name = "VALENTINO GARAVANI";
        product1.description = "Сумка Roman Stud и другие аксессуары";
        product1.price = "";
        productsMainForWeek.add(product1);

        Product product2 = new Product();
        product2.poster = R.drawable.product_main_for_week2;
        product2.name = "КОЛЛЕКЦИОННЫЕ ПРЕДМЕТЫ";
        product2.description = "подарки, которые точно понравятся";
        product2.price = "";
        productsMainForWeek.add(product2);

        Product product3 = new Product();
        product3.poster = R.drawable.product_main_for_week3;
        product3.name = "85+ ПУХОВИКОВ";
        product3.description = "ИЗ коллекций AGR и других брендов";
        product3.price = "";
        productsMainForWeek.add(product3);

        return productsMainForWeek;
    }
}