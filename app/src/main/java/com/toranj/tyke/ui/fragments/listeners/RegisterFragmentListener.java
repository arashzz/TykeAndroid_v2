package com.toranj.tyke.ui.fragments.listeners;

import com.toranj.tyke.models.Tag;
import com.toranj.tyke.models.User;

import java.util.List;

/**
 * Created by arash on 8/18/16.
 */
public interface RegisterFragmentListener {
    void onStep1Finished(User user);
    void onStep2Finished(User user);
}
