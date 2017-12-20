package bid.a02.a02clientproject.retrofitObjects;

import java.util.List;

import bid.a02.a02clientproject.DataAccess.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by wai on 19/12/17.
 */

public interface ServerService {

    @POST("api/account/register")
    Call<Void> register(@Body RegisterRequest request);

    @POST("/token")
    @FormUrlEncoded
    Call<TokenResponse> getToken(@Field("username") String username,
                                @Field("password") String password, @Field("grant_type") String grant_type);


    @GET("api/userTopic")
    Call<List<UserTopic>> getUserTopic(@Header("Authorization") String authHeader);


}