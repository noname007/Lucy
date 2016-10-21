package com.lqcode.lucytv.fragment;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.activity.DetailsLiveActivity;
import com.lqcode.lucytv.entity.CCTVItem;
import com.lqcode.lucytv.entity.Entity;
import com.lqcode.lucytv.network.CCTVListRequest;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */
public class LiveFragment extends BaseFragment implements OnRecyclerItemClick {
    private TVListAdapter mAdapter;
    private List<CCTVItem> list = new ArrayList<>();
    private SwipeRefreshLayout srl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LiveDataRequestNet();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setHasFixedSize(true);
        LiveDataRequestNet();
        mAdapter = new TVListAdapter(list, this);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    /**
     *
     */
    private void LiveDataRequestNet() {
        list.clear();

        new CCTVListRequest() {
            @Override
            public void _onSuccess(final String result) {
                LucyController.uiHelp.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("LiveFragment","result-----------"+result);
                        List<CCTVItem> cctvItems = JSON.parseArray(result, CCTVItem.class);
                        list.addAll(cctvItems);

                        mAdapter.notifyDataSetChanged();
                        srl.setRefreshing(false);
                    }
                });
            }

            @Override
            public void _onFail(String result) {
                srl.setRefreshing(false);
            }
        };
    }

    @Override
    public void onItemClick(View view, Entity data) {
        Animator animator = ViewAnimationUtils.createCircularReveal(view, 1, 110, 1f, 100f);
        animator.setDuration(1000);
        animator.start();

        TextView liveNameText = (TextView) view.findViewById(R.id.tv_item_icon);
        liveNameText.setTransitionName("CCTVTextView");
        CCTVItem item = (CCTVItem) data;
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("cctv_item", item);
        Intent mIntent = new Intent(getContext(), DetailsLiveActivity.class);
//        mIntent.putExtra("LiveName", item.getC());
        mIntent.putExtras(mBundle);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(), liveNameText, liveNameText.getTransitionName()).toBundle());
    }
}