package com.example.testecathoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.testecathoapp.R;
import com.example.testecathoapp.models.Tip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *  Fragment com a view da Dica
 */
public class TipsFragment extends Fragment
{
    private static String TAG = "TipsFragment";

    private TextView mDescriptionTextView;
    private Button mButton;
    private ImageButton mThumbsUp;
    private ImageButton mThumbsDown;

    private String mDescription;
    private String mLabel;
    private String mUrl;
    private boolean mShowButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recebendo os argumentos
        mDescription = getArguments().getString("description");
        mLabel = getArguments().getString("label");
        mUrl = getArguments().getString("url");
        mShowButton = getArguments().getBoolean("show");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tip, container, false);

        // Binding dos elementos do layout
        mDescriptionTextView = view.findViewById(R.id.fragment_tip_description_text);
        mButton = view.findViewById(R.id.fragment_tip_button);
        mThumbsUp = view.findViewById(R.id.fragment_tip_thumbs_up_image);
        mThumbsDown = view.findViewById(R.id.fragment_tip_thumbs_down_image);

        // Setando os valores no layout
        mDescriptionTextView.setText(mDescription);
        if (mShowButton) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(mLabel);
        } else {
            mButton.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    /**
     * Cria uma nova instância do TipsFragment
     * @param tip       Objeto com instância de uma dica
     * @param index     Índice da instância
     * @return
     */
    public static TipsFragment newInstance(Tip tip, int index) {
        // Configurando os argumentos a serem enviados para o fragment
        Bundle args = new Bundle();
        args.putString("description", tip.getDescription());
        args.putString("label", tip.getButtonLabel());
        args.putString("url", tip.getUrl());
        args.putBoolean("show", tip.isToShowButton());

        TipsFragment fragment = new TipsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
