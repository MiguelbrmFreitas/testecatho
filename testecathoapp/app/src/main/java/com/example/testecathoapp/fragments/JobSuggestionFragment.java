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
import com.example.testecathoapp.activities.JobSuggestionDetailsActivity;
import com.example.testecathoapp.models.JobSuggestion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *  Fragment com a view da Sugestão de Vaga
 */
public class JobSuggestionFragment extends Fragment
{
    private static String TAG = "JobSuggestionFragment";

    private String mTitle;
    private String mCompany;
    private String mDate;
    private int mTotalPositions;
    private String[] mLocations;
    private String mSalary;

    private TextView mTitleTextView;
    private TextView mCompanyTextView;
    private TextView mDateTextView;
    private TextView mPositionsTextView;
    private TextView mSalaryTextView;
    private Button mSendButton;
    private ImageButton mViewButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recebendo os argumentos
        mTitle = getArguments().getString("title");
        mCompany = getArguments().getString("company");
        mDate = getArguments().getString("date");
        mTotalPositions = getArguments().getInt("positions");
        mLocations = getArguments().getStringArray("locations");
        mSalary = getArguments().getString("salary");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_job_suggestion, container, false);

        // Binding dos elementos do layout
        mTitleTextView = view.findViewById(R.id.fragment_job_suggestion_main_text);
        mCompanyTextView = view.findViewById(R.id.fragment_job_suggestion_company_text);
        mDateTextView = view.findViewById(R.id.fragment_job_suggestion_date_text);
        mPositionsTextView = view.findViewById(R.id.fragment_job_suggestion_jobs_positions_text);
        mSalaryTextView = view.findViewById(R.id.fragment_job_suggestion_salary);
        mSendButton = view.findViewById(R.id.fragment_job_suggestion_send_cv_button);
        mViewButton = view.findViewById(R.id.fragment_job_view_image_button);

        // Setando os valores no layout
        mTitleTextView.setText(mTitle);
        mCompanyTextView.setText(mCompany);
        mDateTextView.setText(mDate);
        mPositionsTextView.setText(getPositionsAndLocationsString(mTotalPositions, mLocations));
        mSalaryTextView.setText(mSalary);

        // Muda o texto do botão após o clique
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSendButton.setText(getResources().getString(R.string.cv_sent));
            }
        });

        // Muda para a Activity com visualização em maior detalhes da vaga
        mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JobSuggestionDetailsActivity.class);

                // Configura os dados a serem passados para a Activity
                Bundle bundle = new Bundle();
                bundle.putString("title", mTitle);
                bundle.putString("company", mCompany);
                bundle.putString("date", mDate);
                bundle.putInt("positions", mTotalPositions);
                bundle.putStringArray("locations", mLocations);
                bundle.putString("salary", mSalary);

                intent.putExtra(JobSuggestionDetailsActivity.KEY_EXTRA, bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    /**
     * Cria uma nova instância do Fragment
     * @param jobSuggestion     Informações para carregar no Fragment
     * @param index             Informação do índice da instância
     * @return
     */
    public static JobSuggestionFragment newInstance(JobSuggestion jobSuggestion, int index) {
        JobSuggestionFragment jobSuggestionFragment = new JobSuggestionFragment();

        // Argumentos para passar para a nova instância do fragment
        Bundle args = new Bundle();
        args.putString("title", jobSuggestion.getJobAdTitle());
        args.putString("company", jobSuggestion.getCompany());
        args.putString("date", jobSuggestion.getDate());
        args.putInt("positions", jobSuggestion.getTotalPositions());
        args.putStringArray("locations", jobSuggestion.getLocations());
        args.putString("salary", jobSuggestion.getSalary());
        args.putInt("index", index);

        jobSuggestionFragment.setArguments(args);

        return jobSuggestionFragment;
    }

    /**
     * Recebe a string formatada para vagas e localização
     * @param positions     Número de vagas
     * @param locations     String com localizações
     * @return
     */
    private String getPositionsAndLocationsString (int positions, String[] locations) {
        String positionsString = "";
        String locationsString = "";

        if (positions > 1) {
            positionsString = positions + " vagas";
        } else {
            positionsString = "1 vaga";
        }

        Log.i(TAG, "Length is " + locations.length);

        if (locations.length > 2) {
            locationsString = locations[0] + " + " + (locations.length - 1) + " cidades";
        } else if (locations.length == 2) {
            locationsString = locations[0] + " + 1 cidade";
        } else if (locations.length == 1 ){
            locationsString = locations[0];
        } else {
            locationsString = "Localização indeterminada";
        }

        return positionsString + " - " + locationsString;
    }
}
