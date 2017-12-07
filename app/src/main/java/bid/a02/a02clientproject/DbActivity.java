package bid.a02.a02clientproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DbActivity extends AppCompatActivity {


    private HouseViewModel houseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        final TextView tv = findViewById(R.id.countText);
        final TextView scrollTV = findViewById(R.id.txtHouseData);
//        List<House> houses = db.houseDao().getAll();

//        tv.setText("number of houses is " + houses.size());

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
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "myfirst-db2").allowMainThreadQueries().build();

        House myHouse = new House();
        myHouse.address = "hello333";
        db.houseDao().insert(myHouse);

        houseViewModel.getHouses().setValue(db.houseDao().getAll());
    }
}
