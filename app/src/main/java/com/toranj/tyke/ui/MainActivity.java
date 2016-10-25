package com.toranj.tyke.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.toranj.tyke.R;
import com.toranj.tyke.dagger.components.AppComponent;
import com.toranj.tyke.dagger.components.ComponentProvider;
import com.toranj.tyke.helpers.AccountHelper;
import com.toranj.tyke.ui.fragments.BrandsFragment;
import com.toranj.tyke.ui.fragments.DashboardFragment;
import com.toranj.tyke.ui.fragments.SpendingFragment;
import com.toranj.tyke.ui.fragments.listeners.BrandsFragmentListener;
import com.toranj.tyke.ui.fragments.listeners.DashboardFragmentListener;
import com.toranj.tyke.ui.fragments.listeners.SpendingDialogListener;
import com.toranj.tyke.utility.CurrentUser;


public class MainActivity extends BaseActivity
        implements DashboardFragmentListener, OnNavigationItemSelectedListener ,
        SpendingDialogListener, BrandsFragmentListener{

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    //Fragment
    private final String TAG_DASHBOARD = "dashboard";
    private final String TAG_SPENDING = "spending_code";
    private final String TAG_ACCOUNT = "account";
    private final String TAG_BRANDS = "brands";
    private final String TAG_LOTTERIES = "lotteries";
    private final String TAG_LOGOUT = "logout";
    private String currentTag;
    private String previousTag;
    private boolean isMainTag;
    private boolean shouldLogout;

//    @Inject
    FragmentManager fragmentManager;

    //handles the click event for the floating action button shown in the dashboard
    @Override
    public void onFabClicked() {
        SpendingFragment spendingFragment = SpendingFragment.newInstance("test");
        addFragment(spendingFragment, TAG_SPENDING);
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(R.id.content_frame, spendingFragment);
//        ft.commit();
        //spendingFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppDialogTheme);
        //spendingFragment.show(fragmentManager, TAG_SPENDING);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        currentTag = TAG_DASHBOARD;
        isMainTag = true;
        shouldLogout = false;
        initializeActionNavBar();
        initializeComponents();
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        DashboardFragment dashboardFragment = DashboardFragment.newInstance();
        addFragment(dashboardFragment, TAG_DASHBOARD);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        //outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        //outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    //handles the back button pressed
    @Override
    public void onBackPressed() {
        if(currentTag.equals(TAG_SPENDING)) {
            Fragment fragment = fragmentManager.findFragmentByTag(TAG_SPENDING);
            if(fragment != null) {
                removeFragment(TAG_SPENDING);
            }
        }
        else if(isMainTag && !currentTag.equals(TAG_DASHBOARD)) {
            DashboardFragment dashboardFragment = DashboardFragment.newInstance();
            addFragment(dashboardFragment, TAG_DASHBOARD);
        }
    }

    //to inflate the menu into the toolbar
    //TODO:should be removed later if no usage is declared for it
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    //post create event called to sync the drawer toggle after the onRestoreInstanceState has occurred.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(drawerToggle != null) {
            drawerToggle.syncState();
        }
    }

    //for navigation drawer configuration change even
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    //handles the navigation.
    //Every time a new menu item is selected, the tag and fragment will be assigned respectively
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(!item.isChecked()) {
            item.setChecked(true);
        }

        drawerLayout.closeDrawers();
        String tag = "";
        Fragment fragment = null;
        isMainTag = true;
        switch(item.getItemId()) {
            case R.id.nav_home:
                tag = TAG_DASHBOARD;
                fragment = DashboardFragment.newInstance();
                break;
            case R.id.nav_account:
                tag = TAG_ACCOUNT;
                break;
            case R.id.nav_brands:
                tag = TAG_BRANDS;
                fragment = BrandsFragment.newInstance();
                break;
            case R.id.nav_lotteries:
                tag = TAG_LOTTERIES;
//                fragment = LotterisFragment.newInstance();
                break;
            case R.id.nav_logout:
                tag = TAG_LOGOUT;
                shouldLogout = true;
                break;
        }

        if(!tag.equals(currentTag) && !tag.equals(TAG_LOGOUT)) {
            replaceFragment(fragment, tag);
        }
        return true;
    }

    //when a user cancels the fragment for adding the newly earned points
    @Override
    public void onSpendingDialogCancel() {
        removeFragment(TAG_SPENDING);
    }

    //when a user enters the newly earned points into the system and press send
    @Override
    public void onSpendingDialogSend() {

    }

    //when a brand is selected by user from the dashboard
    @Override
    public void dashboardBrandSelected(String brandId) {
        //TODO: redirect to brand detail fragment
    }

    //when a lottery is selected by user from the dashboard
    @Override
    public void dashboardLotterySelected(String lottery) {
        //TODO: redirect to lottery detail fragment
    }

    //when a spending is selected by user from the dashboard
    //this will be triggered by the expiring points shown in the dashbaord
    @Override
    public void dashboardSpendingSelected() {
        //TODO: redirect to spending detail fragment
    }

    //replaces the input fragment with the current fragment
    //with the respective tag but it will not add it to the backstack
    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(
                android.support.v7.appcompat.R.anim.abc_popup_enter,
                android.support.v7.appcompat.R.anim.abc_popup_exit);
        ft.replace(R.id.content_frame, fragment, tag);
        previousTag = currentTag;
        currentTag = tag;
        ft.commit();
    }

    //adds the input fragment into the container
    //with the respective tag but it will not add it to the backstack
    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(
                android.support.v7.appcompat.R.anim.abc_popup_enter,
                android.support.v7.appcompat.R.anim.abc_popup_exit);
        ft.add(R.id.content_frame, fragment, tag);
        previousTag = currentTag;
        currentTag = tag;
        ft.commit();
    }

    //removes the fragment from the fragmentManager by using its tag
    private void removeFragment(String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        currentTag = previousTag;
        ft.remove(fragmentManager.findFragmentByTag(tag)).commit();
    }

    private void initializeComponents() {
//        componentProvider = DaggerComponentProvider.builder()
//                .networkComponent(((TykeApp)getApplication()).getNetworkComponent())
//                .fragmentModule(new FragmentModule(this))
//                .lotteryModule(new LotteryModule())
//                .spendingModule(new SpendingModule())
//                .build();
//        componentProvider.inject(this);
    }

    private boolean isFragmentAdded(String tag) {
        for(int i = 0;i < fragmentManager.getBackStackEntryCount();i++) {
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
            if(entry.getName() != null && entry.getName().equals(tag)) {
                return true;
            }
        }
        return false;
    }

    //initializes the actionbar + navigation bar in the main activity
    //should be called at the beginning of the activity life cycle
    private void initializeActionNavBar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        setNavigationHeaderInformation();
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar ,  R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if(shouldLogout) {
                    //TODO: confirmation dialog to be popped up
                    //TODO: replace this with injection
                    AccountHelper ah = new AccountHelper(PreferenceManager.getDefaultSharedPreferences(getApplication()));
                    ah.removeUserToken();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setTitle("Arash");
    }

    //populate the navigation header information with the current user
    private void setNavigationHeaderInformation() {
        View headerView = navigationView.inflateHeaderView(R.layout.drawer_header);
        TextView name = (TextView)headerView.findViewById(R.id.profile_name);
        name.setText(CurrentUser.name);
        TextView username = (TextView)headerView.findViewById(R.id.profile_username);
        username.setText(CurrentUser.username);
    }

//    private void setCheckMenuItemForTag(String tag) {
//        Menu menuView = navigationView.getMenu();
//        int itemId = 0;
//        switch (tag) {
//            case TAG_ACCOUNT:
//                itemId = R.id.nav_account;
//                break;
//            case TAG_BRANDS:
//                itemId = R.id.nav_account;
//                break;
//            case TAG_LOTTERIES:
//                itemId = R.id.nav_account;
//                break;
//            case TAG_DASHBOARD:
//                itemId = R.id.nav_account;
//                break;
//        }
//        MenuItem menuItem = menuView.findItem(itemId);
//        if(menuItem != null) {
//            menuItem.setChecked(true);
//        }
//    }


    //when a brand is selected from the brand listing
    @Override
    public void brandSelected(String brandId) {

    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ContentLoadingProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ContentLoadingProgressBar)view.findViewById(R.id.progress_loading);
        }
    }
}
