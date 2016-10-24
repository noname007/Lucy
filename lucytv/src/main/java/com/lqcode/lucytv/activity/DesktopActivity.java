package com.lqcode.lucytv.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import com.liqiong.lucy.BaseActivity;
import com.liqiong.lucy.module.impl.LucyKernel;
import com.lqcode.lucytv.InitApp;
import com.lqcode.lucytv.R;
import com.lqcode.lucytv.fragment.AcfunFragment;
import com.lqcode.lucytv.fragment.LiveFragment;
import com.lqcode.lucytv.fragment.MovieFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiQiong on 16/9/1.
 */
public class DesktopActivity extends BaseActivity {
    private static final String TAG = DesktopActivity.class.getSimpleName();
    private ImageView ivSearch;
    private EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        InitApp.initialize(this);
        setupToolbar();
        setupCollapsingToolbar();
        setupViewPager();
    }

    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
        ivSearch = (ImageView) findViewById(R.id.search_movie_btn);
        edSearch = (EditText) findViewById(R.id.search_movie_et);
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        ImageView desktopHeader = (ImageView) findViewById(R.id.header);
        setupViewPager(viewPager, desktopHeader);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setCollapsible(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("频道");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupViewPager(ViewPager viewPager, final ImageView desktopHeader) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new LiveFragment(), "直播");
        adapter.addFrag(new MovieFragment(ivSearch, edSearch), "电影");
        adapter.addFrag(new AcfunFragment(), "Acfun");
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e(TAG,"onPageScrolled position-->>"+position);
//                Log.e(TAG,"onPageScrolled positionOffset-->>"+positionOffset);
//                Log.e(TAG,"onPageScrolled positionOffsetPixels-->>"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected position->>" + position);
                if (position == 0) {
                    desktopHeader.setImageDrawable(getResources().getDrawable(R.mipmap.live_cctv));
                    hideSearch();
                } else if (position == 1) {
                    showSearch();
                    desktopHeader.setImageDrawable(getResources().getDrawable(R.mipmap.movie_head_logo_icon));
                } else {
                    hideSearch();
                    desktopHeader.setImageDrawable(getResources().getDrawable(R.mipmap.movie_head_logo_icon));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageSelected state->>" + state);
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void showSearch() {
//        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//        mShowAction.setDuration(500);
        AlphaAnimation mShowAction = new AlphaAnimation(0,1);
        mShowAction.setDuration(500);
        ivSearch.startAnimation(mShowAction);
        edSearch.startAnimation(mShowAction);
        ivSearch.setVisibility(View.VISIBLE);
        edSearch.setVisibility(View.VISIBLE);
    }

    private void hideSearch() {
        AlphaAnimation mHiddenAction = new AlphaAnimation(1,0);
        mHiddenAction.setDuration(500);
        ivSearch.startAnimation(mHiddenAction);
        edSearch.startAnimation(mHiddenAction);
        ivSearch.setVisibility(View.GONE);
        edSearch.setVisibility(View.GONE);
    }
}

