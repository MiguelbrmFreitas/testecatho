package com.example.testecathoapp.api;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.testecathoapp.R;
import com.example.testecathoapp.models.Keys;
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
    private Keys mKeys;
    private User mUser;
    private RequestCompleted mRequestCompleted;

    public ApiServices (RequestCompleted requestCompleted) {
        getKeys();
        mRequestCompleted = requestCompleted;
    }

    /**
     * Método para receber as keys de autenticação da API
     */
    private void getKeys() {
        String url = mBaseUrl + "/keys";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseBody = response.body().string();
                Log.i(TAG, responseBody);
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String authKey = jsonObject.getString("auth");
                        String tipsKey = jsonObject.getString("tips");
                        String suggestionKey = jsonObject.getString("suggestion");
                        String surveyKey = jsonObject.getString("survey");

                        mKeys = new Keys(authKey, tipsKey, suggestionKey, surveyKey);

                        Log.i(TAG, "Auth key is " + mKeys.getAuth());
                        Log.i(TAG, "Tips key is " + mKeys.getTips());
                        Log.i(TAG, "Suggestion key is " + mKeys.getSuggestion());
                        Log.i(TAG, "Survey key is " + mKeys.getSurvey());

                        mRequestCompleted.onKeysRequestCompleted();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i(TAG, response.message());
                    Log.i(TAG, "Error");
                }
            }
        });

        //client.newCall(request).enqueue(callback);
    }

    /**
     * Método para receber um usuário da API
     * @param id        Id do usuário
     */
    public void getUser (String id) {
        String url = mBaseUrl + "/auth/" + id;

        OkHttpClient client = new OkHttpClient();

        if (mKeys.getAuth() != null) {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("x-api-key", mKeys.getAuth())
                    .build();

            // Chama o callback com a resposta
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    mRequestCompleted.onUserRequestCompleted(call, response);
                }
            });
        } else {
            Log.i(TAG, "Chave de autenticação nula");
        }
    }

    /**
     * Método para fazer a chamada para /suggestions na API, retornando as sugestões de vagas disponíveis
     * @param userToken     Token de autenticação do usuário
     */
    public void getJobSuggestions(String userToken) {
        String url = mBaseUrl + "/suggestion";

        OkHttpClient client = new OkHttpClient();

        if (mKeys.getSuggestion() != null) {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("x-api-key", mKeys.getSuggestion())
                    .addHeader("Authorization", userToken)
                    .build();

            // Chama o callback com a resposta
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    mRequestCompleted.onSuggestionsRequestCompleted(call, response);
                }
            });
        } else {
            Log.i(TAG, "Chave de autenticação nula");
        }
    }

    public void getTips() {
        String url = mBaseUrl + "/tips";

        OkHttpClient client = new OkHttpClient();

        if (mKeys.getTips() != null) {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("x-api-key", mKeys.getTips())
                    .build();

            // Chama o callback com a resposta
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    mRequestCompleted.onTipsRequestCompleted(call, response);
                }
            });
        } else {
            Log.i(TAG, "Chave de autenticação nula");
        }
    }

    /**
     * Método para mapear e retornar a foto correta do usuário
     * @param photoRef  Referência da foto (nome do arquivo)
     * @param context   Context da aplicação
     * @return          Drawable da foto de perfil
     */
    public Drawable getPhotoDrawable(String photoRef, Context context) {
        Drawable photoDrawable;
        Log.i(TAG, photoRef);
        if (photoRef.equals("/assets/ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc.png")) {
            photoDrawable = context.getDrawable(R.drawable.user1);
        } else {
            photoDrawable = context.getDrawable(R.drawable.user2);
        }

        return photoDrawable;
    }

    /**
     * Interface para definir o comportamento e setar um callback de quando uma request é completada
     */
    public interface RequestCompleted {
        void onKeysRequestCompleted();
        void onUserRequestCompleted(Call call, Response response);
        void onSuggestionsRequestCompleted(Call call, Response response);
        void onTipsRequestCompleted(Call call, Response response);
    }
}
