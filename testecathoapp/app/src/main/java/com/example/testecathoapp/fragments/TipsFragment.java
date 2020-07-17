package com.example.testecathoapp.fragments;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 *  Fragment com a view da Dica
 */
public class TipsFragment extends Fragment implements Callback
{

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

    }
}
