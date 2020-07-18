package com.example.testecathoapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.testecathoapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

/*
 *  Activity com detalhes de uma sugestão de vaga visualizada (feature extra)
 */
public class JobSuggestionDetailsActivity extends AppCompatActivity
{
    private TextView mTitleTextView;
    private TextView mCompanyTextView;
    private TextView mDateTextView;
    private TextView mPositionsTextView;
    private TextView mSalaryTextView;
    private RecyclerView mLocationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_suggestion_details);

        // Binding dos elementos do layout
        mTitleTextView = findViewById(R.id.activity_job_suggestion_main_text);
        mCompanyTextView = findViewById(R.id.activity_job_suggestion_company_text);
        mDateTextView = findViewById(R.id.activity_job_suggestion_date_text);
        mPositionsTextView = findViewById(R.id.activity_job_suggestion_jobs_positions_text);
        mSalaryTextView = findViewById(R.id.activity_job_suggestion_salary_text);
        mLocationsRecyclerView = findViewById(R.id.activity_job_suggestion_locations_list);
    }
}
