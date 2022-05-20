package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainHomeRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.databinding.FragmentMainHomeBinding;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.devbase.utils.ScreenUtil;
import com.github.welcomeworld.devbase.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends BaseFragment<FragmentMainHomeBinding> {

    MainHomeRecyclerViewAdapter adapter;
    List<VideoListBean> data=new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MainHomeRecyclerViewAdapter(getActivity());
        getViewBinding().mainHomeRv.setAdapter(adapter);
        getViewBinding().mainHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewBinding().mainHomeSwipeRefresh.setOnRefreshListener(() -> refresh(true));
        getViewBinding().mainHomeSwipeRefresh.setOnLoadListener(this::loadMore);
        refresh(false);
        getViewBinding().mainHomeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        final int offset = getViewBinding().mainHomeRv.computeVerticalScrollOffset();
        final int range = getViewBinding().mainHomeRv.computeVerticalScrollRange() - getViewBinding().mainHomeRv.computeVerticalScrollExtent();
        if(offset>range- footerHeight){
            getViewBinding().mainHomeRv.smoothScrollBy(0,range-offset-footerHeight);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        refresh(false);
    }

    private void refresh(boolean force){
        if((!force&&data!=null&&data.size()>0)){
            return;
        }
        if(getViewBinding().mainHomeSwipeRefresh.isRefreshing()&&!force){
            return;
        }
//        if(force){
//            moreTime = 0;
//        }
        getViewBinding().mainHomeSwipeRefresh.setRefreshing(true);
        ThreadUtil.defer().when(()-> ParserManager.getInstance().refreshRecommend()).done(result -> {
            data = result;
            adapter.replaceAll(data);
            getViewBinding().mainHomeSwipeRefresh.setRefreshing(false);
            if(result!=null&&result.size()>0){
                loadMore();
            }
        });
    }

//    int moreTime = 0;
    private void loadMore(){
//        if(moreTime>=4){
//            return;
//        }
//        moreTime++;
        ThreadUtil.defer().when(()-> ParserManager.getInstance().getMoreRecommend()).done(result -> {
            data.addAll(result);
            adapter.addAll(result);
            getViewBinding().mainHomeSwipeRefresh.setLoading(false);
        });
    }

}
