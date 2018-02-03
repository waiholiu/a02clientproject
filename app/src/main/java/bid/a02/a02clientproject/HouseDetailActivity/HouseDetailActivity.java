package bid.a02.a02clientproject.HouseDetailActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import bid.a02.a02clientproject.DataAccess.House;
import bid.a02.a02clientproject.R;
import bid.a02.a02clientproject.ViewModels.HouseViewModel;

public class HouseDetailActivity extends AppCompatActivity {


    EditText evAddress;
    EditText evNotes;
    String mode;
    Spinner spinnerRooms;
    Spinner customSpinner;

    House currentHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        evAddress = (EditText) findViewById(R.id.txtAddress);
        evNotes = (EditText) findViewById(R.id.txtNotes);

        spinnerRooms = (Spinner) findViewById(R.id.spinnerNoOfRooms);

        Integer[] roomArray = new Integer[]{1, 2, 3, 4, 5, 6};


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, roomArray);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerRooms.setAdapter(adapter);


        CustomSpinnerAdapterInput[] inputs = {
            new CustomSpinnerAdapterInput("var1", "sub1"),
                    new CustomSpinnerAdapterInput("var2", "sub2"),
                    new CustomSpinnerAdapterInput("var3", "sub3")
        };

        customSpinner = (Spinner) findViewById(R.id.spinnerCustom);
        // first parameter is context which is this activity
        // second parameter is the xml file for each row
        // third parameter is the data to be passed in
        customSpinner.setAdapter(new CustomSpinnerAdapter(this, inputs));


        mode = getIntent().getExtras().getString("mode").toString();

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);

        if (mode.equals("new")) {
            tvTitle.setText("New House");
        } else {
            tvTitle.setText("existing house");
            String houseId = getIntent().getExtras().getString("houseId").toString();

            houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
            currentHouse = houseViewModel.getHouseById(Integer.parseInt(houseId)).getValue();
            evAddress.setText(currentHouse.address);
            evNotes.setText(currentHouse.notes);
            spinnerRooms.setSelection(currentHouse.noOfRooms - 1, true);


        }


    }

    private HouseViewModel houseViewModel;

    private void SaveHouse() {

        if (mode.equals("new")) {
            currentHouse = new House();
        }

        currentHouse.address = evAddress.getText().toString();
        currentHouse.notes = evNotes.getText().toString();
        currentHouse.noOfRooms = (int) spinnerRooms.getSelectedItem();
        houseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);
        houseViewModel.saveHouse(currentHouse);
        NavUtils.navigateUpFromSameTask(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_housedetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save:
                SaveHouse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
