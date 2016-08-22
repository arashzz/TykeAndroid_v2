package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paginate.Paginate;
import com.paginate.recycler.LoadingListItemCreator;
import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.ui.MainActivity.LoadingViewHolder;
import com.toranj.tyke.adapters.BrandAdapter;
import com.toranj.tyke.dagger.components.ComponentProvider;
import com.toranj.tyke.dagger.components.DaggerComponentProvider;
import com.toranj.tyke.dagger.modules.BrandModule;
import com.toranj.tyke.dagger.modules.FragmentModule;
import com.toranj.tyke.dagger.modules.LotteryModule;
import com.toranj.tyke.dagger.modules.SpendingModule;
import com.toranj.tyke.models.Brand;
import com.toranj.tyke.restApi.BrandApiInterface;
import com.toranj.tyke.retrofit.TykeCallbackResponse;
import com.toranj.tyke.ui.MainActivity;
import com.toranj.tyke.ui.fragments.listeners.BrandsFragmentListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by arash on 8/13/16.
 */
public class BrandsFragment extends DialogFragment implements Paginate.Callbacks{

    private ComponentProvider componentProvider;
    private BrandsFragmentListener activityListener;
    private BrandAdapter brandAdapter;

    private RecyclerView recyclerView;
    private boolean isLoading;
    private ContentLoadingProgressBar progressBar;

    @Inject
    Retrofit retrofit;

    @Inject
    BrandApiInterface brandApiInterface;

    public static BrandsFragment newInstance() {
        BrandsFragment fragment = new BrandsFragment();
        return fragment;
    }

    public BrandsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLoading = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_brands, container, false);
        view.invalidate();
        initializeViewComponents(view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity != null) {
            try {
                activityListener = (BrandsFragmentListener) activity;
            }
            catch(ClassCastException e) {
                Log.e("BrandsFragment",
                        "The Activity passed is not an Instance of BrandsFragmentListener Interface");
                throw new ClassCastException("The Activity passed is not an Instance of BrandsFragmentListener Interface");
            }
            ((TykeApp)getActivity().getApplication()).getComponentProvider().inject(this);

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    private void initializeViewComponents(View view) {

        progressBar = (ContentLoadingProgressBar)view.findViewById(R.id.progress_brands);

        //RecyclerView initialization
        recyclerView = (RecyclerView) view.findViewById(R.id.list_brands);

        //Adapter for RecyclerView
        brandAdapter = new BrandAdapter(new ArrayList<Brand>());
        recyclerView.setAdapter(brandAdapter);

        final LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);

        Paginate.with(recyclerView, this)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(new CustomLoadingListItemCreator())
                .build();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                populateBrands(null);
            }
        }, 2000);

    }

    private void populateBrands(List<Brand> result) {
        progressBar.hide();
        List<Brand> brands = new ArrayList<>();
        Brand brand = new Brand();
        brand.setName("test brand");
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brands.add(brand);
        brandAdapter.addItems(brands);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMore() {
        isLoading = true;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return false;
    }

    public class BrandCallback implements TykeCallbackResponse<List<Brand>> {
        @Override
        public void onResponse(List<Brand> result) {
            populateBrands(result);
            isLoading = false;
        }

        @Override
        public void onFailure() {
            isLoading = false;
        }
    }

    private class CustomLoadingListItemCreator implements LoadingListItemCreator {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.custom_loading_list_item, parent, false);
            return new LoadingViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            LoadingViewHolder vh = (LoadingViewHolder)holder;
        }
    }

}

