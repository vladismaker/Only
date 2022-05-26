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

public class RecyclerViewHimAndHerAdapter extends RecyclerView.Adapter<RecyclerViewHimAndHerAdapter.ViewHolder> {
    private String[] names;
    private String[] images;
    private RecyclerViewHimAndHerAdapter.Listener listener;

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

    public RecyclerViewHimAndHerAdapter(String[] names, String[] images) {
        this.names = names;
        this.images = images;
    }

    public int getItemCount(){
        return names.length;
    }
    public void setListener(RecyclerViewHimAndHerAdapter.Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHimAndHerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_him_and_her, parent, false);
        return new RecyclerViewHimAndHerAdapter.ViewHolder(cv);
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageViewId);
        Picasso.with(cardView.getContext()).load(images[position]).into(imageView);
        //Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageId[position]);
        //imageView.setBackground(drawable);

        TextView textView = (TextView) cardView.findViewById(R.id.textViewName);
        textView.setText(names[position]);

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
