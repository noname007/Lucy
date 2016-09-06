package com.lqcode.lucytv.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.TVItem;
import com.lqcode.lucytv.network.LiveRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */
public class LiveFragment extends BaseFragment {
    private ListAdapter mAdapter;
    private List<TVItem> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(
                R.id.fragment_list_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        LiveDataRequetNet();
        mAdapter = new ListAdapter(list);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    /**
     *
     */
    private void LiveDataRequetNet() {
        new LiveRequest() {
            @Override
            public void _onSuccess(final String result) {
                LucyController.uiHelp.post(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(JSON.parseArray(result, TVItem.class));
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void _onFail(String result) {
                LucyController.uiHelp.toast("fail!");
            }
        };
    }
}
