package com.sawapps.baymaxhealthcare.Network.Remote;

import com.sawapps.baymaxhealthcare.Network.Responses.DoctorSearchResponse.DoctorSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by SaiTejaswi Koppuravuri @ BayMaxHealthCare.
 */

public interface Service {


    @GET
    Call<DoctorSearchResponse> searchDoctors(@Url String url);

    @GET
    Call<GetDietResponse> getMeal(@Url String url, @Header("X-Mashape-Key") String s, @Header("X-Mashape-Host") String s2);

}

