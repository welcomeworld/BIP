package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainHomeRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.SwiperefreshContainer;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainHomeFragment extends BaseFragment {
    @BindView(R.id.main_home_rv)
    RecyclerView recyclerView;
    @BindView(R.id.main_home_swipeRefresh)
    SwiperefreshContainer swipeRefreshLayout;

    MainHomeRecyclerViewAdapter adapter;
    List<VideoListBean> data=new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MainHomeRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(recyclerView.getContext(),2,GridLayoutManager.VERTICAL,false);
        gridLayoutManager.setSpanSizeLookup(adapter.getSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(true);
            }
        });
        swipeRefreshLayout.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
                loadMore();
            }
        });
        refresh(false);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState ==SCROLL_STATE_IDLE ){
                    scrollHideBottom();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
    private void scrollHideBottom(){
        Log.e("SwipeRefresh","scrollHide");
        if(getContext() == null){
            return;
        }
        int footerHeight = ScreenUtil.dp2px(getContext(),96);
        final int offset = recyclerView.computeVerticalScrollOffset();
        final int range = recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent();
        if(offset>range- footerHeight){
        recyclerView.smoothScrollBy(0,range-offset-footerHeight);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        refresh(false);
    }

    private void refresh(boolean force){
        if((!force&&data!=null&&data.size()>0)||swipeRefreshLayout ==null){
            return;
        }
        if(swipeRefreshLayout.isRefreshing()&&!force){
            return;
        }
        if(force){
            moreTime = 0;
        }
        swipeRefreshLayout.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<VideoListBean> recommend = ParserManager.getInstance().refreshRecommend();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data = recommend;
                        adapter.replaceAll(data);
                        swipeRefreshLayout.setRefreshing(false);
                        if(recommend!=null&&recommend.size()>0){
                            loadMore();
                        }
                    }
                });
            }
        }).start();
    }

    int moreTime = 0;
    private void loadMore(){
        if(moreTime>=4){
            return;
        }
        moreTime++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<VideoListBean> recommend = ParserManager.getInstance().getMoreRecommend();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data.addAll(recommend);
                        adapter.addAll(recommend);
                        swipeRefreshLayout.setLoading(false);
                    }
                });
            }
        }).start();
    }

}
