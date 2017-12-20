package bid.a02.a02clientproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bid.a02.a02clientproject.DataAccess.RegisterRequest;
import bid.a02.a02clientproject.retrofitObjects.ServerService;
import bid.a02.a02clientproject.retrofitObjects.TokenResponse;
import bid.a02.a02clientproject.retrofitObjects.UserTopic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tokenViewModel = ViewModelProviders.of(this).get(TokenViewModel.class);

        final Observer<TokenResponse> tokenObserver = new Observer<TokenResponse>() {
            @Override
            public void onChanged(@Nullable final TokenResponse tokenResponse) {
                // Update the UI, in this case, a TextView.

                TextView tv = (TextView)findViewById(R.id.tvToken);
                tv.setText("access token : \n" + tokenResponse.accessToken);
                accessToken = tokenResponse.accessToken;
            }
        };


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        tokenViewModel.getTokenResponse().observe(this, tokenObserver);

    }

    private String accessToken;
    private TokenViewModel tokenViewModel;

    public void retrofitlogin_click(View view) {

        Object customDateAdapter = new Object() {
            final DateFormat dateFormat;
            {
                dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            }

            @ToJson
            synchronized String dateToJson(Date d) {
                return dateFormat.format(d);
            }

            @FromJson
            synchronized Date dateToJson(String s) throws ParseException {
                return dateFormat.parse(s);
            }
        };

        Moshi moshi = new Moshi.Builder()
                .add(customDateAdapter)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testbasicauth20170222085409.azurewebsites.net/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        ServerService service = retrofit.create(ServerService.class);

        Call<TokenResponse> call = service.getToken("admin@admin.com", "Sample1!", "password");

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {

                    tokenViewModel.setTokenResponse(response.body());


                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });

    }


    public void retrofitregister_click(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testbasicauth20170222085409.azurewebsites.net/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        ServerService service = retrofit.create(ServerService.class);

        RegisterRequest req = new RegisterRequest();
        req.Name = "FirstAndroid Guy";
        req.Email = "android2@test.com";
        req.ConfirmPassword = "Sample1!";
        req.Password = "Sample1!";

        Call<Void> call = service.register(req);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                 if (response.isSuccessful()) {
                    // tasks available
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void btnGetUserTopics(View view) {
        Object customDateAdapter = new Object() {
            final DateFormat dateFormat;
            {
                dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            }

            @ToJson
            synchronized String dateToJson(Date d) {
                return dateFormat.format(d);
            }

            @FromJson
            synchronized Date dateToJson(String s) throws ParseException {
                return dateFormat.parse(s);
            }
        };

        Moshi moshi = new Moshi.Builder()
                .add(customDateAdapter)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testbasicauth20170222085409.azurewebsites.net/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        ServerService service = retrofit.create(ServerService.class);

        Call<List<UserTopic>> call = service.getUserTopic("bearer " + this.accessToken);

        call.enqueue(new Callback<List<UserTopic>>() {
            @Override
            public void onResponse(Call<List<UserTopic>> call, Response<List<UserTopic>> response) {
                if (response.isSuccessful()) {

                    TextView tv = (TextView)findViewById(R.id.tvUserTopic);
                    tv.setText("number of topics : \n" + response.body().size());


                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<List<UserTopic>> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });

    }
}
