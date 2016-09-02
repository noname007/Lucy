package com.lqcode.lucytv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyKernel;

/**
 * Created by LiQiong on 16/9/1.
 */
public class DesktopActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        RecyclerView recyclerViewMain = (RecyclerView) findViewById(R.id.rv_main);
        recyclerViewMain.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMain.setAdapter(new DesktopAdapder());
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
            holder.itemIV.setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return 6;
        }
    }
}

