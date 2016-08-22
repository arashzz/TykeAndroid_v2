package com.toranj.tyke.ui.fragments.listeners;

/**
 * Created by arash on 7/28/16.
 */
public interface DashboardFragmentListener {
    void dashboardBrandSelected(String brandId);
    void dashboardLotterySelected(String lottery);
    void dashboardSpendingSelected();
    void onFabClicked();
}
