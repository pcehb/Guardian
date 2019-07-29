package com.icloud.pcehb.guardian.network;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.icloud.pcehb.guardian.model.Feed;
import com.icloud.pcehb.guardian.model.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class FeedRepository {

    private Retrofit retrofit;
    private FeedsService feedsService;

    private static final FeedRepository ourInstance = new FeedRepository();

    public static FeedRepository getInstance() {
        return ourInstance;
    }


    public enum NetworkStatus {
        IDLE, LOADING
    }
    private MutableLiveData<NetworkStatus> networkStatus = new MutableLiveData<>();

    public LiveData<NetworkStatus> getNetworkStatus(){
        return networkStatus;
    }

    private FeedRepository() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(FeedsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        feedsService = retrofit.create(FeedsService.class);
        networkStatus.setValue(NetworkStatus.IDLE);

    }

    // Retrieve list of feed articles
    public LiveData<List<Result>> getFeedList(int page, String section) {
        final MutableLiveData<List<Result>> articles = new MutableLiveData<>();

        // Get the HTTP call
        Call<Feed> call = feedsService.getAllFeeds( page,"62b0063c-a565-4648-b782-7555a22c34b1", "all", section);
        // Initiate network call in the background
        networkStatus.setValue(NetworkStatus.LOADING);

        call.enqueue(new Callback<Feed>() {

            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()){
                    Log.e("Success", new Gson().toJson(response.body()));
                    articles.setValue(response.body().getResponse().getResults());
                    networkStatus.setValue(NetworkStatus.IDLE);
                }
                else
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));
                networkStatus.setValue(NetworkStatus.IDLE);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
        return articles;
    }

}
