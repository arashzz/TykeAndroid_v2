package com.toranj.tyke.models;

import java.util.List;

/**
 * Created by arash on 8/16/16.
 */
public class DashboardTag extends Base {
    String name;
    List<Brand> brands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
