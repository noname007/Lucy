package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyKernel;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.entity.TVItem;
import com.lqcode.lucytv.network.DesktopRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiQiong on 16/9/1.
 */
public class DesktopActivity extends BaseActivity {
    private List<TVItem> items = new ArrayList<>();
    private DesktopAdapder desktopAdapder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        RecyclerView recyclerViewMain = (RecyclerView) findViewById(R.id.rv_main);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMain.setAdapter(desktopAdapder = new DesktopAdapder());
        desktopNetwork();
    }

    private void desktopNetwork() {
        new DesktopRequest() {
            @Override
            public void _onSuccess(String result) {
                items = JSON.parseArray(result, TVItem.class);
                desktopAdapder.notifyDataSetChanged();
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }

    //=================================================================
    class DesktopHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView itemIV;

        public DesktopHolder(View itemView) {
            super(itemView);
            itemIV = (SimpleDraweeView) itemView.findViewById(R.id.sdv_item);
        }
    }

    class DesktopAdapder extends RecyclerView.Adapter<DesktopHolder> {

        @Override
        public DesktopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DesktopHolder(LayoutInflater.from(LucyKernel.getInstance().getContext())
                    .inflate(R.layout.desktop_item, parent, false));
        }

        @Override
        public void onBindViewHolder(DesktopHolder holder, int position) {
            TVItem item = items.get(position);
            holder.itemIV.setImageURI(item.getPng());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}

