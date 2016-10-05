package com.lqcode.lucytv.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.AcfunItem;
import com.lqcode.lucytv.entity.MovieInfo;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import java.util.List;

/**
 *
 */
public class AcfunListAdapter extends RecyclerView.Adapter<AcfunListAdapter.MyViewHolder> {

    private List<AcfunItem> mListData;
    private OnRecyclerItemClick onRecyclerItemClick;

    public AcfunListAdapter(List<AcfunItem> mListData, OnRecyclerItemClick onRecyclerItemClick) {
        this.mListData = mListData;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.acfun_movie_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final AcfunItem item = mListData.get(i);
        myViewHolder.movieName.setText(item.getTitle());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClick.onItemClick(view, item);
            }
        });
        myViewHolder.moviePoster.setImageURI(item.getImage());
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