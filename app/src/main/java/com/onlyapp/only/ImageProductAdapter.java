package com.onlyapp.only;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ImageProductAdapter extends RecyclerView.Adapter<ImageProductAdapter.ProductViewHolder>{
    private String[] images;
    private Context context;
    //private final List<Product> products;

    public ImageProductAdapter(String[] images, Context context) {
        //this.products = products;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.image_main_product_page,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.setProduct(images[position], context);
        //holder.setProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return images.length;
        //return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder{

        private  final ImageView imagePoster;

        public ProductViewHolder(View view){
            super(view);
            imagePoster = view.findViewById(R.id.imageMainProductPagePoster);
        }

        void setProduct(String image, Context context){
            //Пикассо
            Picasso.with(context).load(image).into(imagePoster);
            //imagePoster.setImageResource(image);
        }
    }
}

/*

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

        import com.squareup.picasso.Picasso;

public class ImageProductAdapter extends RecyclerView.Adapter<ImageProductAdapter.ViewHolder> {
    private String[] images;
    private ImageProductAdapter.Listener listener;

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

    public ImageProductAdapter(String[] images) {
        this.images = images;
    }

    public int getItemCount(){
        return images.length;
    }

    public void setListener(ImageProductAdapter.Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_goods, parent, false);
        return new ImageProductAdapter.ViewHolder(cv);
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imagePoster);
        Picasso.with(cardView.getContext()).load(images[position]).into(imageView);

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
*/
