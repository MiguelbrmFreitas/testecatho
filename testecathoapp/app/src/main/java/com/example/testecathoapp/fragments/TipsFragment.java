package com.example.testecathoapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.testecathoapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *  Fragment com a view da Dica
 */
public class TipsFragment extends Fragment
{

    private TextView mTipTextView;
    private Button mButton;
    private ImageButton mThumbsUp;
    private ImageButton mThumbsDown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recebendo os argumentos
        Log.i("Fragment", "create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tip, container, false);

        // Binding dos elementos do layout
        mTipTextView = view.findViewById(R.id.fragment_tip_text);
        mButton = view.findViewById(R.id.fragment_tip_button);
        mThumbsUp = view.findViewById(R.id.fragment_tip_thumbs_up_image);
        mThumbsDown = view.findViewById(R.id.fragment_tip_thumbs_down_image);

        // Setando os valores no layout
        Log.i("Fragment", "Cheguei aqui");

        return view;
    }

    public static TipsFragment newInstance() {
        Bundle args = new Bundle();

        TipsFragment fragment = new TipsFragment();
        fragment.setArguments(args);

        Log.i("Fragment", "e ae, parceiro");

        return fragment;
    }
}
