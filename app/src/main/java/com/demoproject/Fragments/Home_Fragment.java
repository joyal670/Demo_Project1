package com.demoproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demoproject.Adapter.CatagoriesAdapter;
import com.demoproject.Adapter.FreshProdctAdaper;
import com.demoproject.Adapter.ImageSliderAdapter;
import com.demoproject.ApiClient.Client;
import com.demoproject.ApiInterface.Interface;
import com.demoproject.ApiModel.Model;
import com.demoproject.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_Fragment extends Fragment implements FreshProdctAdaper.OnProductClickListener, CatagoriesAdapter.OnitemClickListener
{
    SliderView sliderView;
    List<Model.DataBean.BannersBean> banners;
    ImageSliderAdapter imageSliderAdapter;

    RecyclerView home_catagories_recycerlview;
    List<Model.DataBean.CategoriesBean> categories;
    CatagoriesAdapter catagoriesAdapter;

    RecyclerView newproduct_recycerview;
    List<Model.DataBean.FreshProductsBean> freshProducts;
    FreshProdctAdaper freshProdctAdaper;


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // binding widgets
        sliderView = view.findViewById(R.id.imageSlider);
        home_catagories_recycerlview = view.findViewById(R.id.home_catagories_recycerlview);
        newproduct_recycerview = view.findViewById(R.id.newproduct_recycerview);

        // setting layout for recyceriview
        home_catagories_recycerlview.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        home_catagories_recycerlview.setLayoutManager(layoutManager);
        home_catagories_recycerlview.setHasFixedSize(true);

        // api calls
        Interface apiInterface = Client.getClient(getActivity()).create(Interface.class);
        Call<Model> getBanner = apiInterface.getProducts();
        getBanner.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response)
            {
                if(response.isSuccessful())
                {
                    banners.clear();
                    categories.clear();
                    freshProducts.clear();

                    banners.addAll(response.body().getData().getBanners());
                    categories.addAll(response.body().getData().getCategories());
                    freshProducts.addAll(response.body().getData().getFresh_products());

                    imageSliderAdapter.notifyDataSetChanged();
                    catagoriesAdapter.notifyDataSetChanged();
                    freshProdctAdaper.notifyDataSetChanged();

                }
                else
                {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t)
            {
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
                System.out.println(t);
            }
        });

        banners = new ArrayList<>();
        imageSliderAdapter = new ImageSliderAdapter(getContext(), banners);
        sliderView.setSliderAdapter(imageSliderAdapter);
        sliderView.startAutoCycle();

        categories = new ArrayList<>();
        catagoriesAdapter = new CatagoriesAdapter(getContext(), categories);
        home_catagories_recycerlview.setAdapter(catagoriesAdapter);

        freshProducts = new ArrayList<>();
        freshProdctAdaper = new FreshProdctAdaper(getContext(), freshProducts);
        newproduct_recycerview.setAdapter(freshProdctAdaper);

        return view;
    }

    // on click
    @Override
    public void onItemClick(int position)
    {
    }

    // on click
    @Override
    public void OnProductClickListener(int position)
    {
    }
}