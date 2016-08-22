package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.dagger.components.ComponentProvider;
import com.toranj.tyke.dagger.components.DaggerComponentProvider;
import com.toranj.tyke.dagger.modules.FragmentModule;
import com.toranj.tyke.dagger.modules.LotteryModule;
import com.toranj.tyke.dagger.modules.SpendingModule;
import com.toranj.tyke.restApi.LotteryApiInterface;
import com.toranj.tyke.ui.MainActivity;
import com.toranj.tyke.ui.fragments.listeners.DashboardFragmentListener;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by arash on 8/13/16.
 */
public class LotteryDetailsFragment extends DialogFragment {

    private ComponentProvider componentProvider;
    private String lotteryId;

    @Inject
    Retrofit retrofit;

    @Inject
    LotteryApiInterface lotteryApiInterface;

    public static LotteryDetailsFragment newInstance(String lotteryId) {
        LotteryDetailsFragment fragment = new LotteryDetailsFragment();
        fragment.lotteryId = lotteryId;
        return fragment;
    }

    public LotteryDetailsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_lottery_detail, container, false);
        view.invalidate();
//        initializeViewComponents(view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        if(activity != null) {
//            try {
//                activityListener = (DashboardFragmentListener) activity;
//            }
//            catch(ClassCastException e) {
//                Log.e("DashboardFragment",
//                        "The Activity passed is not an Instance of DashboardFragmentListener Interface");
//            }
//
//            initComponents();
//
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        activityListener = null;
    }

    private void initComponents() {

        componentProvider = DaggerComponentProvider.builder()
                .networkComponent(((TykeApp)getActivity().getApplication()).getNetworkComponent())
//                .fragmentModule(new FragmentModule((MainActivity)getActivity()))
                .lotteryModule(new LotteryModule())
                .spendingModule(new SpendingModule())
                .build();
        componentProvider.inject(this);
    }
}
