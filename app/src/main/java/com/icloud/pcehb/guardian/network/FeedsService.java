package com.icloud.pcehb.guardian.network;

import com.icloud.pcehb.guardian.model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedsService {
    String BASE_URL = "https://content.guardianapis.com/";

    @GET("search")
    Call<Feed> getAllFeeds(@Query("page") int page, @Query("api-key") String api, @Query("show-fields") String fields, @Query("section") String section);

}
