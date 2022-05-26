package com.onlyapp.only;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapterMainForWeek extends RecyclerView.Adapter<ProductAdapterMainForWeek.ProductViewHolder>{

    private final List<Product> products;

    public ProductAdapterMainForWeek(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card_main_for_week,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.setProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder{

        private  final ImageView imagePoster;
        private final TextView textName, textDescription;

        public ProductViewHolder(View view){
            super(view);

            imagePoster = view.findViewById(R.id.imagePoster);
            textName = view.findViewById(R.id.textName);
            textDescription = view.findViewById(R.id.textDescription);
        }

        void setProduct(Product product){
            imagePoster.setImageResource(product.poster);
            textName.setText(product.name);
            textDescription.setText(product.description);
        }
    }
}
