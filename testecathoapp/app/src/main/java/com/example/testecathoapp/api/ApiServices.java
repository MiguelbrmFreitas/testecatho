package com.example.testecathoapp.api;

import android.content.Context;
import android.util.Log;

import com.example.testecathoapp.models.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 *  Classe para as chamadas de API
 */
public class ApiServices
{
    private static String TAG = "ApiServices";
    private static String AUTH_KEY = "1811a58a2bdceefa000c5b4bb1def4e00c8151de2e3fc1180849ce12134d763ad0416a71fd2cd59241a30a225596ecf4020498ad4b9be3b5f9220e852b86defb";
    private String mBaseUrl = "https://teste-catho-api-v2.herokuapp.com";

    public ApiServices (Callback callback) {
        getAuthKey(callback);
    }

    private void getAuthKey(Callback callback) {
        String url = mBaseUrl + "/keys";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }

    public User getUser (String id) {
        String url = mBaseUrl + "/auth/" + id;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.i(TAG, response.message());
            Log.i(TAG, response.body().string());
        } catch (Exception e) {
            Log.i(TAG, "Deu treta");
        }

        return new User("", "", "", "");
    }
}
