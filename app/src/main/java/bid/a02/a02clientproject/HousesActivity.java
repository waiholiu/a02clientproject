package bid.a02.a02clientproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import bid.a02.a02clientproject.DataAccess.House;

public class HousesActivity extends AppCompatActivity {


    private HouseViewModel houseViewModel;
    private HouseAdapter houseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HousesActivity.this, HouseDetailActivity.class);
                intent.putExtra("mode", "new");
                startActivity(intent);

            }
        });

        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
//        houseViewModel.getHouses()

        RecyclerView recList = (RecyclerView) findViewById(R.id.houseList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        // LayoutManager determines how ViewHolder's are laid out
        recList.setLayoutManager(llm);

        // Adapters determine the data . The CreateList is just a method that returns a List<ContactInfo> of mock data
        houseAdapter = new HouseAdapter(new ArrayList<House>());
        recList.setAdapter(houseAdapter);

        houseViewModel.getHouses().observe(HousesActivity.this, new Observer<List<House>>() {

            @Override
            public void onChanged(@Nullable List<House> houses) {
                houseAdapter.addItems(houses);
            }
        });







    }

    public void btnOpenHouse(View view) {
        Intent intent = new Intent(HousesActivity.this, HouseDetailActivity.class);
        intent.putExtra("mode", "edit");

        startActivity(intent);
    }
}
