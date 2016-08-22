package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.restApi.UserApiInterface;
import com.toranj.tyke.ui.fragments.listeners.BrandsFragmentListener;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;


/**
 * Created by arash on 8/18/16.
 */
public class Register1Fragment extends Fragment implements View.OnClickListener{

    private RegisterFragmentListener activityListener;

    private NumberPicker dayNumberPicker, monthNumberPicker, yearNumberPicker, sexNumberPicker;

    public static Register1Fragment newInstance() {
        Register1Fragment fragment = new Register1Fragment();
        return fragment;
    }

    public Register1Fragment() {

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
        ((TykeApp)getActivity().getApplication()).getComponentProvider().inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    @Override
    public void onClick(View view) {
        int sex = sexNumberPicker.getValue();
        int day = dayNumberPicker.getValue();
        int month = monthNumberPicker.getValue();
        int year = yearNumberPicker.getValue();
    }

    private void initializeViewComponents(View view) {
        String[] sexes = { "مرد", "زن", "غیره" };
        sexNumberPicker = (NumberPicker)view.findViewById(R.id.np_sex);
        sexNumberPicker.setOnLongPressUpdateInterval(300);
        sexNumberPicker.setMinValue(0);
        sexNumberPicker.setMaxValue(1);
        sexNumberPicker.setMaxValue(2);
        sexNumberPicker.setDisplayedValues(sexes);

        dayNumberPicker = (NumberPicker)view.findViewById(R.id.np_day);
        dayNumberPicker.setOnLongPressUpdateInterval(100);
        dayNumberPicker.setMinValue(1);
        dayNumberPicker.setMaxValue(31);

        String[] months = { "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
                "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند",};
        monthNumberPicker = (NumberPicker)view.findViewById(R.id.np_month);
        monthNumberPicker.setOnLongPressUpdateInterval(300);
        monthNumberPicker.setMinValue(0);
        monthNumberPicker.setMaxValue(11);
        monthNumberPicker.setDisplayedValues(months);

        yearNumberPicker = (NumberPicker)view.findViewById(R.id.np_year);
        yearNumberPicker.setOnLongPressUpdateInterval(50);
        yearNumberPicker.setMinValue(1340);
        yearNumberPicker.setMaxValue(1385);

        Button nextStep = (Button)view.findViewById(R.id.btn_register_step_2);
        nextStep.setOnClickListener(this);
    }
}
