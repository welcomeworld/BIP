package com.github.welcomeworld.bangumi.instrumentality.project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Space;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.welcomeworld.bangumi.instrumentality.project.R;
import com.github.welcomeworld.bangumi.instrumentality.project.adapter.SearchResultRecyclerViewAdapter;
import com.github.welcomeworld.bangumi.instrumentality.project.model.VideoListBean;
import com.github.welcomeworld.bangumi.instrumentality.project.parser.ParserManager;
import com.github.welcomeworld.bangumi.instrumentality.project.ui.widget.SwiperefreshContainer;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.ScreenUtil;
import com.github.welcomeworld.bangumi.instrumentality.project.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.top_space)
    Space topSpace;
    @BindView(R.id.search_input)
    EditText searchInputText;
    @BindView(R.id.search_result_swipecontainer)
    SwiperefreshContainer swiperefreshContainer;
    @BindView(R.id.search_result_recyclerview)
    RecyclerView resultRecyclerView;

    private String searchText = "";
    SearchResultRecyclerViewAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        topSpace.getLayoutParams().height = ScreenUtil.getStatusBarHeight(this);
        searchInputText.setOnEditorActionListener((v, actionId, event) -> {
            searchText = searchInputText.getText().toString();
            refresh();
            return false;
        });
        adapter = new SearchResultRecyclerViewAdapter(this);
        resultRecyclerView.setAdapter(adapter);
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swiperefreshContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!StringUtil.isEmpty(searchText)){
                    refresh();
                }
            }
        });
        swiperefreshContainer.setOnLoadListener(new SwiperefreshContainer.OnLoadListener() {
            @Override
            public void onLoad() {
//                load();
            }
        });
    }

    @OnClick({R.id.cancel_button})
    public void onClick(View view){
        finish();
    }

    public void refresh(){
        if(swiperefreshContainer==null||searchText==null||searchText.isEmpty()){
            return;
        }
        swiperefreshContainer.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<VideoListBean> searchData = ParserManager.getInstance().search(searchText,""+1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swiperefreshContainer.setRefreshing(false);
                        adapter.replaceAll(searchData);
                        if(searchData!=null&&searchData.size()>0){
//                            loadMore();
                        }
                    }
                });
            }
        }).start();
    }
}
