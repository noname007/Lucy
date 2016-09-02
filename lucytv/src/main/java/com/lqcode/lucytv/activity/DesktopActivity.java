package com.lqcode.lucytv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyController;
import com.liqiong.lucy.module.impl.LucyKernel;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.Tools.ImageTool;
import com.lqcode.lucytv.Tools.JsonTool;
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
        recyclerViewMain.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewMain.setAdapter(desktopAdapder = new DesktopAdapder());
        desktopNetwork();
    }

    private void desktopNetwork() {
        new DesktopRequest() {
            @Override
            public void _onSuccess(String result) {
                items = JSON.parseArray(result, TVItem.class);
//                items = (ArrayList<TVItem>) JsonTool.objectToEntity(result);
//                LucyController.uiHelp.toast(items.toString());
                desktopAdapder.notifyDataSetChanged();
            }

            @Override
            public void _onFail(String result) {

            }
        };
    }

    //=================================================================
    class DesktopHolder extends RecyclerView.ViewHolder {
        private ImageView itemIV;

        public DesktopHolder(View itemView) {
            super(itemView);
            itemIV = (ImageView) itemView.findViewById(R.id.iv_desktop_item);
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
//            holder.itemIV.setImageResource(R.mipmap.ic_launcher);
            TVItem item = items.get(position);
            ImageTool.imageLoader.displayImage(item.getPng(), holder.itemIV);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}

