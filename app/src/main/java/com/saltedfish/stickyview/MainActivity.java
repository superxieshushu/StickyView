package com.saltedfish.stickyview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private View mView;
    private FrameLayout mFl;
    private RecyclerView mRv;
    private TabLayout mStickTab;

    private int mStickyPositionY;
    private SingleStickyAdapter mSingleStickyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = findViewById(R.id.rv);
        mFl = findViewById(R.id.fl);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mView = LayoutInflater.from(this).inflate(R.layout.item_single_sticky, mRv, false);
        mSingleStickyAdapter = new SingleStickyAdapter();
        mSingleStickyAdapter.setTabView(mView);
        mRv.setAdapter(mSingleStickyAdapter);
        mRv.addItemDecoration(new SingleDecoration());

        initTab();
        initData();
        initListener();
    }

    private void initData() {
        int statusBarHeight = -1;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = this.getResources().getDimensionPixelSize(resourceId);
        }
        mStickyPositionY = statusBarHeight;
    }

    private void initTab() {
        mStickTab = new TabLayout(this);
        mStickTab.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        Utils.dp2px(this, 44)));
        mStickTab.setTabMode(TabLayout.MODE_FIXED);
        mStickTab.setTabGravity(TabLayout.GRAVITY_FILL);
        mStickTab.setBackground(new ColorDrawable(Color.parseColor("#eaeaea")));
        TabLayout.Tab tab = mStickTab.newTab().setText("tab 1");
        mStickTab.addTab(tab, true);
        tab = mStickTab.newTab().setText("tab 2");
        mStickTab.addTab(tab);
        tab = mStickTab.newTab().setText("tab 3");
        mStickTab.addTab(tab);
        tab = mStickTab.newTab().setText("tab 4");
        mStickTab.addTab(tab);
//        mFl.addView(mStickTab);
    }

    private void initListener() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] location = new int[2];
                mView.getLocationInWindow(location);
                int count = mFl.getChildCount();
                if (location[1] <= mStickyPositionY) {
                    if (count == 1) {
                        mFl.addView(mStickTab);
                    }
                } else {
                    if (count > 1) {
                        mFl.removeView(mStickTab);
                    }
                }
            }
        });
    }
}
