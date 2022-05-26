package com.onlyapp.only;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class RecyclerViewGoodsAdapter extends RecyclerView.Adapter<RecyclerViewGoodsAdapter.ViewHolder> {
    private String[] names;
    private String[] descriptions;
    private String[] prices;
    private String[] images;
    private RecyclerViewGoodsAdapter.Listener listener;

    interface Listener{
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public RecyclerViewGoodsAdapter(String[] names, String[] descriptions, String[] prices, String[] images) {
        this.names = names;
        this.descriptions = descriptions;
        this.prices = prices;
        this.images = images;
    }

    public int getItemCount(){
        return names.length;
    }
    public void setListener(RecyclerViewGoodsAdapter.Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_goods, parent, false);
        return new RecyclerViewGoodsAdapter.ViewHolder(cv);
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imagePoster);
        Picasso.with(cardView.getContext()).load(images[position]).into(imageView);
        //Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageId[position]);
        //imageView.setBackground(drawable);

        TextView textViewName = (TextView) cardView.findViewById(R.id.textName);
        TextView textViewDescription = (TextView) cardView.findViewById(R.id.textDescription);
        TextView textViewPrice = (TextView) cardView.findViewById(R.id.textPrice);
        textViewName.setText(names[position]);
        textViewDescription.setText(descriptions[position]);
        textViewPrice.setText(prices[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }
}