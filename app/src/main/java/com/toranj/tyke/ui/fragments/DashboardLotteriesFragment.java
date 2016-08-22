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

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.adapters.DashboardLotteriesAdapter;
import com.toranj.tyke.models.Lottery;
import com.toranj.tyke.models.Spending;
import com.toranj.tyke.restApi.LotteryApiInterface;
import com.toranj.tyke.retrofit.TykeCallbackResponse;
import com.toranj.tyke.ui.fragments.listeners.DashboardFragmentListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by arash on 8/15/16.
 */
public class DashboardLotteriesFragment extends Fragment {

    //View objects
    private ContentLoadingProgressBar progressBar;
    private RecyclerView recyclerView;

    //Adapters
    private DashboardLotteriesAdapter lotteriesAdapter;

    //Listener
    private DashboardFragmentListener activityListener;

    //Injections
    @Inject
    Retrofit retrofit;

    @Inject
    LotteryApiInterface lotteryApiInterface;

    public DashboardLotteriesFragment() {

    }

    public static DashboardLotteriesFragment getInstance() {
        DashboardLotteriesFragment fragment = new DashboardLotteriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard_lotteries, container, false);
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

    private void go() {

    }

    private void initializeViewComponents(View view) {
        progressBar = (ContentLoadingProgressBar)view.findViewById(R.id.progress_dashboard_lotteries);
        recyclerView = (RecyclerView)view.findViewById(R.id.list_dashboard_lotteries);

        lotteriesAdapter = new DashboardLotteriesAdapter(new ArrayList<Object>());
        recyclerView.setAdapter(lotteriesAdapter);

        final LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);


        List<Object> result = new ArrayList<>();
        Spending spending = new Spending();
        spending.setAmount(23000);
        result.add(spending);
        Lottery lottery1 = new Lottery();
        lottery1.setName("روز پدر");
        result.add(lottery1);
        Lottery lottery2 = new Lottery();
        lottery2.setName("شب نشینی");
        result.add(lottery2);
        Lottery lottery3 = new Lottery();
        lottery3.setName("روز مادر");
        result.add(lottery3);
        Lottery lottery = new Lottery();
        lottery.setName("عید نوروز");
        result.add(lottery);
        Lottery lottery5 = new Lottery();
        lottery5.setName("ماه رمدون ۲");
        result.add(lottery5);
        Lottery lottery6 = new Lottery();
        lottery6.setName("الکی پلکی");
        result.add(lottery6);
        Lottery lottery7 = new Lottery();
        lottery7.setName("پول زور بده");
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);
        result.add(lottery7);

        progressBar.hide();
        recyclerView.setVisibility(View.VISIBLE);
        lotteriesAdapter.addItems(result);
    }

    private void populateLotteries(List<Lottery> lotteris) {
        lotteriesAdapter.addItems(new ArrayList<Object>(lotteris));
    }

    private void populateSpending(Spending spending) {
        lotteriesAdapter.addItem(spending);
    }

    public class LotteryCallback implements TykeCallbackResponse<List<Lottery>> {
        @Override
        public void onResponse(List<Lottery> result) {
            populateLotteries(result);
        }

        @Override
        public void onFailure() {

        }
    }
    public class SpendingCallback implements TykeCallbackResponse<Spending> {
        @Override
        public void onResponse(Spending result) {
            populateSpending(result);
        }

        @Override
        public void onFailure() {

        }
    }
}
