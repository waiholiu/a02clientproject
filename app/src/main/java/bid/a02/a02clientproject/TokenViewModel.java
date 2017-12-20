package bid.a02.a02clientproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bid.a02.a02clientproject.retrofitObjects.TokenResponse;

/**
 * Created by wai on 20/12/17.
 */

public class TokenViewModel extends AndroidViewModel {


    public TokenViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<TokenResponse> tokenResponse;

    public MutableLiveData<TokenResponse> getTokenResponse()
    {

        if (tokenResponse == null) {
            tokenResponse = new MutableLiveData<TokenResponse>();
        }
        return tokenResponse;
    }

    public void setTokenResponse(TokenResponse tokenResponse)
    {
        this.tokenResponse.setValue(tokenResponse);
    }

}
