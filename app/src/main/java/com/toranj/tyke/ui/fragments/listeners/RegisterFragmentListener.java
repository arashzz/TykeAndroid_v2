package com.toranj.tyke.ui.fragments.listeners;

import com.toranj.tyke.models.Tag;

import java.util.List;

/**
 * Created by arash on 8/18/16.
 */
public interface RegisterFragmentListener {
    void onStep1Finished(String sex, int date, int month, int year);
    void onStep2Finished(String firstName, String lastName, String mobileNumber, List<Tag> tags);
}
