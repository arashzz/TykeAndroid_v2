package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.toranj.tyke.R;
import com.toranj.tyke.adapters.DashboardViewPagerAdapter;
import com.toranj.tyke.ui.fragments.listeners.DashboardFragmentListener;

public class DashboardFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private DashboardFragmentListener activityListener;

    private final String TAB_LOTTERIES = "قرعه کشی";
    private final String TAB_BRANDS = "برند";
    private final int TAB_ID_LOTTERIES = 1;
    private final int TAB_ID_BRANDS = 2;

    ViewPager viewPager;
    TabLayout tabLayout;

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //go();
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
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

//            initComponents();

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        DashboardViewPagerAdapter viewPagerAdapter = new DashboardViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new DashboardBrandsFragment(), TAB_BRANDS, TAB_ID_BRANDS);
        viewPagerAdapter.addFragment(new DashboardLotteriesFragment(), TAB_LOTTERIES, TAB_ID_LOTTERIES);

        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initializeViewComponents(View view) {

        viewPager = (ViewPager)view.findViewById(R.id.pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.dashboard_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.onFabClicked();
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
