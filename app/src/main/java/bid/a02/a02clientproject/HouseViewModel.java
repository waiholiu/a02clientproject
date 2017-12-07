package bid.a02.a02clientproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class HouseViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<List<House>> mHouses;

    public MutableLiveData<List<House>> getHouses() {
        if (mHouses == null) {
            mHouses = new MutableLiveData<List<House>>();
        }
        return mHouses;
    }

// Rest of the ViewModel...
}