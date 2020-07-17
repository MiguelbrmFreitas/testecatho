package com.example.testecathoapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.example.testecathoapp.R;
import com.example.testecathoapp.api.ApiServices;
import com.example.testecathoapp.models.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/*
*   Activity principal
*/
public class MainActivity extends AppCompatActivity implements Callback, ApiServices.RequestCompleted {

    private static String TAG = "MainActivity";

    private ApiServices mApiServices;
    private static String USER_ID = "ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc"; // Alternativa: "cc3431c3-d9c9-48e2-8a1f-c3c0260f0712"

    private Handler mHandler;

    private TextView mGreetingsTextView;
    private CircleImageView mProfilePicture;
    private ProgressBar mSpinner;
    LinearLayout mWrapper;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding dos widgets/componentes
        mSpinner = findViewById(R.id.activity_main_progress_bar);
        mGreetingsTextView = findViewById(R.id.activity_main_greetings_tv);
        mProfilePicture =  findViewById(R.id.activity_main_profile_picture);
        mWrapper = findViewById(R.id.activity_main_linear_layout_wrapper);

        mHandler = new Handler(Looper.getMainLooper());

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
                Log.i(TAG, "Json Object is " + jsonObject.toString());
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String token = jsonObject.getString("token");
                String photo = jsonObject.getString("photo");
                mUser = new User(id, name, token, photo);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mGreetingsTextView.setText(Html.fromHtml(getString(R.string.greetings_user, mUser.getName())));
                        Drawable photo = mApiServices.getPhotoDrawable(mUser.getPhotoRef(), getApplicationContext());
                        mProfilePicture.setImageDrawable(photo);
                        mSpinner.setVisibility(View.GONE);
                        mWrapper.setVisibility(View.VISIBLE);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, response.message());
            Log.i(TAG, "Error");
        }
    }

    @Override
    public void onRequestCompleted() {
        // Chamar o método de getUser só quando a primeira request acabar
        mApiServices.getUser(this, USER_ID);
    }
}
