package com.lqcode.lucytv.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.TVItem;

import java.util.List;

/**
 * Created by @vitovalov on 30/9/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<TVItem> mListData;

    public ListAdapter(List<TVItem> mListData) {
        this.mListData = mListData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        TVItem item=mListData.get(i);
        String name=item.getLiveName().replace("cctv","");
        if(name.length()==1)
            name+="  ";
        else if(name.length()==2)
            name+=" ";
        myViewHolder.title.setText(name);
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listitem_name);
        }
    }
}