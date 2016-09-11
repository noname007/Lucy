package com.lqcode.lucytv.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.CCTVItem;
import com.lqcode.lucytv.entity.MovieItem;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.List;

/**
 *
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private List<MovieItem> mListData;
    private OnRecyclerItemClick onRecyclerItemClick;

    public MovieListAdapter(List<MovieItem> mListData, OnRecyclerItemClick onRecyclerItemClick) {
        this.mListData = mListData;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movice_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
//        final CCTVItem item = mListData.get(i);
//        String shortName = item.getC().replace("cctv", "");
//        if (shortName.length() == 1)
//            shortName += "  ";
//        else if (shortName.length() == 2)
//            shortName += " ";
//        myViewHolder.title.setText(shortName);
//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onRecyclerItemClick.onItemClick(view, item);
//            }
//        });
//        String tvName = item.getN();
//        myViewHolder.name.setText(tvName + "");
//        String currentTVName = item.getT();
//        myViewHolder.currentName.setText(currentTVName);
        final MovieItem item = mListData.get(i);
        myViewHolder.movieName.setText(item.getName());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClick.onItemClick(view, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView movieName;

        public MyViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movie_name_tv);
        }
    }
}