package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.toranj.tyke.R;
import com.toranj.tyke.TykeApp;
import com.toranj.tyke.adapters.TagsAdapter;
import com.toranj.tyke.models.Tag;
import com.toranj.tyke.models.User;
import com.toranj.tyke.restApi.TagApiInterface;
import com.toranj.tyke.retrofit.TykeCallback;
import com.toranj.tyke.retrofit.TykeCallbackResponse;
import com.toranj.tyke.ui.fragments.listeners.RegisterFragmentListener;
import com.toranj.tyke.ui.fragments.listeners.TagFragmentListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by arash on 8/18/16.
 */
public class Register3Fragment extends Fragment implements TagFragmentListener {

    private final int GRID_SPAN_COUNT = 3;
    private RegisterFragmentListener activityListener;
    private User currentUser;
    private List<Tag> selectedTags;
    private TagsAdapter tagsAdapter;

    private RecyclerView recyclerView;

    @Inject
    Retrofit retrofit;

    @Inject
    TagApiInterface tagApiInterface;

    public static Register3Fragment newInstance(User user) {
        Register3Fragment fragment = new Register3Fragment();
        fragment.currentUser = user;
        return fragment;
    }

    public Register3Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedTags = new ArrayList<>();
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
                Log.e("Register3Fragment",
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

    private void getTags() {
        Call<List<Tag>> call = tagApiInterface.getTagsForUser(currentUser);
        call.enqueue(new TykeCallback<List<Tag>>(new AllTagsResponse()));
    }

    private void initializeViewComponents(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_register_tags_list);

        final RecyclerView.LayoutManager lm = new GridLayoutManager(
                getActivity(),
                GRID_SPAN_COUNT,
                GridLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(lm);
        List<Tag> tags = new ArrayList<>();
        for(int i = 0;i < 100;i++) {
            Tag tag = new Tag();
            tag.setName("Test Tag");
            tag.setId(String.valueOf(i));
            tags.add(tag);
        }

        tagsAdapter = new TagsAdapter(getActivity(), tags, this);
        recyclerView.setAdapter(tagsAdapter);

        Button btnNextStep = (Button)view.findViewById(R.id.btn_register_finish);
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Boolean> call = tagApiInterface.addTagsForUser(currentUser, selectedTags);
                call.enqueue(new TykeCallback<Boolean>(new AddTagsResponse()));
            }
        });

        Button btnSkipStep = (Button)view.findViewById(R.id.btn_register_skip);
        btnSkipStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.onStep2Finished(currentUser);
            }
        });
    }

    private void populateTags(List<Tag> tags) {
        if(tags != null) {
            tagsAdapter.addItems(tags);
        }
    }

    @Override
    public void onTagSelected(Tag tag) {
        selectedTags.add(tag);
        Log.d("TAG ADDED", "With ID = " + tag.getId());
    }

    @Override
    public void onTagDeselected(Tag tag) {
        selectedTags.remove(tag);
        Log.d("TAG REMOVED", "With ID = " + tag.getId());
    }

    private class AllTagsResponse implements TykeCallbackResponse<List<Tag>> {
        @Override
        public void onResponse(List<Tag> result) {
            populateTags(result);
        }

        @Override
        public void onFailure() {

        }
    }

    private class AddTagsResponse implements TykeCallbackResponse<Boolean> {
        @Override
        public void onResponse(Boolean result) {

        }

        @Override
        public void onFailure() {

        }
    }
}