package bid.a02.a02clientproject.retrofitObjects;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by wai on 20/12/17.
 */

public class TokenResponse {
    @Json(name="access_token")
    public String accessToken;
    public String token_type;
    public int expires_in;
    public String userName;

    public String name;
    @Json(name=".issued")
    public String issued;
    @Json(name=".expires")
    public Date expires;
    public String Roles;

}

