package com.example.testecathoapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.example.testecathoapp.R;
import com.example.testecathoapp.adapters.JobsViewPagerAdapter;
import com.example.testecathoapp.api.ApiServices;
import com.example.testecathoapp.containers.PagerContainer;
import com.example.testecathoapp.models.JobSuggestion;
import com.example.testecathoapp.models.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/*
*   Activity principal
*/
public class MainActivity extends AppCompatActivity implements ApiServices.RequestCompleted {

    private static String TAG = "MainActivity";

    private ApiServices mApiServices;
    private static String USER_ID = "ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc"; // Alternativa: "cc3431c3-d9c9-48e2-8a1f-c3c0260f0712"

    private Handler mHandler;

    private TextView mGreetingsTextView;
    private CircleImageView mProfilePicture;
    private ProgressBar mSpinner;
    private LinearLayout mWrapper;
    private ViewPager mViewPager;
    PagerContainer mContainer;

    private User mUser;
    private JobSuggestion[] mJobSuggestionList;

    private ImageView[] mDots;
    private int mDotsCount;
    private LinearLayout mDotsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding dos elementos do layout
        mSpinner = findViewById(R.id.activity_main_progress_bar);
        mGreetingsTextView = findViewById(R.id.activity_main_greetings_tv);
        mProfilePicture =  findViewById(R.id.activity_main_profile_picture);
        mWrapper = findViewById(R.id.activity_main_linear_layout_wrapper);
        mDotsLayout = findViewById(R.id.activity_main_layout_dots);

        // Inicializa o PageContainer e o ViewPager
        mContainer = findViewById(R.id.activity_main_pager_container);
        mViewPager = mContainer.getViewPager();



        // Inicializa o Handler para as threads
        mHandler = new Handler(Looper.getMainLooper());

        // Inicializa o objeto para chamadas de API
        mApiServices = new ApiServices(this);
    }

    @Override
    public void onKeysRequestCompleted() {
        // Chamar o método de getUser só quando a primeira request acabar
        mApiServices.getUser(USER_ID);
    }

    @Override
    public void onUserRequestCompleted(Call call, Response response) {
        try {
            String responseBody = response.body().string();
            Log.i(TAG, responseBody);
            if (response.isSuccessful()) {
                JSONObject jsonObject = new JSONObject(responseBody);
                Log.i(TAG, "Json Object is " + jsonObject.toString());
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String token = jsonObject.getString("token");
                String photo = jsonObject.getString("photo");
                // Cria usuário a partir do parsing do Json Object
                mUser = new User(id, name, token, photo);
                // Atualiza a UI na thread principal
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mGreetingsTextView.setText(Html.fromHtml(getString(R.string.greetings_user, mUser.getName())));
                        Drawable photo = mApiServices.getPhotoDrawable(mUser.getPhotoRef(), getApplicationContext());
                        mProfilePicture.setImageDrawable(photo);
                        mApiServices.getJobSuggestions(mUser.getToken());
                    }
                });
            } else {
                Log.i(TAG, response.message());
                Log.i(TAG, "Error! Response isn't successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuggestionsRequestCompleted(Call call, Response response) {
        // Chamada do método de getSuggestions que depende das informações do request getUser
        try {
            String stringResponse = response.body().string();
            Log.i(TAG, stringResponse);
            JSONArray jsonArray = new JSONArray(stringResponse);
            setupJobSuggestionList(jsonArray.length(), jsonArray);

            Object jsonObject = jsonArray.get(0);
            Object object = jsonArray.get(0);
            Log.i(TAG, "0: " + jsonObject.toString());
            Log.i(TAG, "1: " + object.toString());
            Log.i(TAG, "length: " + jsonArray.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSpinner.setVisibility(View.GONE);
                mWrapper.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Método para adicionar o dot marcado abaixo do slider de vagas
     * @param currentPage   Página atual
     */
    private void setupDots(int currentPage) {
        // Para não duplicar
        mDotsLayout.removeAllViews();
        // Inicializa os dots
        mDots = new ImageView[mDotsCount];
        // Itera por todos os dots e coloca como default
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new ImageView(getApplicationContext());
            mDots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_dot));
            mDots[i].setAlpha(0.25f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(6,8,6,8);

            mDotsLayout.addView(mDots[i], params);
        }

        // Seta o dot da página atual como ativo (é mais econômico fazer esse if para mudar aqui do que checar todas as vezes no loop)
        if (mDots.length > 0)
            mDots[currentPage].setImageDrawable(getDrawable(R.drawable.active_dot));
        mDots[currentPage].setAlpha(1f);
    }

    private void setupJobSuggestionList(int length, JSONArray jsonArray) throws JSONException {
        mJobSuggestionList = new JobSuggestion[length];
        for(int i = 0; i < length; i++) {
            // Setando os campos para criar o objeto
            String title = jsonArray.getJSONObject(i).getString("jobAdTile");
            String company = jsonArray.getJSONObject(i).getString("company");
            String date = jsonArray.getJSONObject(i).getString("date");
            int totalPositions = jsonArray.getJSONObject(i).getInt("totalPositions");

            // Array para as localizações
            JSONArray locationJsonArray = jsonArray.getJSONObject(i).getJSONArray("locations");
            String[] locationsArray = new String[locationJsonArray.length()];
            // Iteração para preencher o array
            for(int j = 0; j < locationJsonArray.length(); j++) {
                locationsArray[j] = locationJsonArray.get(j).toString();
            }

            // Salário recebe o valor real se não for vazio, caso contrário recebe o range
            String realSalary = jsonArray.getJSONObject(i).getJSONObject("salary").getString("real");
            String salaryRange = jsonArray.getJSONObject(i).getJSONObject("salary").getString("range");
            String salary = realSalary.isEmpty() ? salaryRange : realSalary;
            Log.i(TAG, "Salário é: " + salary);

            mJobSuggestionList[i] = new JobSuggestion(title, company, date, totalPositions, locationsArray, salary);
        }

        setViewPagerAdapter(mJobSuggestionList);
    }

    private void setViewPagerAdapter (JobSuggestion[] jobSuggestions) {
        // Inicializa o Adapter do View Pager
        JobsViewPagerAdapter jobsViewPagerAdapter = new JobsViewPagerAdapter(getSupportFragmentManager(), jobSuggestions);
        mViewPager.setAdapter(jobsViewPagerAdapter);

        // Configura o ViewPager
        mViewPager.setPageMargin(convertDpToPixels(this,16));
        mViewPager.setClipChildren(false);

        // Configura os dots
        mDotsCount = jobsViewPagerAdapter.getCount();
        setupDots(0);

        // Listener do ViewPager para a mudança de fragments
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setupDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Muda a vaga automaticamente a cada 5 segundos
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CustomTimerTask(), 10000, 5000);
    }

    public JobSuggestion getJobSuggestion(int index) {
        if (index <= (mJobSuggestionList.length - 1)) {
            return mJobSuggestionList[index];
        } else {
            return null;
        }
    }

    /**
     * Método para converter pixels relativos para absolutos
     * @param context   Referência ao contexto do app
     * @param dip       Pixels em DPI a serem convertidos
     * @return          Resultado de pixels em PX
     */
    private static int convertDpToPixels(Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }

    /**
     * Classe que define a execução de um timer para mudar as sugestões de vagas automaticamente
     */
    public class CustomTimerTask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentItem = mViewPager.getCurrentItem();
                    if (currentItem < 4) {
                        mViewPager.setCurrentItem(++currentItem);
                    } else {
                        mViewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }
}
