package bid.a02.a02clientproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import bid.a02.a02clientproject.DataAccess.House;

public class DbActivity extends AppCompatActivity {


    private HouseViewModel houseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv = findViewById(R.id.countText);
        final TextView scrollTV = findViewById(R.id.txtHouseData);

        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<House>> houseObserver = new Observer<List<House>>() {
            @Override
            public void onChanged(@Nullable final List<House> houses) {
                // Update the UI, in this case, a TextView.
                tv.setText("no of Houses is " + houses.size());
            }
        };

        final Observer<List<House>> SecondHouseObserver = new Observer<List<House>>() {
            @Override
            public void onChanged(@Nullable final List<House> houses) {
                // Update the UI, in this case, a TextView.

                scrollTV.setText("");
                for(House h : houses)
                {
                    scrollTV.setText(scrollTV.getText() +  "\nHouse Id " + h.id);
                }
            }
        };


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        houseViewModel.getHouses().observe(this, houseObserver);
        houseViewModel.getHouses().observe(this, SecondHouseObserver);


    }

    public void btnAddHouse(View view) {

        House myHouse = new House();
        myHouse.address = "hello333";

        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
        houseViewModel.addHouse(myHouse);

    }
}
