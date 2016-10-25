package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.models.User;
import com.toranj.tyke.restApi.UserApiInterface;
import com.toranj.tyke.retrofit.TykeCallback;
import com.toranj.tyke.retrofit.TykeCallbackResponse;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;


/**
 * Created by arash on 8/18/16.
 */
public class Register2Fragment extends Fragment implements TykeCallbackResponse<User>{

    private RegisterFragmentListener activityListener;
    private EditText vCodeEditText;
    private User currentUser;

    @Inject
    Retrofit retrofit;

    @Inject
    UserApiInterface userApiInterface;

    public static Register2Fragment newInstance(User currentUser) {
        Register2Fragment fragment = new Register2Fragment();
        fragment.currentUser = currentUser;
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
        View view = inflater.inflate(R.layout.fragment_register_2, container, false);
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
                Log.e("Register2Fragment",
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

    private void initializeViewComponents(View view) {
        vCodeEditText = (EditText)view.findViewById(R.id.et_register_verification_code);

        Button buttonSendCode = (Button)view.findViewById(R.id.btn_register_send_verification_code);
        buttonSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verificationCode = vCodeEditText.getText().toString();
                if(verificationCode != null && !verificationCode.isEmpty()) {
                    //TODO: uncomment later for actual api call
//                    Call<User> call = userApiInterface.verify(currentUser, verificationCode);
//                    call.enqueue(new TykeCallback<User>(Register2Fragment.this));
                    //TODO: comment this when making an actual api call
                    activityListener.onStep2Finished(null);
                }
            }
        });

        Button buttonCancel = (Button)view.findViewById(R.id.btn_register_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.onRegisterCancel();
            }
        });
    }

    @Override
    public void onResponse(User result) {
        if(result != null && result.isVerified()) {
            activityListener.onStep2Finished(result);
        }
        else {
            //TODO: show user that verification process failed
        }
    }

    @Override
    public void onFailure() {
        Log.e("something happened", "oh my");
    }
}
