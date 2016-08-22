package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.adapters.DashboardBrandsAdapter;
import com.toranj.tyke.models.Brand;
import com.toranj.tyke.models.DashboardTag;
import com.toranj.tyke.restApi.BrandApiInterface;
import com.toranj.tyke.ui.fragments.listeners.DashboardFragmentListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by arash on 8/15/16.
 */
public class DashboardBrandsFragment extends Fragment {

    //View objects
    private ContentLoadingProgressBar progressBar;
    private LinearLayout container;

    //Listener
    private DashboardFragmentListener activityListener;

    @Inject
    Retrofit retrofit;

    @Inject
    BrandApiInterface brandApiInterface;


    public DashboardBrandsFragment() {

    }

    public static DashboardBrandsFragment getInstance() {
        DashboardBrandsFragment fragment = new DashboardBrandsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard_brands, container, false);
        view.invalidate();
        initializeViewComponents(view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity != null) {
            try {
                activityListener = (DashboardFragmentListener) activity;
            }
            catch(ClassCastException e) {
                Log.e("DashboardFragment",
                        "The Activity passed is not an Instance of DashboardFragmentListener Interface");
            }
        }
        ((TykeApp)getActivity().getApplication()).getComponentProvider().inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    private void initializeViewComponents(View view) {
        progressBar = (ContentLoadingProgressBar)view.findViewById(R.id.progress_dashboard_brands);
        container = (LinearLayout) view.findViewById(R.id.container_dashboard_brands);

        progressBar.hide();
        container.setVisibility(View.VISIBLE);

        List<Brand> brands = new ArrayList<>();
        Brand brand = new Brand();
        brand.setName("Nike");
        brand.setImage("https://yt3.ggpht.com/-Q8jolvY4uUw/AAAAAAAAAAI/AAAAAAAAAAA/vTOgdhghgt8/s900-c-k-no-mo-rj-c0xffffff/photo.jpg");
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

        DashboardTag category = new DashboardTag();
        category.setName("Sport");
        category.setBrands(brands);

        List<DashboardTag> list = new ArrayList<>();
        list.add(category);
        list.add(category);
        populateCategories(list);
    }

    private void populateCategories(List<DashboardTag> categories) {

        for(DashboardTag category:categories) {
            View view = View.inflate(getContext(), R.layout.dashboard_category_list_item, null);

            TextView categoryName = (TextView)view.findViewById(R.id.text_dashboard_category_name);
            categoryName.setText(category.getName());

            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.list_dashboard_brands);
            DashboardBrandsAdapter adapter = new DashboardBrandsAdapter(category.getBrands());
            recyclerView.setAdapter(adapter);
            final LinearLayoutManager lm = new LinearLayoutManager(getActivity());
            lm.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(lm);

            container.addView(view);
        }


    }
}
