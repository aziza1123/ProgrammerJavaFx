/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aziza.entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.google.gson.JsonObject;
/**
 *
 * @author lenovo
 */
public interface giveawysApi {
    @GET("https://www.gamerpower.com/api/giveaways/")
    Call<JsonObject> getGlobalData();
    
    @GET("https://www.gamerpower.com/api/giveaways?platform={pc}")
    Call<JsonObject> getpcData(@Path(value="pc")String pc);
}