package bid.a02.a02clientproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ServerActivity extends AppCompatActivity {

    //
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        mTextView = (TextView) findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://testbasicauth20170222085409.azurewebsites.net/token";
//        String url2 = "https://testbasicauth20170222085409.azurewebsites.net/api/values";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: " + response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("error " + error.getMessage());
//            }
//
//
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("grant_type", "password");
            jsonBody.put("username", "2@test.com");
            jsonBody.put("password", "Sample1!");

        }
        catch(Exception ex)
        {}

        queue.add(new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTextView.setText("onResponse = \n " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("response error \n" + error.networkResponse.statusCode);
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "2@test.com");
                params.put("password", "Sample1!");
                params.put("grant_type", "password");
                return params;
            }

        });


    }
}
