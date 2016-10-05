package com.lqcode.lucytv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.activity.DetailsAcfunActivity;
import com.lqcode.lucytv.entity.AcfunItem;
import com.lqcode.lucytv.entity.AcfunNetData;
import com.lqcode.lucytv.entity.Entity;
import com.lqcode.lucytv.network.AcfunMovieListRequest;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class AcfunFragment extends BaseFragment implements OnRecyclerItemClick {
    private List<AcfunItem> acfunItems;
    private AcfunListAdapter adapter;
    private SwipeRefreshLayout srl;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAcfunNet();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter = new AcfunListAdapter(acfunItems, this));
        getAcfunNet();
        return view;
    }

    public void getAcfunNet() {
        new AcfunMovieListRequest() {

            @Override
            public void _onSuccess(String result) {
                acfunItems = JSON.parseObject(result, AcfunNetData.class).getData().getHits();
                adapter.notifyDataSetChanged();
                srl.setRefreshing(false);
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }

    @Override
    public void onItemClick(View view, Entity data) {
        AcfunItem item = (AcfunItem) data;
        Intent mIntent = new Intent(getContext(), DetailsAcfunActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("acfun_item", item);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }
}
