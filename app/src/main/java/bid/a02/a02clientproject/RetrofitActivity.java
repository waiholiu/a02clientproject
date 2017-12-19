package bid.a02.a02clientproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import bid.a02.a02clientproject.DataAccess.RegisterRequest;
import bid.a02.a02clientproject.retrofitObjects.ServerService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void retrofitlogin_click(View view) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testbasicauth20170222085409.azurewebsites.net/")
                .build();

        ServerService service = retrofit.create(ServerService.class);

        Call<ResponseBody> call = service.getToken("2@test.com", "Sample1!", "password");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    TextView tv = (TextView)findViewById(R.id.tvToken);
                    try {
                        tv.setText(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });

    }


    public void retrofitregister_click(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testbasicauth20170222085409.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerService service = retrofit.create(ServerService.class);

        RegisterRequest req = new RegisterRequest();
        req.Name = "FirstAndroid Guy";
        req.Email = "android@test.com";
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
}
