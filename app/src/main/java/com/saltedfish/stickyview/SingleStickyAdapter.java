package com.saltedfish.stickyview;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SaltedFish on 2018/3/11.
 * 单次吸附效果适配器
 */

public class SingleStickyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int STICKY = 10;
    private View mTabView;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == STICKY) {
            return new SingleStickyViewHolder(mTabView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, parent, false);
            return new ItemViewHolder(view);
        }

    }

    public void setTabView(View tabView) {
        mTabView = tabView;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).mTv.setText("" + position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3) {
            return STICKY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    static class SingleStickyViewHolder extends RecyclerView.ViewHolder {
        TabLayout mTabLayout;

        SingleStickyViewHolder(View itemView) {
            super(itemView);
            mTabLayout = itemView.findViewById(R.id.tab);
            mTabLayout.addTab(mTabLayout.newTab().setText("tab 1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("tab 2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("tab 3"));
            mTabLayout.addTab(mTabLayout.newTab().setText("tab 4"));
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

}
