package com.toranj.tyke.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.models.User;
import com.toranj.tyke.restApi.UserApiInterface;
import com.toranj.tyke.retrofit.TykeCallback;
import com.toranj.tyke.retrofit.TykeCallbackResponse;
import com.toranj.tyke.ui.fragments.listeners.BrandsFragmentListener;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;


/**
 * Created by arash on 8/18/16.
 */
public class Register1Fragment extends Fragment implements View.OnClickListener, TykeCallbackResponse<User> {

    private RegisterFragmentListener activityListener;
    private EditText firstNameTextView, lastNameTextView, cellNumberTextView;
    private ImageButton maleImageButton, femaleImageButton;
    private NumberPicker dayNumberPicker, monthNumberPicker, yearNumberPicker;

    private final String TAG_SEX_MALE = "male";
    private final String TAG_SEX_FEMALE = "female";

    @Inject
    Retrofit retrofit;

    @Inject
    UserApiInterface userApiInterface;

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


    //assign the delegate here in the onAttach
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity != null) {
            try {
                activityListener = (RegisterFragmentListener) activity;
            }
            catch(ClassCastException e) {
                Log.e("Register1Fragment",
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

    }

    private void initializeViewComponents(View view) {
//
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
//
        firstNameTextView = (EditText) view.findViewById(R.id.et_first_name);
        lastNameTextView = (EditText) view.findViewById(R.id.et_last_name);
        cellNumberTextView = (EditText) view.findViewById(R.id.et_cell_number);

        maleImageButton = (ImageButton)view.findViewById(R.id.ib_register_male);
        maleImageButton.setTag(TAG_SEX_MALE);
        maleImageButton.setOnClickListener(new SexButtonOnClickListener());
        femaleImageButton = (ImageButton)view.findViewById(R.id.ib_register_female);
        femaleImageButton.setTag(TAG_SEX_FEMALE);
        femaleImageButton.setOnClickListener(new SexButtonOnClickListener());

        //sexRadioGroup = (RadioGroup)view.findViewById(R.id.rg_register_sex);
        Button nextStep = (Button)view.findViewById(R.id.btn_register_next_step);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = firstNameTextView.getText() + " " + lastNameTextView.getText();
                User user = new User();
                user.setName(fullName);
                //user.setSex(getSelectedSex());
                user.setDateOfBirth(getSelectedDateOfBirth());
                user.setUsername(cellNumberTextView.getText().toString());
                //TODO: uncomment later for actual api call
                //Call<User> call = userApiInterface.register(user);
                //call.enqueue(new TykeCallback<User>(Register1Fragment.this));
                //TODO: comment this when making an actual api call
                activityListener.onStep1Finished(user);
            }
        });

        Button cancel = (Button)view.findViewById(R.id.btn_register_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.onRegisterCancel();
            }
        });
    }


    private String getSelectedSex() {
//        int selectedSexId = sexRadioGroup.getCheckedRadioButtonId();
//        if(selectedSexId == R.id.rb_register_female) {
//            return "female";
//        }
        return "male";
    }

    private String getSelectedDateOfBirth() {
        return dayNumberPicker.getValue() + "/" +
                monthNumberPicker.getValue() + "/" +
                yearNumberPicker.getValue();
    }

    @Override
    public void onResponse(User result) {
        if(result != null && result.getId() != null && !result.getId().isEmpty()) {
            activityListener.onStep1Finished(result);
        }
        else {
            //TODO: how to show that the registration has failed
        }
    }

    @Override
    public void onFailure() {

    }

    private class SexButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v.getTag().toString().equals(TAG_SEX_MALE) && !v.isSelected()) {

                maleImageButton.setSelected(true);
                maleImageButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary_dark));
                maleImageButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.primary));

                femaleImageButton.setSelected(false);
                femaleImageButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary));
                femaleImageButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.primary_dark));
            }
            else if(v.getTag().toString().equals(TAG_SEX_FEMALE) && !v.isSelected()) {
                femaleImageButton.setSelected(true);
                femaleImageButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary_dark));
                femaleImageButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.primary));

                maleImageButton.setSelected(false);
                maleImageButton.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary));
                maleImageButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.primary_dark));
            }
        }
    }
}
