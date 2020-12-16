package com.demoproject.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demoproject.ApiModel.Model;
import com.demoproject.R;
import com.squareup.picasso.Picasso;
import com.demoproject.ApiModel.Model.DataBean.FreshProductsBean;

import java.util.List;


public class FreshProdctAdaper  extends RecyclerView.Adapter<FreshProdctAdaper.ImageHolder>
{
    Context context;
    List<Model.DataBean.FreshProductsBean> freshProducts;
    FreshProdctAdaper.OnProductClickListener mProductlistener;

    public FreshProdctAdaper(Context context, List<Model.DataBean.FreshProductsBean> freshProducts) {
        this.context = context;
        this.freshProducts = freshProducts;
    }

    @NonNull
    @Override
    public FreshProdctAdaper.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.freshproduct_layout, parent, false);
        return new FreshProdctAdaper.ImageHolder(view);
    }

    @Override
    public int getItemCount() {
        return freshProducts.size();
    }


    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView freshproduct_image;
        TextView freshproduct_offer_price, freshproduct_name, freshproduct_selling_price, freshproduct_actual_price;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            freshproduct_image = itemView.findViewById(R.id.freshproduct_image);
            freshproduct_offer_price = itemView.findViewById(R.id.freshproduct_offer_price);
            freshproduct_name = itemView.findViewById(R.id.freshproduct_name);
            freshproduct_selling_price = itemView.findViewById(R.id.freshproduct_selling_price);
            freshproduct_actual_price = itemView.findViewById(R.id.freshproduct_actual_price);

            freshproduct_actual_price.setPaintFlags(freshproduct_actual_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mProductlistener != null)
            {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                {
                    mProductlistener.OnProductClickListener(position);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FreshProdctAdaper.ImageHolder holder, int position)
    {
        try {
            int dis;
            int pric;
            int tot;

             dis = Integer.parseInt(freshProducts.get(position).getDiscount());
             pric = Integer.parseInt(freshProducts.get(position).getPrice());
             tot = 100 * (pric - dis) / pric;

            holder.freshproduct_offer_price.setText(tot + "");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        holder.freshproduct_name.setText(freshProducts.get(position).getName());
        holder.freshproduct_selling_price.setText(freshProducts.get(position).getDiscount());
        holder.freshproduct_actual_price.setText(freshProducts.get(position).getPrice());
        Picasso.get().load(freshProducts.get(position).getImage()).into(holder.freshproduct_image);

    }

    public interface OnProductClickListener
    {
        void OnProductClickListener(int position);
    }

    public void OnProductClickListener(FreshProdctAdaper.OnProductClickListener listener)
    {
        mProductlistener = listener;
    }

}
