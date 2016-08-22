package com.toranj.tyke.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toranj.tyke.R;
import com.toranj.tyke.adapters.DashboardViewPagerAdapter;
import com.toranj.tyke.ui.fragments.listeners.DashboardFragmentListener;

public class DashboardFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private DashboardFragmentListener activityListener;

    private final String TAB_LOTTERIES = "قرعه کشی";
    private final String TAB_BRANDS = "برند";
    private final int TAB_ID_LOTTERIES = 1;
    private final int TAB_ID_BRANDS = 2;

    ViewPager viewPager;
    TabLayout tabLayout;

//    private RecyclerView recyclerView;
//    private DashboardLotteriesAdapter dashboardAdapter;
//    private ComponentProvider componentProvider;
//
//    private SpendingCallback spendingCallback;
//    private LotteryCallback lotteryCallback;

//    @Inject
//    Retrofit retrofit;
//
//    @Inject
//    LotteryApiInterface lotteryApiInterface;
//
//    @Inject
//    SpendingApiInterface spendingApiInterface;

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //go();
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        view.invalidate();
        initializeViewComponents(view);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity != null) {
            try {
                activityListener = (DashboardFragmentListener) activity;
            }
            catch(ClassCastException e) {
                Log.e("DashboardFragment",
                        "The Activity passed is not an Instance of DashboardFragmentListener Interface");
            }

//            initComponents();

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        DashboardViewPagerAdapter viewPagerAdapter = new DashboardViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new DashboardBrandsFragment(), TAB_BRANDS, TAB_ID_BRANDS);
        viewPagerAdapter.addFragment(new DashboardLotteriesFragment(), TAB_LOTTERIES, TAB_ID_LOTTERIES);

        viewPager.setAdapter(viewPagerAdapter);
    }


    //    private void initComponents() {
////        lotteryComponent = DaggerLotteryComponent.builder()
////                .networkComponent(((TykeApp)getActivity().getApplication()).getNetworkComponent())
////                .lotteryModule(new LotteryModule())
////                .build();
////        lotteryComponent.inject(this);
////        componentProvider = DaggerComponentProvider.builder()
////                .networkComponent(((TykeApp)getActivity().getApplication()).getNetworkComponent())
////                .fragmentModule(new FragmentModule((MainActivity)getActivity()))
////                .lotteryModule(new LotteryModule())
////                .spendingModule(new SpendingModule())
////                .build();
////        componentProvider.inject(this);
//    }

    private void initializeViewComponents(View view) {

        viewPager = (ViewPager)view.findViewById(R.id.pager);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        //RecyclerView initialization
//        recyclerView = (RecyclerView)view.findViewById(R.id.list_lotteries);
//
//        //Adapter for RecyclerView
//        dashboardAdapter = new DashboardLotteriesAdapter(new ArrayList<Object>());
//        recyclerView.setAdapter(dashboardAdapter);
//
//        //final GridLayoutManager lm = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
//        final LinearLayoutManager lm = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(lm);
//
//
//        List<Object> result = new ArrayList<>();
//        Spending spending = new Spending();
//        spending.setAmount(23000);
//        result.add(spending);
//        Lottery lottery1 = new Lottery();
//        lottery1.setName("روز پدر");
//        result.add(lottery1);
//        Lottery lottery2 = new Lottery();
//        lottery2.setName("شب نشینی");
//        result.add(lottery2);
//        Lottery lottery3 = new Lottery();
//        lottery3.setName("روز مادر");
//        result.add(lottery3);
//        Lottery lottery = new Lottery();
//        lottery.setName("عید نوروز");
//        result.add(lottery);
//        Lottery lottery5 = new Lottery();
//        lottery5.setName("ماه رمدون ۲");
//        result.add(lottery5);
//        Lottery lottery6 = new Lottery();
//        lottery6.setName("الکی پلکی");
//        result.add(lottery6);
//        Lottery lottery7 = new Lottery();
//        lottery7.setName("پول زور بده");
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//        result.add(lottery7);
//
//        dashboardAdapter.addItems(result);


        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.dashboard_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.onFabClicked();
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

//    private void populateLotteries(List<Lottery> lotteris) {
//
//        dashboardAdapter.addItems(new ArrayList<Object>(lotteris));
//    }

//    private void populateSpending(Spending spending) {
//        dashboardAdapter.addItem(spending);
//    }

//    private void go() {
//
////        Call<List<Lottery>> lotteryListCall = lotteryApiInterface.getByCriteria("jermin");
////        lotteryListCall.enqueue(new TykeCallback<List<Lottery>>(new LotteryCallback()));
////
////        Call<Spending> expiredSpendingCall = spendingApiInterface.getExpired("arash.moeen");
////        expiredSpendingCall.enqueue(new TykeCallback<Spending>(new SpendingCallback()));
//
//        //Call<Spending> call =
////        Call<List<Lottery>> call = lotteryApiInterface.getByCriteria("jermin");
////        call.enqueue(new TykeCallback<List<Lottery>>(this));
//        //listener.go();
//    }

//    public class SpendingCallback implements TykeCallbackResponse<Spending> {
//        @Override
//        public void onResponse(Spending result) {
//            populateSpending(result);
//        }
//
//        @Override
//        public void onFailure() {
//
//        }
//    }


//    public class LotteryCallback implements TykeCallbackResponse<List<Lottery>> {
//        @Override
//        public void onResponse(List<Lottery> result) {
//            populateLotteries(result);
//        }
//
//        @Override
//        public void onFailure() {
//
//        }
//    }
}
