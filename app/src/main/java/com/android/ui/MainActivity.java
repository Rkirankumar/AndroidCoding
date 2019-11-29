package com.android.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.R;
import com.android.model.Facts;
import com.android.utils.SharePreferenceUtils;
import com.android.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefresh;
    private MainViewModel mainViewModel;

    FactsAdapter mBlogAdapter;

    Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharePreferenceUtils.init(getApplicationContext());

        mtoolbar=findViewById(R.id.toolbar_actionbar);
        //setting the title
        mtoolbar.setTitle("TITLE");
        //placing toolbar in place of actionbar

        setSupportActionBar(mtoolbar);

        initializationViews();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getPopularBlog();

        swipeRefresh.setOnRefreshListener(() -> {
            getPopularBlog();
        });
    }

    private void initializationViews() {
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.blogRecyclerView);
    }

    public void getPopularBlog() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllBlog().observe(this, new Observer<List<Facts>>() {
            @Override
            public void onChanged(@Nullable List<Facts> blogList) {
                swipeRefresh.setRefreshing(false);
                prepareRecyclerView(blogList);
                mtoolbar.setTitle(SharePreferenceUtils.read(SharePreferenceUtils.TITLE, null));
                ;
            }
        });
    }

    private void prepareRecyclerView(List<Facts> blogList) {
        mBlogAdapter = new FactsAdapter(blogList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);
        mBlogAdapter.notifyDataSetChanged();

    }

}
