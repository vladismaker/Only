package com.onlyapp.only;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AddBasketFragment extends Fragment {
    View view;
    ImageButton closeFr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_basket, container, false);
        init();

        closeFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragment().getChildFragmentManager().beginTransaction().remove(AddBasketFragment.this).commit();
            }
        });

        return view;
    }

    public void init(){
        closeFr = view.findViewById(R.id.idCloseAddBasket);
    }
}