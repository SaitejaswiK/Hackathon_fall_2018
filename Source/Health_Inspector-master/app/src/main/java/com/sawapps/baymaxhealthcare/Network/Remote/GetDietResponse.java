package com.sawapps.baymaxhealthcare.Network.Remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sawapps.baymaxhealthcare.Network.GetDiet.Meal;
import com.sawapps.baymaxhealthcare.Network.GetDiet.Nutrients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SaiTejaswi Koppuravuri @ BayMaxHealthCare.
 */

public class GetDietResponse {

    @SerializedName("meals")
    @Expose
    public List<Meal> meals = new ArrayList<Meal>();
    @SerializedName("nutrients")
    @Expose
    public Nutrients nutrients;

}
