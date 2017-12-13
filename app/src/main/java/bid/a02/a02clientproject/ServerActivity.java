package bid.a02.a02clientproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bid.a02.a02clientproject.DataAccess.GsonRequest;
import bid.a02.a02clientproject.DataAccess.MySingleton;
import bid.a02.a02clientproject.DataAccess.TopicDto;


public class ServerActivity extends AppCompatActivity {

    //
    TextView mTextView;

    TextView txtTopics;
    JSONObject token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        mTextView = (TextView) findViewById(R.id.textView);
        txtTopics = (TextView) findViewById(R.id.txtTopics);

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


        StringRequest myRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    token = new JSONObject(response.toString());
                    mTextView.setText("token = \n " + token.get("access_token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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

        };

        MySingleton.getInstance(this).addToRequestQueue(myRequest);
    }

    public void btnGetAllTopics(View view) {

        String url = "https://testbasicauth20170222085409.azurewebsites.net/api/topics";

        GsonRequest<TopicDto[]> myReq = new GsonRequest<TopicDto[]>(url,
                TopicDto[].class, null,
                createSuccessListener(), // listener for success
                createErrorListener());  // listener for failure
        MySingleton.getInstance(this).addToRequestQueue(myReq);

    }

    private Response.Listener<TopicDto[]> createSuccessListener() {

        return new Response.Listener<TopicDto[]>() {
            @Override
            public void onResponse(TopicDto[] response) {
//                Log.i(TAG, "Response : " + response.getSite());
                txtTopics.setText("length " + response.length);

            }
        };

    }

    private Response.ErrorListener createErrorListener() {

        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("err", "Error : " + error.getLocalizedMessage());
            }
        };

    }
}
