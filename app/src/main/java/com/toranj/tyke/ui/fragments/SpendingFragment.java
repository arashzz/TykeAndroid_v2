package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.dagger.components.ComponentProvider;
import com.toranj.tyke.restApi.SpendingApiInterface;
import com.toranj.tyke.ui.fragments.listeners.SpendingDialogListener;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by arash on 8/10/16.
 */
public class SpendingFragment extends Fragment {

    private ComponentProvider componentProvider;
    private final static String KEY_TITLE = "title";
    private SpendingDialogListener activityListener;

    @Inject
    Retrofit retrofit;

    @Inject
    SpendingApiInterface spendingApiInterface;

    public SpendingFragment() {
    }

    public static SpendingFragment newInstance(String title) {
        SpendingFragment fragment = new SpendingFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        return super.onCreateDialog(savedInstanceState);
//
//        final RelativeLayout root = new RelativeLayout(getActivity());
//        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        final Dialog dialog = new Dialog(getActivity());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        return dialog;
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityListener = (SpendingDialogListener) activity;
        }
        catch(ClassCastException e) {
            Log.e("SpendingFragment",
                    "The Activity passed is not an Instance of SpendingDialogListener Interface");
        }
//        initComponents();
        ((TykeApp)getActivity().getApplication()).getComponentProvider().inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spending, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViewComponents(view);
    }

    private void initializeViewComponents(View view) {

        EditText codeEditText = (EditText)view.findViewById(R.id.et_spending_code);
        codeEditText.setFocusableInTouchMode(true);
        codeEditText.requestFocus();
        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
