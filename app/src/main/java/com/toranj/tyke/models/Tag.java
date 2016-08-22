package com.toranj.tyke.models;

/**
 * Created by arash on 8/18/16.
 */
public class Tag extends Base {

    String name;
    String displayName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
