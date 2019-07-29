package com.icloud.pcehb.guardian.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.icloud.pcehb.guardian.network.FeedRepository;
import com.icloud.pcehb.guardian.model.Result;

import java.util.List;

public class FeedViewModel extends ViewModel {

    private LiveData<List<Result>> feedList;

    private MutableLiveData<Integer> selectedFeed= new MutableLiveData<Integer>();

    public MutableLiveData<Integer> getSelectedFeed() {
        return selectedFeed;
    }

    public void setSelectedFeed(int position) {
        selectedFeed.setValue(position);
    }

    public LiveData<List<Result>> getFeedList(int page, String section){
        feedList = FeedRepository.getInstance().getFeedList(page, section);
        return feedList;
    }

    public LiveData<FeedRepository.NetworkStatus> getNetworkStatus(){
        return FeedRepository.getInstance().getNetworkStatus();
    }
}
