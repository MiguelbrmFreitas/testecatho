package com.example.testecathoapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.example.testecathoapp.R;
import com.example.testecathoapp.api.ApiServices;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/*
*   Activity principal
*/
public class MainActivity extends AppCompatActivity implements Callback {

    private static String TAG = "MainActivity";

    private ApiServices mApiServices;
    private static String USER_ID = "ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc"; // Alternativa: "cc3431c3-d9c9-48e2-8a1f-c3c0260f0712"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiServices = new ApiServices(this);
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.i(TAG, "Fail");
        e.printStackTrace();
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        String responseBody = response.body().string();
        Log.i(TAG, responseBody);
        if (response.isSuccessful()) {
            try {
                JSONObject jsonObject = new JSONObject(responseBody);
                Log.i(TAG, "auth key is " + jsonObject.getString("auth"));
                Log.i(TAG, "Tips key is " + jsonObject.getString("tips"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, response.message());
            Log.i(TAG, "Error");
        }
    }
}
