package bid.a02.a02clientproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HouseViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public HouseViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    private MutableLiveData<List<House>> mHouses;

    public MutableLiveData<List<House>> getHouses() {
        List<House> houses = null;
        try {
            houses = new
                    getHousesDataAsyncTask(appDatabase).execute().get();
            if (mHouses == null) {
                mHouses = new MutableLiveData<List<House>>();
            }
            mHouses.setValue(houses);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();

        }
        return mHouses;
    }

    private static class getHousesDataAsyncTask extends AsyncTask<Void,
                Void, List<House>> {

        private AppDatabase db;

        public getHousesDataAsyncTask(AppDatabase userDatabase) {
            db = userDatabase;
        }

        @Override
        protected List<House> doInBackground(Void... param) {
            List<House> houses = db.houseDao().getAll();
            return houses;
        }

    }


    public void addHouse(House house) {
        new AddHouseAsyncTask(appDatabase).execute(house);
        getHouses();

    }

    private static class AddHouseAsyncTask extends AsyncTask<House, Void,Void> {

        private AppDatabase db;

        public AddHouseAsyncTask(AppDatabase userDatabase) {
            db = userDatabase;
        }

        @Override
        protected Void doInBackground(House... house) {
            db.houseDao().insert(house[0]);
            return null;
        }
    }





// Rest of the ViewModel...
}