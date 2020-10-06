package com.github.welcomeworld.bangumi.instrumentality.project.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.MainHomeRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.DynamicHeaderInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.DynamicParameterInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.FixedHeaderInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.FixedParameterInterceptor;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.BaseUrl;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.IndexNetAPI;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.IndexRecommendBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.net.retrofit.IndexRecommendDataBean;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.SwiperefreshContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        GridLayoutManager gridLayoutManager=new GridLayoutManager(recyclerView.getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MainHomeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
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

    private void loadMore(){
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
