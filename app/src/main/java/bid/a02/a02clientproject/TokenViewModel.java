package bid.a02.a02clientproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import bid.a02.a02clientproject.retrofitObjects.TokenResponse;

/**
 * Created by wai on 20/12/17.
 */

public class TokenViewModel extends AndroidViewModel {


    public TokenViewModel(@NonNull Application application) {
        super(application);
    }

    private TokenResponse tokenResponse;

    public TokenResponse getTokenResponse()
    {

        return tokenResponse;
    }

    public void setTokenResponse(TokenResponse tokenResponse)
    {
        this.tokenResponse = tokenResponse;
    }

}
