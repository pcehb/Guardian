package com.icloud.pcehb.guardian.ui.fragments;

// Displays Culture News

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.icloud.pcehb.guardian.R;
import com.icloud.pcehb.guardian.network.FeedRepository;
import com.icloud.pcehb.guardian.model.Result;
import com.icloud.pcehb.guardian.ui.activities.DetailsActivity;
import com.icloud.pcehb.guardian.ui.FeedAdapter;
import com.icloud.pcehb.guardian.ui.FeedViewModel;

import java.lang.reflect.Field;
import java.util.List;

public class CultureFragment extends Fragment {
    private RecyclerView feedListView;
    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private FeedAdapter adapter;
    private FeedViewModel viewModel;
    private ProgressBar progressBar;
    private String section = "culture"; // the section query
    private RecyclerView recyclerView;
    private int page = 1;
    private SwipeRefreshLayout swipeContainer;
    private TextView noInternet;

    private final Observer<List<Result>> feedsListObserver = new Observer<List<Result>>(){
        @Override
        public void onChanged(List<Result> Result){
            adapter.updateData(Result);
            swipeContainer.setRefreshing(false);
        }
    };

    private final Observer<List<Result>> loadMoreObserver = new Observer<List<Result>>(){
        @Override
        public void onChanged(List<Result> Result){
            adapter.addData(Result);
        }
    };

    private final Observer<FeedRepository.NetworkStatus> networkStatusObserver = new Observer<FeedRepository.NetworkStatus>(){
        @Override
        public void onChanged(FeedRepository.NetworkStatus networkStatus){
            if(networkStatus == FeedRepository.NetworkStatus.LOADING)
                progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        }
    };

    private final Observer<Integer> selectedObserver = new Observer<Integer>(){
        @Override
        public void onChanged(Integer position){
            if (adapter.articles == null)
                return;

            Result article = adapter.articles.get(position);

            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("article", article); // pass the article object to details activity

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        }
    };

    private View.OnClickListener listItemClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            viewModel.setSelectedFeed(position);
        }


    };

    public CultureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);

        noInternet = view.findViewById(R.id.noInternet);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        //hide loading wheel to allow custom wheel
        try {
            Field f = swipeContainer.getClass().getDeclaredField("mCircleView");
            f.setAccessible(true);
            ImageView img = (ImageView)f.get(swipeContainer);
            img.setAlpha(0.0f);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        adapter = new FeedAdapter(listItemClickListener);

        feedListView = (RecyclerView) view.findViewById(R.id.feed_list_view);
        DividerItemDecoration dividerVert = new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL);
        dividerVert.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        feedListView.addItemDecoration(dividerVert);
        feedListView.setAdapter(adapter);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            feedListView.setLayoutManager(gridLayoutManager);

        } else {
            // In portrait
            feedListView.setLayoutManager(layoutManager);
        }

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(FeedViewModel.class);

        loadFeed();

        recyclerView = (RecyclerView) view.findViewById(R.id.feed_list_view);

        // gets next page of articles when reaches bottom of RV
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    page++;
                    viewModel.getFeedList(page, section).observe((LifecycleOwner) getActivity(), loadMoreObserver);
                }
            }
        });

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                viewModel.getFeedList(page, section).observe((LifecycleOwner) getActivity(), feedsListObserver);
            }
        });


        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getActivity() != null) {
            isNetworkConnected();
        }
    }

    //requests article data
    public void loadFeed(){
        page = 1;
        viewModel.getFeedList(page, section).observe((LifecycleOwner) getActivity(), feedsListObserver);
        viewModel.getNetworkStatus().observe((LifecycleOwner) getActivity(), networkStatusObserver);

        viewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        viewModel.getSelectedFeed().observe(this, selectedObserver);
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null){
            noInternet.setVisibility(View.INVISIBLE);
        }
        else {
            noInternet.setVisibility(View.VISIBLE);
        }

        return cm.getActiveNetworkInfo() != null;
    }

}