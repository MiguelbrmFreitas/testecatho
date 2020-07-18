package com.example.testecathoapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.testecathoapp.R;
import com.example.testecathoapp.adapters.LocationsAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 *  Activity com detalhes de uma sugestão de vaga visualizada (feature extra)
 */
public class JobSuggestionDetailsActivity extends AppCompatActivity
{
    public static final String KEY_EXTRA = "JOB_SUGGESTION_ACTIVITY";

    private String mTitle;
    private String mCompany;
    private String mDate;
    private int mTotalPositions;
    private String[] mLocations;
    private String mSalary;

    private Toolbar mToolbar;
    private TextView mTitleTextView;
    private TextView mCompanyTextView;
    private TextView mDateTextView;
    private TextView mPositionsTextView;
    private TextView mSalaryTextView;
    private RecyclerView mLocationsRecyclerView;

    private LocationsAdapter mLocationsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_suggestion_details);

        // Binding dos elementos do layout
        mToolbar = findViewById(R.id.activity_job_suggestion_toolbar);
        mTitleTextView = findViewById(R.id.activity_job_suggestion_main_text);
        mCompanyTextView = findViewById(R.id.activity_job_suggestion_company_text);
        mDateTextView = findViewById(R.id.activity_job_suggestion_date_text);
        mPositionsTextView = findViewById(R.id.activity_job_suggestion_jobs_positions_text);
        mSalaryTextView = findViewById(R.id.activity_job_suggestion_salary_text);
        mLocationsRecyclerView = findViewById(R.id.activity_job_suggestion_locations_list);

        if (getIntent().hasExtra(KEY_EXTRA)) {
            // Recebendo os argumentos
            Bundle bundle = getIntent().getBundleExtra(KEY_EXTRA);
            mTitle = bundle.getString("title");
            mCompany = bundle.getString("company");
            mDate = bundle.getString("date");
            mTotalPositions = bundle.getInt("positions", 1);
            mLocations = bundle.getStringArray("locations");
            mSalary = bundle.getString("salary");

            // Configurando o adapter de localizações e o layout manager
            mLocationsAdapter = new LocationsAdapter(mLocations, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setStackFromEnd(true);
            layoutManager.setReverseLayout(false);
            mLocationsRecyclerView.setLayoutManager(layoutManager);
            mLocationsRecyclerView.setAdapter(mLocationsAdapter);

            // Setando os valores dos elementos do layout
            mTitleTextView.setText(mTitle);
            mCompanyTextView.setText(mCompany);
            mDateTextView.setText(mDate);
            mPositionsTextView.setText("" + mTotalPositions);
            mSalaryTextView.setText(mSalary);

            // Configurando a toolbar
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Detalhes da vaga");
            mToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24);
            mToolbar.setTitleTextColor(getApplicationContext().getColor(R.color.textColor));

            // Listener para voltar a tela quando clicar no botão de navegação
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } else {
            throw new IllegalArgumentException("Couldn't retrieve data");
        }
    }
}
