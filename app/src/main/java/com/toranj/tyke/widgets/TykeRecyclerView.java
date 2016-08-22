package com.toranj.tyke.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.toranj.tyke.widgets.interfaces.ITykeRecyclerView;

/**
 * Created by arash on 8/15/16.
 */
public class TykeRecyclerView extends RecyclerView {

    private ITykeRecyclerView widgetListener;
    private boolean loading = true;
    private int pastVisiblesItems;
    private int visibleItemCount;
    private int totalItemCount;

    public TykeRecyclerView(Context context) {
        super(context);
    }

    public TykeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TykeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setWidgetListener(ITykeRecyclerView listener) {
        widgetListener = listener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
//        if(dy > 0) {
//            visibleItemCount = getLayoutManager().getChildCount();
//            totalItemCount = getLayoutManager().getItemCount();
//            pastVisiblesItems = getLayoutManager().findFirstVisibleItemPosition();
//
//            if (loading)
//            {
//                if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
//                {
//                    loading = false;
//                    Log.v("...", "Last Item Wow !");
//                    //Do pagination.. i.e. fetch new data
//                }
//            }
//        }
    }
}
