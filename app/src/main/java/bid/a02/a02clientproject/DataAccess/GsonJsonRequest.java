package bid.a02.a02clientproject.DataAccess;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class GsonJsonRequest<T, RequestType> extends JsonObjectRequest {
    private static final Gson gson = new Gson();
    private final Class<T> responseClazz;
    private final Class<RequestType> requestClazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private final RequestType request;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param responseClazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonJsonRequest(int httpVerb, String url, Class<T> responseClazz, RequestType request, Class<RequestType> requestClazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) throws JSONException {
//        super(httpVerb, url, errorListener);
//         public JsonRequest(int method, String url, String requestBody, Listener<T> listener,
//                ErrorListener errorListener)

//        super(httpVerb, url, gson.toJson(request, requestClazz), listener, errorListener);

        super(httpVerb, url, new JSONObject(gson.toJson(request, requestClazz)), null, errorListener);
//        super(httpVerb, url, "{\"email\": \"fdsafda\", \"password\": \"1234\". \"confirmPassword\": \"1234\", \"name\": \"afdsfsad\" }", listener, errorListener);
        this.requestClazz = requestClazz;
        this.responseClazz = responseClazz;
        this.headers = headers;
        this.listener = listener;
        this.request = request;
    }

//    @Override
//    public String getBodyContentType() {
//        return "application/x-www-form-urlencoded; charset=UTF-8";
//    }
//
//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        return headers != null ? headers : super.getHeaders();
//    }

//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("Content-Type", "application/json");
//        return params;
//    }
//
//    @Override
//    protected void deliverResponse(T response) {
//        listener.onResponse(response);
//    }
//
//    @Override
//    protected Response<T> parseNetworkResponse(NetworkResponse response) {
//        try {
//            String json = new String(
//                    response.data,
//                    HttpHeaderParser.parseCharset(response.headers));
//            return Response.success(
//                    gson.fromJson(json, responseClazz),
//                    HttpHeaderParser.parseCacheHeaders(response));
//        } catch (UnsupportedEncodingException e) {
//            return Response.error(new ParseError(e));
//        } catch (JsonSyntaxException e) {
//            return Response.error(new ParseError(e));
//        }
//    }
}
