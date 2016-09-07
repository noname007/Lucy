package com.lqcode.lucytv.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqiong.lucy.module.impl.LucyController;
import com.liqiong.lucy.module.impl.LucyKernel;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.activity.PlayerActivity;
import com.lqcode.lucytv.entity.CCTVItem;
import com.lqcode.lucytv.entity.TVItem;
import com.lqcode.lucytv.ui.OnRecyclerItemClick;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by @vitovalov on 30/9/15.
 */
public class TVListAdapter extends RecyclerView.Adapter<TVListAdapter.MyViewHolder> {

    private List<CCTVItem> mListData;
    private OnRecyclerItemClick onRecyclerItemClick;

    public TVListAdapter(List<CCTVItem> mListData, OnRecyclerItemClick onRecyclerItemClick) {
        this.mListData = mListData;
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final CCTVItem item = mListData.get(i);
        String shortName = item.getC().replace("cctv", "");
        if (shortName.length() == 1)
            shortName += "  ";
        else if (shortName.length() == 2)
            shortName += " ";
        myViewHolder.title.setText(shortName);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerItemClick.onItemClick(view, item);
            }
        });
        String tvName = item.getN();
        myViewHolder.name.setText(tvName + "");
        String currentTVName = item.getT();
        myViewHolder.currentName.setText(currentTVName);
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView name;
        private TextView currentName;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_item_icon);
            name = (TextView) itemView.findViewById(R.id.tv_item_name);
            currentName = (TextView) itemView.findViewById(R.id.tv_item_current_play_name);
        }
    }
}