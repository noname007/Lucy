package com.lqcode.lucytv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liqiong.lucy.module.impl.LucyController;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.activity.DetailsMovieActivity;
import com.lqcode.lucytv.entity.Entity;
import com.lqcode.lucytv.entity.MovieInfo;
import com.lqcode.lucytv.network.MovieListRequest;
import com.lqcode.lucytv.network.MovieSearchRequest;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/4.
 */
public class MovieFragment extends BaseFragment implements OnRecyclerItemClick {
    private List<MovieInfo> data = new ArrayList<>();
    private MovieListAdapter adapter = null;
    private SwipeRefreshLayout srl;
    private int currentPage = 0;

    public MovieFragment() {
        super();
    }

    public MovieFragment(final Button searchBtn, final EditText searchEt) {

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchEt.getText()))
                    getMovieSearchLikeName(searchEt.getText().toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovieListByNet(0);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);
        getMovieListByNet(0);
        recyclerView.setAdapter(adapter = new MovieListAdapter(data, this));
        return view;
    }

    private void getMovieListByNet(int page) {
        currentPage = page;
        if (page == 0)
            data.clear();
        new MovieListRequest(page) {

            @Override
            public void _onSuccess(final String result) {
                LucyController.uiHelp.post(new Runnable() {
                    @Override
                    public void run() {
                        List<MovieInfo> netData = JSON.parseArray(result, MovieInfo.class);
                        data.addAll(netData);
                        adapter.notifyDataSetChanged();
                        srl.setRefreshing(false);
                    }
                });
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }

    private void getMovieSearchLikeName(String likeName) {
        new MovieSearchRequest(likeName) {

            @Override
            public void _onSuccess(final String result) {
                LucyController.uiHelp.post(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        List<MovieInfo> netData = JSON.parseArray(result, MovieInfo.class);
                        data.addAll(netData);
                        adapter.notifyDataSetChanged();
                        srl.setRefreshing(false);
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
        if (view.getId() == R.id.footer_rl) {
            getMovieListByNet(++currentPage);
        } else {
            MovieInfo item = (MovieInfo) data;
            Intent mIntent = new Intent(getContext(), DetailsMovieActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("movie_info", item);
            mIntent.putExtras(mBundle);
            SimpleDraweeView movie_item = (SimpleDraweeView) view.findViewById(R.id.movie_item_poster);
            startActivity(mIntent);
//        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), movie_item, movie_item.getTransitionName()).toBundle());
        }
    }
}