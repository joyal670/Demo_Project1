package com.demoproject.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demoproject.ApiModel.Model;
import com.demoproject.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<SliderViewHolder>
{
    Context context;
    List<Model.DataBean.BannersBean> banners;

    public ImageSliderAdapter(Context context,  List<Model.DataBean.BannersBean> banners) {
        this.context = context;
        this.banners = banners;
    }


    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_layout,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
//        viewHolder.sliderImageview.setImageResource(imageSliderModelList.get(position).getImage());

        Picasso.get().load(banners.get(position).getImage()).into(viewHolder.sliderImageview);
    }

    @Override
    public int getCount() {
        return banners.size();
    }
}

class  SliderViewHolder extends SliderViewAdapter.ViewHolder {
    ImageView sliderImageview;
    public SliderViewHolder(View itemView) {
        super(itemView);
        sliderImageview=itemView.findViewById(R.id.imageView);
    }
}



