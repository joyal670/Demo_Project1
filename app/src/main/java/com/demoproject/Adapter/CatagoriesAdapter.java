package com.demoproject.Adapter;

import android.content.Context;
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

import java.util.List;
import com.demoproject.ApiModel.Model.DataBean.CategoriesBean;

import de.hdodenhof.circleimageview.CircleImageView;

public class CatagoriesAdapter extends RecyclerView.Adapter<CatagoriesAdapter.ImageHolder>
{
    Context context;
    List<Model.DataBean.CategoriesBean> categories;
    CatagoriesAdapter.OnitemClickListener mlistener;

    public CatagoriesAdapter(Context context, List<Model.DataBean.CategoriesBean> categories) {
        this.context = context;
        this.categories = categories;
    }


    @NonNull
    @Override
    public CatagoriesAdapter.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.catagories_layout, parent, false);
        return new CatagoriesAdapter.ImageHolder(view);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CircleImageView catagories_image;
        TextView catagories_name;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            catagories_image = itemView.findViewById(R.id.catagories_image);
            catagories_name = itemView.findViewById(R.id.catagories_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mlistener != null)
            {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                {
                    mlistener.onItemClick(position);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoriesAdapter.ImageHolder holder, int position) {

        holder.catagories_name.setText(categories.get(position).getName());
        Picasso.get().load(categories.get(position).getImage()).into(holder.catagories_image);

    }

    public interface OnitemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnClickListener(CatagoriesAdapter.OnitemClickListener listener)
    {
        mlistener = listener;
    }


}
