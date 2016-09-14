package com.lqcode.lucytv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.activity.DetailsLiveActivity;
import com.lqcode.lucytv.activity.DetailsMovieActivity;
import com.lqcode.lucytv.entity.Entity;
import com.lqcode.lucytv.entity.MovieItem;
import com.lqcode.lucytv.network.MovieListRequest;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */
public class MovieFragment extends BaseFragment implements OnRecyclerItemClick {
    private List<MovieItem> data = new ArrayList<>();
    private MovieListAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        getMovieListByNet();
        recyclerView.setAdapter(adapter = new MovieListAdapter(data, this));
        return view;
    }

    private void getMovieListByNet() {
        new MovieListRequest() {

            @Override
            public void _onSuccess(final String result) {
                LucyController.uiHelp.post(new Runnable() {
                    @Override
                    public void run() {
                        List<MovieItem> netData = JSON.parseArray(result, MovieItem.class);
                        data.addAll(netData);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }

    @Override
    public void onItemClick(View view, Entity data) {
        MovieItem item= (MovieItem) data;
        Intent mIntent = new Intent(getContext(), DetailsMovieActivity.class);
        mIntent.putExtra("id",item.getId());
        startActivity(mIntent);
    }
}
