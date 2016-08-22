package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.restApi.UserApiInterface;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by arash on 8/18/16.
 */
public class Register2Fragment extends Fragment {

    private RegisterFragmentListener activityListener;

    public static Register2Fragment newInstance() {
        Register2Fragment fragment = new Register2Fragment();
        return fragment;
    }

    public Register2Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_1, container, false);
        view.invalidate();
        initializeViewComponents(view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity != null) {
            try {
                activityListener = (RegisterFragmentListener) activity;
            }
            catch(ClassCastException e) {
                Log.e("BrandsFragment",
                        "The Activity passed is not an Instance of RegisterFragmentListener Interface");
                throw new ClassCastException("The Activity passed is not an Instance of RegisterFragmentListener Interface");
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    private void initializeViewComponents(View view) {

    }
}
