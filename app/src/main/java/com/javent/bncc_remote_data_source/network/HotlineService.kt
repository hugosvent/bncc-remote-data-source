package com.javent.bncc_remote_data_source.network;

import com.javent.bncc_remote_data_source.model.LookUpData;
import retrofit2.http.GET

interface HotlineService {
  @GET("https://bncc-corona-versus.firebaseio.com/v1/hotlines.json")
  suspend fun getHotlines() : List<LookUpData>
}
