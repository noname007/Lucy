package com.lqcode.lucytv.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.MovieInfo;
import com.lqcode.lucytv.entity.MovieItem;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.List;

/**
 *
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private List<MovieInfo> mListData;
    private OnRecyclerItemClick onRecyclerItemClick;

    public MovieListAdapter(List<MovieInfo> mListData, OnRecyclerItemClick onRecyclerItemClick) {
        this.mListData = mListData;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final MovieInfo item = mListData.get(i);
        myViewHolder.movieName.setText(item.getName());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClick.onItemClick(view, item);
            }
        });
        myViewHolder.moviePoster.setImageURI(item.getSmallImg());
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView movieName;
        private SimpleDraweeView moviePoster;

        public MyViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movie_name_tv);
            moviePoster = (SimpleDraweeView) itemView.findViewById(R.id.movie_item_poster);
        }
    }
}