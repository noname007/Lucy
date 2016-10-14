package com.lqcode.lucytv.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.Entity;
import com.lqcode.lucytv.entity.MovieInfo;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import org.w3c.dom.Text;

import java.util.List;

/**
 *
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private static final int TYPE_ITEM = 0, TYPE_FOOTER = 1;
    private List<MovieInfo> mListData;
    private OnRecyclerItemClick onRecyclerItemClick;

    public MovieListAdapter(List<MovieInfo> mListData, OnRecyclerItemClick onRecyclerItemClick) {
        this.mListData = mListData;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_footer, viewGroup, false);
            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        int itemType = getItemViewType(i);
        if (itemType == TYPE_ITEM) {
            final MovieInfo item = mListData.get(i);
            myViewHolder.movieName.setText(item.getName());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClick.onItemClick(view, item);
                }
            });
            myViewHolder.moviePoster.setImageURI(item.getSmallImg());
        } else if (itemType == TYPE_FOOTER) {
            ((FooterViewHolder) myViewHolder).footer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClick.onItemClick(view, new Entity());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListData != null && mListData.size() == 0)
            return 0;
        return mListData == null ? 0 : mListData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1 == getItemCount())
            return TYPE_FOOTER;
        return TYPE_ITEM;
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

    class FooterViewHolder extends MyViewHolder {
        private RelativeLayout footer;

        public FooterViewHolder(View itemView) {
            super(itemView);
            footer = (RelativeLayout) itemView.findViewById(R.id.footer_rl);
        }
    }
}